package br.com.machado.pedro.ivo.tasks;

import br.com.machado.pedro.ivo.beans.enums.OperationType;
import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import br.com.machado.pedro.ivo.entity.beans.generic.Country;
import br.com.machado.pedro.ivo.index.store.factory.IndexStoreFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * That task will just do a simple count query by a indexed field
 *
 * @author Pedro
 */
public class SelectAllWithPaginationByIndexedAttributeTask implements Command {

		private static final String        TASK_ID       = "SELECT_ALL_WITH_PAGINATION_BY_INDEXED_ATTIRBUTE";
		private static final String        TASK_TOTAL_ID = "SELECT_ALL_WITH_PAGINATION_BY_INDEXED_ATTIRBUTE_TOTAL";
		private static final OperationType operation     = OperationType.SELECT;
		private static final int           pagesize      = 100;
		private Long    total;
		private Country country;
		private Map<String, Object> metadata = new HashMap<>();

		public SelectAllWithPaginationByIndexedAttributeTask(Long total, Country country) {
				this.total = total;
				this.country = country;
				metadata.put("country", country.toString());
				metadata.put("pagesize", pagesize);
		}

		@Override
		public void execute() {
				int offset = 0;
				Long fullTime = 0L;
				SimpleDAO dao = DAOFactory.getInstance();

				if (dao.isIndexQueriesSupported()) {
						while (offset <= total) {
								Long totalTime = dao.selectByIndexedCountry(country, offset, pagesize);
								fullTime = fullTime + totalTime;
								metadata.put("offset", offset);

								IndexStoreFactory.getIndexStore().store(totalTime, operation, dao.getEngine(), TASK_ID, metadata);
								offset = offset + pagesize;
								try {
										Thread.sleep(100);
								}
								catch (InterruptedException e) {
								}
						}

						metadata.remove("offset");
						metadata.put("entries", total);
						metadata.put("total", true);

						IndexStoreFactory.getIndexStore().store(fullTime, operation, dao.getEngine(), TASK_TOTAL_ID, metadata);
				}
				try {
						Thread.sleep(500);
				}
				catch (InterruptedException e) {
				}
				SelectAllWithPaginationByIndexedAttributeHandlerTask.countries.remove(country);
				DAOFactory.requeue(dao);
		}

		@Override
		public void run() {
				this.execute();
		}

}
