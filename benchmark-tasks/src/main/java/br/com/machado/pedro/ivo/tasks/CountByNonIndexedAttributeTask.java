package br.com.machado.pedro.ivo.tasks;

import br.com.machado.pedro.ivo.beans.enums.OperationType;
import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import br.com.machado.pedro.ivo.entity.beans.generic.Country;
import br.com.machado.pedro.ivo.index.store.factory.IndexStoreFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * That task will just do a simple count query by a indexed field
 *
 * @author Pedro
 */
public class CountByNonIndexedAttributeTask implements Command {

		private static final Logger LOGGER = LoggerFactory.getLogger(CountByNonIndexedAttributeTask.class);

		private static final String        TASK_ID   = "COUNT_BY_NON_INDEXED_ATTIRBUTE";
		private static final OperationType operation = OperationType.SELECT;
		private Country country;

		public CountByNonIndexedAttributeTask(Country country) {
				this.country = country;
		}

		@Override
		public void execute() {
				SimpleDAO dao = DAOFactory.getInstance();
				if (dao.isNonIndexQueriesSupported()) {
						Long totalTime = dao.countByNonIndexedCountry(country);

						Map<String, Object> metadata = new HashMap<>();
						metadata.put("country", country);
						metadata.put("total", dao.getResult());

						IndexStoreFactory.getIndexStore().store(totalTime, operation, dao.getEngine(), TASK_ID, metadata);
				}
				DAOFactory.requeue(dao);
		}

		@Override
		public void run() {
				this.execute();
		}

}
