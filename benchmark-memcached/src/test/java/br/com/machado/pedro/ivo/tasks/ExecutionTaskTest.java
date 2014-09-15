package br.com.machado.pedro.ivo.tasks;

import br.com.machado.pedro.ivo.dao.SimpleDAOMemcached;
import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import org.junit.Test;

public class ExecutionTaskTest {

		@Test
		public void printEntity() {
				System.setProperty(DAOFactory.SIMPLE_DAO_FOR_NAME, SimpleDAOMemcached.class.getCanonicalName());
				System.setProperty(DAOFactory.DB_CONNECTION_POOL, "75");

				Command task = new ExecutionTask();
				task.execute();
		}
}
