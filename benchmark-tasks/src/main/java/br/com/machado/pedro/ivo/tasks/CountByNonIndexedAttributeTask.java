package br.com.machado.pedro.ivo.tasks;

import br.com.machado.pedro.ivo.beans.enums.OperationType;
import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import br.com.machado.pedro.ivo.entity.beans.generic.Country;
import br.com.machado.pedro.ivo.index.store.factory.IndexStoreFactory;

/**
 * That task will just do a simple count query by a indexed field
 *
 * @author Pedro
 */
public class CountByNonIndexedAttributeTask implements Command {

		private static final String        TASK_ID   = "COUNT_BY_NON_INDEXED_ATTIRBUTE";
		private static final OperationType operation = OperationType.SELECT;

		@Override
		public void execute() {
				/**
				 * Will query by any country
				 */
				SimpleDAO dao = DAOFactory.createSimpleDAO();

				for (Country country : Country.values()) {
						Long totalTime = dao.countByNonIndexedCountry(country);
						IndexStoreFactory.getIndexStore().store(totalTime, operation, dao.getEngine(), TASK_ID, null);
				}
		}

		@Override
		public void run() {
				this.execute();
		}

}
