package br.com.machado.pedro.ivo.tasks;

import br.com.machado.pedro.ivo.dao.SimpleDAOMySql;
import org.junit.Test;

public class ExecutionTaskTest {

		@Test
		public void printEntity() {
				System.setProperty(DAOFactory.SIMPLE_DAO_FOR_NAME, SimpleDAOMySql.class.getCanonicalName());

				Command task = new ExecutionTask();
				task.execute();
		}
}
