package br.com.machado.pedro.ivo.dao.generic;

import br.com.machado.pedro.ivo.entity.beans.generic.Country;
import br.com.machado.pedro.ivo.entity.generic.SimpleEntity;

public abstract class SimpleDAO {

		protected Object result;

		public abstract Long save(SimpleEntity entity);

		public abstract String getEngine();

		public abstract Long countByIndexedCountry(Country country);

		public abstract Long countByNonIndexedCountry(Country country);

		public abstract Long selectByIndexedCountry(Country country);

		public abstract Long selectByNonIndexedCountry(Country country);

		public abstract SimpleEntity findById(Long id);

		public abstract Long update(SimpleEntity entity);

		public abstract Long deleteById(Long id);

		public abstract Long selectByIndexedCountry(Country country, int offset, int pagesize);

		public abstract Long selectByNonIndexedCountry(Country country, int offset, int pagesize);

		public Object getResult() {
				return result;
		}

		public abstract boolean isConnected();

		public abstract void reconnect();

		public abstract void close();

		public boolean isNonIndexQueriesSupported() {
				return true;
		}

		public boolean isIndexQueriesSupported() {
				return true;
		}
}
