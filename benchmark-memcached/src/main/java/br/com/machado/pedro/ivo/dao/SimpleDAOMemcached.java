package br.com.machado.pedro.ivo.dao;

import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import br.com.machado.pedro.ivo.entity.beans.generic.Country;
import br.com.machado.pedro.ivo.entity.generic.SimpleEntity;
import br.com.machado.pedro.ivo.entity.mock.SimpleEntityImpl;
import com.google.gson.Gson;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleDAOMemcached extends SimpleDAO {

		private MemcachedClient conn = null;

		// Database credentials
		private static final String SERVER_IP = "172.16.83.4:11211";
		private static final Logger LOGGER    = LoggerFactory.getLogger(SimpleDAOMemcached.class);
		private              Gson   gson      = new Gson();

		@Override
		public String getEngine() {
				return "MEMCACHED";
		}

		@Override
		public Long save(SimpleEntity entity) {
				try {
						long startTime = System.nanoTime();

						getConnection().add(entity.getId().toString(), 3600, gson.toJson(entity));

						return System.nanoTime() - startTime;
				}
				catch (Exception e) {
						LOGGER.error("Method[save] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return 0l;
		}

		@Override public Long countByIndexedCountry(Country country) {
				result = 0l;
				return 0l;
		}

		@Override public Long countByNonIndexedCountry(Country country) {
				result = 0l;
				return 0l;
		}

		@Override public Long selectByIndexedCountry(Country country) {
				return 0l;
		}

		@Override public Long selectByNonIndexedCountry(Country country) {
				return 0l;
		}

		@Override public SimpleEntity findById(Long id) {
				try {
						return gson.fromJson((String) getConnection().get(id.toString()), SimpleEntityImpl.class);
				}
				catch (Exception e) {
						LOGGER.error("Method[findById] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return null;
		}

		@Override public Long update(SimpleEntity entity) {
				try {
						long startTime = System.nanoTime();

						getConnection().replace(entity.getId().toString(), (60 * 60 * 2), gson.toJson(entity));

						return System.nanoTime() - startTime;
				}
				catch (Exception e) {
						LOGGER.error("Method[update] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return 0l;
		}

		@Override public Long deleteById(Long id) {
				try {
						long startTime = System.nanoTime();

						getConnection().delete(id.toString());

						return System.nanoTime() - startTime;
				}
				catch (Exception e) {
						LOGGER.error("Method[update] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return 0l;
		}

		@Override public Long selectByIndexedCountry(Country country, int offset, int pagesize) {
				return 0l;
		}

		@Override public Long selectByNonIndexedCountry(Country country, int offset, int pagesize) {
				return 0l;
		}

		@Override public boolean isConnected() {
				return true;
		}

		@Override public void reconnect() {
				try {
						conn = null;
						connect();
				}
				catch (Exception e) {
						LOGGER.error("Method[reconnect] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
		}

		@Override public void close() {
				try {
						conn = null;
				}
				catch (Exception e) {
						LOGGER.error("Method[close] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
		}

		private MemcachedClient getConnection() {
				if (conn == null) {
						connect();
				}

				return conn;
		}

		private synchronized void connect() {
				try {
						XMemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(SERVER_IP));
						conn = builder.build();
				}
				catch (Exception e) {
						LOGGER.error("Method[connect] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
		}

		public boolean isNonIndexQueriesSupported() {
				return false;
		}

		public boolean isIndexQueriesSupported() {
				return false;
		}
}
