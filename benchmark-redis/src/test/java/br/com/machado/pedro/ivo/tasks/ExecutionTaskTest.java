package br.com.machado.pedro.ivo.tasks;

import br.com.machado.pedro.ivo.SimpleDAORedis;
import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import org.junit.Test;

public class ExecutionTaskTest {

		@Test
		public void printEntity() {
				System.setProperty(DAOFactory.SIMPLE_DAO_FOR_NAME, SimpleDAORedis.class.getCanonicalName());
				System.setProperty(DAOFactory.DB_CONNECTION_POOL, "100");

				Command task = new ExecutionTask();
				task.execute();
		}
}
