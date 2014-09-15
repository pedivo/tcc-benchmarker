package br.com.machado.pedro.ivo;

import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import br.com.machado.pedro.ivo.entity.beans.generic.Country;
import br.com.machado.pedro.ivo.entity.generic.SimpleEntity;
import br.com.machado.pedro.ivo.entity.mock.SimpleEntityImpl;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by pedivo on 30/08/14.
 * <p/>
 * <p/>
 */
public class SimpleDAORedis extends SimpleDAO {

		private        Jedis     conn;
		private static JedisPool pool;
		private static boolean isIndexCreated = false;
		private        Gson    gson           = new Gson();
		private static int     connectionFail = 0;

		// Database credentials
		private static final String SERVER_IP = "172.16.83.4";
		private static final Logger LOGGER    = LoggerFactory.getLogger(SimpleDAORedis.class);

		@Override
		public String getEngine() {
				return "REDIS";
		}

		@Override
		public Long save(SimpleEntity entity) {
				try {

						String object = gson.toJson(entity);

						long startTime = System.nanoTime();

						getConnection().set(entity.getId().toString(), object);
						getConnection().zadd(entity.getIndexedCountry().toString(), new Double(entity.getId()), object);

						long totalTime = System.nanoTime() - startTime;
						requeue();

						return totalTime;
				}
				catch (Exception e) {
						LOGGER.error("Method[save] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
						requeue();
				}
				return 0l;
		}

		@Override public Long countByIndexedCountry(Country country) {
				try {
						long startTime = System.nanoTime();

						result = getConnection().zcard(country.toString());

						long totalTime = System.nanoTime() - startTime;
						requeue();

						return totalTime;
				}
				catch (Exception e) {
						LOGGER.error("Method[countByIndexedCountry] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
						requeue();
						return countByIndexedCountry(country);
				}
		}

		@Override public Long selectByIndexedCountry(Country country, int offset, int pagesize) {
				try {
						long startTime = System.nanoTime();
						pagesize--;

						getConnection().zrange(country.toString(), new Long(offset).longValue(), new Long(offset + pagesize).longValue());
						getConnection().disconnect();
						getConnection().close();

						long totalTime = System.nanoTime() - startTime;

						requeue();

						Thread.sleep(100);

						return totalTime;
				}
				catch (Exception e) {
						LOGGER.error("Method[selectByIndexedCountry] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
						requeue();
						return selectByIndexedCountry(country, offset, pagesize);
				}
		}

		@Override public Long selectByIndexedCountry(Country country) {
				try {
						long startTime = System.nanoTime();

						getConnection().zrange(country.toString(), 0l, -1l);
						getConnection().disconnect();
						getConnection().close();

						long totalTime = System.nanoTime() - startTime;
						requeue();

						Thread.sleep(100);

						return totalTime;
				}
				catch (Exception e) {
						LOGGER.error("Method[selectByIndexedCountry] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
						requeue();
						return selectByIndexedCountry(country);
				}
		}

		@Override public Long selectByNonIndexedCountry(Country country) {
				requeue();
				return 0l;
		}

		@Override public SimpleEntity findById(Long id) {
				try {
						SimpleEntityImpl entity = gson.fromJson(getConnection().get(id.toString()), SimpleEntityImpl.class);
						requeue();
						return entity;
				}
				catch (Exception e) {
						LOGGER.error("Method[findById] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
						requeue();
						return findById(id);
				}
		}

		@Override public Long update(SimpleEntity entity) {
				try {
						long startTime = System.nanoTime();

						String jsonEntity = getConnection().get(entity.getId().toString());

						SimpleEntityImpl oldEntity = gson.fromJson(jsonEntity, SimpleEntityImpl.class);

						String object = gson.toJson(entity);
						getConnection().set(entity.getId().toString(), object);

						getConnection().zrem(oldEntity.getIndexedCountry().toString(), jsonEntity);
						getConnection().zadd(entity.getIndexedCountry().toString(), new Double(entity.getId()), object);

						long totalTime = System.nanoTime() - startTime;
						requeue();

						return totalTime;
				}
				catch (Exception e) {
						LOGGER.error("Method[update] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
						requeue();
						return update(entity);
				}
		}

		@Override public Long deleteById(Long id) {
				long totalTime = 0l;
				try {
						long startTime = System.nanoTime();

						getConnection().del(id.toString());

						totalTime = (System.nanoTime() - startTime);

						requeue();

						return totalTime;
				}
				catch (Exception e) {
						LOGGER.error("Method[delete] id[{}] Exception m[{}] stack[{}]", id, e.getMessage(), e.getStackTrace());
						requeue();
						deleteById(id);
				}
				return 0l;
		}

		@Override public Long countByNonIndexedCountry(Country country) {
				requeue();
				return 0l;
		}

		@Override public Long selectByNonIndexedCountry(Country country, int offset, int pagesize) {
				requeue();
				return 0l;
		}

		private void requeue() {
				if (conn != null) {
						try {
								pool.returnResource(conn);
						}
						catch (Exception e) {
								try {
										pool.returnBrokenResource(conn);
								}
								catch (Exception e1) {
										conn = null;
								}
						}
						conn = null;
				}
		}

		@Override public boolean isConnected() {
				try {
						if (conn != null) {
								conn = null;
						}
				}
				catch (Exception e) {
						LOGGER.error("Method[isConnected] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
						requeue();
				}
				return false;
		}

		@Override public void reconnect() {
				if (conn != null) {
						conn.close();
				}
				conn = null;
				connect();
		}

		@Override public void close() {
				if (conn != null) {
						conn.close();
						conn = null;
				}
				pool.destroy();
				pool = null;
		}

		private Jedis getConnection() {
				if (conn == null) {
						connect();
				}

				return conn;
		}

		private synchronized void connect() {
				try {
						if (pool == null) {
								JedisPoolConfig config = new JedisPoolConfig();
								config.setMaxIdle(100);
								config.setMaxTotal(2000);
								config.setBlockWhenExhausted(false);
								config.setMaxWaitMillis(10);
								config.setTestWhileIdle(true);
								config.setTestOnReturn(true);
								config.setTestOnBorrow(true);
								config.setLifo(true);

								pool = new JedisPool(config, SERVER_IP);
						}

						conn = pool.getResource();
				}
				catch (Exception e) {
						try {
								Thread.sleep(1000);
						}
						catch (InterruptedException e1) {
						}
						connect();
						LOGGER.error("Method[connect] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
		}

		public boolean isNonIndexQueriesSupported() {
				return false;
		}
}
