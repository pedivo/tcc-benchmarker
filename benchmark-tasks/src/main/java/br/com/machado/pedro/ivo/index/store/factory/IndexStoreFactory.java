package br.com.machado.pedro.ivo.index.store.factory;

import br.com.machado.pedro.ivo.index.store.FileIndexStore;
import br.com.machado.pedro.ivo.index.store.IndexStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndexStoreFactory {

		private static final Logger LOGGER = LoggerFactory.getLogger(IndexStoreFactory.class);

		private static IndexStore engine;

		public static IndexStore getIndexStore() {
				if (engine == null) {
						if (System.getProperty("indexStoreForName") == null) {
								engine = new FileIndexStore();
								return engine;
						}
						try {
								engine = (IndexStore) Class.forName(System.getProperty("indexStoreForName")).newInstance();
						}
						catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
								LOGGER.error("Method[getIndexStore] Unknown Error m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
								return null;
						}
				}
				return engine;
		}

}
