package br.com.machado.pedro.ivo.entity.factory;

import br.com.machado.pedro.ivo.entity.generic.SimpleEntity;
import br.com.machado.pedro.ivo.entity.mock.SimpleEntityImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityFactory {

		private static final Logger LOGGER = LoggerFactory.getLogger(EntityFactory.class);

		public static SimpleEntity createSimpleEntity() {
				if (System.getProperty("simpleEntityForName") == null) { return new SimpleEntityImpl(); }
				try {
						return (SimpleEntity) Class.forName(System.getProperty("simpleEntityForName")).newInstance();
				}
				catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
						LOGGER.error("Method[createSimpleEntity] Unknown Error m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
						return null;
				}
		}
}
