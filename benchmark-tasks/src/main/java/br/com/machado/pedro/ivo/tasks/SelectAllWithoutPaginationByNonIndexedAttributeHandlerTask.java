package br.com.machado.pedro.ivo.tasks;

import br.com.machado.pedro.ivo.entity.beans.generic.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Pedro
 */
public class SelectAllWithoutPaginationByNonIndexedAttributeHandlerTask implements Command {

		private static final Logger LOGGER = LoggerFactory.getLogger(SelectAllWithoutPaginationByNonIndexedAttributeHandlerTask.class);
		private static ThreadPoolExecutor threadPool;

		public SelectAllWithoutPaginationByNonIndexedAttributeHandlerTask() {
				threadPool = new ThreadPoolExecutor(20, 20, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
		}

		@Override
		public void execute() {
				/**
				 * Will query by any country
				 */
				int total = 0;
				for (Country country : Country.values()) {
						threadPool.submit(new SelectAllWithoutPaginationByNonIndexedAttributeTask(country));
						total++;
				}

				while (threadPool.getCompletedTaskCount() < total) {
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
