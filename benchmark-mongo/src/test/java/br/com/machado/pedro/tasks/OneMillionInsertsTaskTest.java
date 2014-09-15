package br.com.machado.pedro.tasks;

import br.com.machado.pedro.ivo.dao.SimpleDAOMongoDB;
import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import br.com.machado.pedro.ivo.tasks.Command;
import br.com.machado.pedro.ivo.tasks.OneMillionInsertsTask;
import org.junit.Test;

public class OneMillionInsertsTaskTest {

		@Test
		public void printEntity() {
				System.setProperty(DAOFactory.SIMPLE_DAO_FOR_NAME, SimpleDAOMongoDB.class.getCanonicalName());

				Command task = new OneMillionInsertsTask();
				task.execute();
		}
}
