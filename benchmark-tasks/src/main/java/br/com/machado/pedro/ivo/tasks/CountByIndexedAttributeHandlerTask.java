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
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * That task will just do a simple count query by a indexed field
 *
 * @author Pedro
 */
public class CountByIndexedAttributeHandlerTask implements Command {

		private static final Logger LOGGER = LoggerFactory.getLogger(CountByIndexedAttributeHandlerTask.class);

		private static final String        TASK_ID   = "COUNT_BY_INDEXED_ATTIRBUTE";
		private static final OperationType operation = OperationType.SELECT;

		// ThreadPoolInstance
		private static ThreadPoolExecutor threadPool;

		public CountByIndexedAttributeHandlerTask() {
				threadPool = new ThreadPoolExecutor(100, 100, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
		}

		@Override
		public void execute() {
				/**
				 * Will query by any country
				 */
				for (Country country : Country.values()) {
						threadPool.submit(new CountByIndexedAttributeTask(country));
				}

				while (threadPool.getCompletedTaskCount() < Country.values().length) {
						try {
								Thread.sleep(2000);
						}
						catch (InterruptedException e) {
								LOGGER.error("Method[execute] Unknown Error m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
						}
				}
		}

		@Override
		public void run() {
				this.execute();
		}

}
