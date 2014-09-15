package br.com.machado.pedro.ivo.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * That task will add 1 million entries in each database to check how fast they are doing this operation. This will also check if a engine is affected by
 * degradation after have a million entries in only one collection. It will also support other tasks like select, delete and update tasks.
 *
 * @author Pedro
 */
public class OneMillionInsertsTask implements Command {

		private static final Logger LOGGER = LoggerFactory.getLogger(OneMillionInsertsTask.class);

		// ThreadPoolInstance
		private static ThreadPoolExecutor threadPool;
		private static final long TOTAL_OF_ROWS = 1000001;

		public OneMillionInsertsTask() {
				threadPool = new ThreadPoolExecutor(50, 50, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
		}

		@Override
		public void execute() {
				/**
				 * Will create 1 million SimpleEntities
				 *
				 */
				for (int i = 1; i < TOTAL_OF_ROWS; i++) {
						threadPool.submit(new SimpleEntityInsertTask());
				}

				while (threadPool.getCompletedTaskCount() < (TOTAL_OF_ROWS - 1)) {
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
