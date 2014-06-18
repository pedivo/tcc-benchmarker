package br.com.machado.pedro.ivo.entity.factory;

import br.com.machado.pedro.ivo.entity.generic.SimpleEntity;
import br.com.machado.pedro.ivo.entity.mock.SimpleEntityMock;

public class EntityFactory {

	public static SimpleEntity createSimpleEntity() {
		if (System.getProperty("simpleEntityForName") == null) { return new SimpleEntityMock(); }
		try {
			return (SimpleEntity) Class.forName(System.getProperty("simpleEntityForName")).newInstance();
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			return null;
		}
	}
}
