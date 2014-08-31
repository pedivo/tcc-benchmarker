package br.com.machado.pedro.ivo.tasks;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * This task will update 100k entries in each database to check how fast they are doing this operation. This will also check if a engine is affected by
 * degradation during many threads doing the same operation.
 *
 * @author Pedro
 */
public class HundredKUpdatesTask implements Command {

		// ThreadPoolInstance
		private static ThreadPoolExecutor threadPool;
		private static final long TOTAL_OF_ROWS = 100000;

		public HundredKUpdatesTask() {
				threadPool = new ThreadPoolExecutor(100, 100, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
		}

		@Override
		public void execute() {
				/**
				 * Will update 100k SimpleEntities
				 *
				 */
				int index = 0;
				for (int i = 0; i < TOTAL_OF_ROWS; i++) {
						threadPool.submit(new SimpleEntityUpdateTask(new Long(index)));
						index = index + 9;
				}

				while (threadPool.getCompletedTaskCount() < TOTAL_OF_ROWS) {
						try {
								Thread.sleep(2000);
						}
						catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
						}
				}
		}

		@Override
		public void run() {
				this.execute();
		}

}
