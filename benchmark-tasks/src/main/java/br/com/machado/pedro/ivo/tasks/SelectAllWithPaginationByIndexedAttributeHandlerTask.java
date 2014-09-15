package br.com.machado.pedro.ivo.tasks;

import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import br.com.machado.pedro.ivo.entity.beans.generic.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * This task has the responsability to count how many entries there're for each country
 * and then starts other task just to Select those entries using limit and offset
 *
 * @author Pedro
 */
public class SelectAllWithPaginationByIndexedAttributeHandlerTask implements Command {

		private static final Logger LOGGER = LoggerFactory.getLogger(SelectAllWithPaginationByIndexedAttributeHandlerTask.class);
		private static ThreadPoolExecutor threadPool;
		public static Set<Country> countries = new HashSet<>();
		private static LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();

		public SelectAllWithPaginationByIndexedAttributeHandlerTask() {
				threadPool = new ThreadPoolExecutor(20, 20, 1, TimeUnit.MINUTES, queue);
		}

		@Override
		public void execute() {
				/**
				 * Will query by any country
				 */
				int total = 0;
				SimpleDAO dao = DAOFactory.getInstance();

				for (Country country : Country.values()) {
						Long totalTime = dao.countByIndexedCountry(country);
						countries.add(country);
						threadPool.submit(new SelectAllWithPaginationByIndexedAttributeTask((Long) dao.getResult(), country));
						total++;

						DAOFactory.requeue(dao);
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
