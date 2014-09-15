package br.com.machado.pedro.tasks;

import br.com.machado.pedro.ivo.dao.SimpleDAOMongoDB;
import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import br.com.machado.pedro.ivo.tasks.Command;
import br.com.machado.pedro.ivo.tasks.ExecutionTask;
import org.junit.Test;

public class ExecutionTaskTest {

		@Test
		public void printEntity() {
				System.setProperty(DAOFactory.SIMPLE_DAO_FOR_NAME, SimpleDAOMongoDB.class.getCanonicalName());
				System.setProperty(DAOFactory.DB_CONNECTION_POOL, "75");

				Command task = new ExecutionTask();
				task.execute();
		}
}
