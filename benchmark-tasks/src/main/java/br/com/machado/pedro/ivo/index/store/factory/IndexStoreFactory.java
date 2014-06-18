package br.com.machado.pedro.ivo.index.store.factory;

import br.com.machado.pedro.ivo.index.store.FileIndexStore;
import br.com.machado.pedro.ivo.index.store.IndexStore;


public class IndexStoreFactory {

	public static IndexStore getIndexStore() {
		if (System.getProperty("indexStoreForName") == null) { return new FileIndexStore(); }
		try {
			return (IndexStore) Class.forName(System.getProperty("indexStoreForName")).newInstance();
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			return null;
		}
	}

}
