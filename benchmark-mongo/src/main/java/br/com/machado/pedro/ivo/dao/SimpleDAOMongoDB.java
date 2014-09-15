package br.com.machado.pedro.ivo.dao;

import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import br.com.machado.pedro.ivo.entity.beans.generic.Country;
import br.com.machado.pedro.ivo.entity.generic.SimpleEntity;
import br.com.machado.pedro.ivo.entity.mock.SimpleEntityImpl;
import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by pedivo on 30/08/14.
 * <p/>
 * <p/>
 */
public class SimpleDAOMongoDB extends SimpleDAO {

		private MongoClient conn = null;
		private DBCollection coll;
		private static boolean isIndexCreated = false;

		// Database credentials
		private static final String SERVER_IP   = "172.16.83.4";
		private static final int    SERVER_PORT = 27017;
		private static final Logger LOGGER      = LoggerFactory.getLogger(SimpleDAOMongoDB.class);

		@Override
		public Long save(SimpleEntity entity) {
				try {

						BasicDBObject doc = new BasicDBObject("_id", entity.getId())
								.append("firstname", entity.getFirstname())
								.append("lastname", entity.getLastname())
								.append("birthday", entity.getBirthday())
								.append("city", entity.getCity())
								.append("email", entity.getEmail())
								.append("indexedCountry", entity.getIndexedCountry().toString())
								.append("notIndexedCountry", entity.getNotIndexedCountry().toString());

						long startTime = System.nanoTime();

						getConnection().insert(doc);

						return System.nanoTime() - startTime;
				}
				catch (Exception e) {
						LOGGER.error("Method[save] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return 0l;
		}

		@Override
		public String getEngine() {
				return "MONGODB";
		}

		@Override public Long countByIndexedCountry(Country country) {
				try {
						long startTime = System.nanoTime();

						super.result = getConnection().count(new BasicDBObject("indexedCountry", country.toString()));

						long totalTime = System.nanoTime() - startTime;

						return totalTime;
				}
				catch (Exception e) {
						LOGGER.error("Method[countByIndexedCountry] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return 0l;
		}

		@Override public Long countByNonIndexedCountry(Country country) {
				try {
						long startTime = System.nanoTime();

						super.result = getConnection().count(new BasicDBObject("notIndexedCountry", country.toString()));

						long totalTime = System.nanoTime() - startTime;

						return totalTime;
				}
				catch (Exception e) {
						LOGGER.error("Method[countByNonIndexedCountry] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return 0l;
		}

		@Override public Long selectByIndexedCountry(Country country) {
				try {
						long startTime = System.nanoTime();

						getConnection().find(new BasicDBObject("indexedCountry", country.toString()));

						long totalTime = System.nanoTime() - startTime;

						return totalTime;
				}
				catch (Exception e) {
						LOGGER.error("Method[selectByIndexedCountry] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return 0l;
		}

		@Override public Long selectByNonIndexedCountry(Country country) {
				try {
						long startTime = System.nanoTime();

						getConnection().find(new BasicDBObject("notIndexedCountry", country.toString()));

						long totalTime = System.nanoTime() - startTime;

						return totalTime;
				}
				catch (Exception e) {
						LOGGER.error("Method[selectByNonIndexedCountry] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return 0l;
		}

		@Override public SimpleEntity findById(Long id) {
				try {
						DBObject myDoc = getConnection().findOne(new BasicDBObject("_id", id));

						SimpleEntityImpl entity = new SimpleEntityImpl();

						entity.setId(new Long(myDoc.get("_id").toString()));
						entity.setBirthday((Date) myDoc.get("birthday"));
						entity.setCity(myDoc.get("city").toString());
						entity.setEmail(myDoc.get("email").toString());
						entity.setFirstname(myDoc.get("firstname").toString());
						entity.setLastname(myDoc.get("lastname").toString());
						entity.setIndexedCountry(Country.valueOf(myDoc.get("indexedCountry").toString()));
						entity.setNotIndexedCountry(Country.valueOf(myDoc.get("notIndexedCountry").toString()));

						return entity;
				}
				catch (Exception e) {
						LOGGER.error("Method[findById] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return null;
		}

		@Override public Long update(SimpleEntity entity) {
				try {
						BasicDBObject search = new BasicDBObject("_id", entity.getId());

						BasicDBObject doc = new BasicDBObject("_id", entity.getId())
								.append("firstname", entity.getFirstname())
								.append("lastname", entity.getLastname())
								.append("birthday", entity.getBirthday())
								.append("city", entity.getCity())
								.append("email", entity.getEmail())
								.append("indexedCountry", entity.getIndexedCountry().toString())
								.append("notIndexedCountry", entity.getNotIndexedCountry().toString());

						long startTime = System.nanoTime();

						getConnection().update(search, doc);

						return System.nanoTime() - startTime;
				}
				catch (Exception e) {
						LOGGER.error("Method[update] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return 0l;
		}

		@Override public Long deleteById(Long id) {

				try {
						BasicDBObject search = new BasicDBObject("_id", id);

						long startTime = System.nanoTime();

						getConnection().remove(search);

						return System.nanoTime() - startTime;
				}
				catch (Exception e) {
						LOGGER.error("Method[update] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return 0l;
		}

		@Override public Long selectByIndexedCountry(Country country, int offset, int pagesize) {
				try {
						long startTime = System.nanoTime();

						getConnection().find(new BasicDBObject("indexedCountry", country.toString())).skip(offset).limit(pagesize);

						long totalTime = System.nanoTime() - startTime;

						return totalTime;
				}
				catch (Exception e) {
						LOGGER.error("Method[selectByIndexedCountry] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return 0l;
		}

		@Override public Long selectByNonIndexedCountry(Country country, int offset, int pagesize) {
				try {
						long startTime = System.nanoTime();

						getConnection().find(new BasicDBObject("notIndexedCountry", country.toString())).skip(offset).limit(pagesize);

						long totalTime = System.nanoTime() - startTime;

						return totalTime;
				}
				catch (Exception e) {
						LOGGER.error("Method[selectByIndexedCountry] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return 0l;
		}

		@Override public boolean isConnected() {
				return true;
		}

		@Override public void reconnect() {

		}

		@Override public void close() {
				try {
						conn.close();
				}
				catch (Exception e) {
						LOGGER.error("Method[close] Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
		}

		private DBCollection getConnection() {
				if (coll == null) {
						connect();
				}

				return coll;
		}

		private void connect() {
				try {
						conn = new MongoClient(SERVER_IP, SERVER_PORT);
						DB db = conn.getDB("BENCHMARKER");
						coll = db.getCollection("SIMPLE_DAO");

						if (!isIndexCreated) {
								coll.createIndex(new BasicDBObject("indexedCountry", 1));
								isIndexCreated = true;
						}

				}
				catch (Exception e) {
						LOGGER.error("Method[connect] SQL Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
		}
}
