package br.com.machado.pedro.ivo.dao.factory;

import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DAOFactory {

		private static List<SimpleDAO> pool;
		private static final Logger LOGGER = LoggerFactory.getLogger(DAOFactory.class);
		private static int POOL_SIZE = 200;

		public static final String DB_CONNECTION_POOL = "DB_CONNECTION_POOL";

		public static final String SIMPLE_DAO_FOR_NAME = "SIMPLE_DAO_FORNAME";

		static {
				if (System.getProperty(DB_CONNECTION_POOL) != null && !System.getProperty(DB_CONNECTION_POOL).isEmpty()) {
						POOL_SIZE = new Integer(System.getProperty(DB_CONNECTION_POOL)).intValue();
				}
				pool = new ArrayList<>();
				for (int i = 0; i < POOL_SIZE; i++) {
						try {
								pool.add((SimpleDAO) Class.forName(System.getProperty(SIMPLE_DAO_FOR_NAME)).newInstance());
						}
						catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
								LOGGER.error("Method[static] Unknown Error m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
						}
				}
		}

		public synchronized static SimpleDAO getInstance() {
				try {
						while (pool.isEmpty()) {
								try {
										Thread.sleep(100);
								}
								catch (InterruptedException e) {
										LOGGER.error("Method[getInstance] Unknown Error m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
								}
						}
						SimpleDAO dao = pool.get(0);
						if (dao == null) {
								LOGGER.info("Method[getInstance] fail to get connection instance pool_size[{}]", pool.size());
								dao = getInstance();
						}

						if (!dao.isConnected()) {
								dao.reconnect();
						}
						pool.remove(dao);
						return dao;
				} catch (Exception e) {
						e.printStackTrace();
						LOGGER.error("Method[getInstance] Unknown Error m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return null;
		}

		public synchronized static void requeue(SimpleDAO dao) {
				pool.add(dao);
		}

		public static void close() {
				for (SimpleDAO dao : pool) {
						dao.close();
						dao = null;
				}
				pool = null;
		}
}
