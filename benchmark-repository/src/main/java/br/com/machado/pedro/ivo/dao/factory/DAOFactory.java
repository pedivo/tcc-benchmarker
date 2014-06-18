package br.com.machado.pedro.ivo.dao.factory;

import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import br.com.machado.pedro.ivo.dao.mock.SimpleDAOMock;

public class DAOFactory {

	public static SimpleDAO createSimpleDAO() {
		if (System.getProperty("simpleDAOForName") == null) { return new SimpleDAOMock(); }
		try {
			return (SimpleDAO) Class.forName(System.getProperty("simpleDAOForName")).newInstance();
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			return null;
		}
	}
}
