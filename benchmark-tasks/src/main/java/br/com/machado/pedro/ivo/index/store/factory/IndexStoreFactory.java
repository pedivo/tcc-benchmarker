package br.com.machado.pedro.ivo.index.store.factory;

import br.com.machado.pedro.ivo.index.store.FileIndexStore;
import br.com.machado.pedro.ivo.index.store.IndexStore;

public class IndexStoreFactory {

		private static IndexStore engine;

		public static IndexStore getIndexStore() {
				if (engine == null) {
						if (System.getProperty("indexStoreForName") == null) { engine = new FileIndexStore(); return engine; }
						try {
								engine = (IndexStore) Class.forName(System.getProperty("indexStoreForName")).newInstance();
						}
						catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
								return null;
						}
				}
				return engine;
		}

}
