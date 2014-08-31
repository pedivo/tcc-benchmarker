package br.com.machado.pedro.ivo.tasks;

import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import br.com.machado.pedro.ivo.entity.beans.generic.Country;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * This task has the responsability to count how many entries there're for each country
 * and then starts other task just to Select those entries using limit and offset
 *
 * @author Pedro
 */
public class SelectAllWithoutPaginationByIndexedAttributeHandlerTask implements Command {
		private static ThreadPoolExecutor threadPool;

		public SelectAllWithoutPaginationByIndexedAttributeHandlerTask() {
				threadPool = new ThreadPoolExecutor(20, 20, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
		}

		@Override
		public void execute() {
				/**
				 * Will query by any country
				 */
				int total = 0;
				for (Country country : Country.values()) {
						threadPool.submit(new SelectAllWithoutPaginationByIndexedAttributeTask(country));
						total++;
				}

				while (threadPool.getCompletedTaskCount() < total) {
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
