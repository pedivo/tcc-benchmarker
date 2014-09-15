package br.com.machado.pedro.ivo.tasks;

import br.com.machado.pedro.ivo.dao.SimpleDAOMariaDB;
import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import org.junit.Test;

public class OneMillionInsertsTaskTest {

		@Test
		public void printEntity() {
				System.setProperty(DAOFactory.SIMPLE_DAO_FOR_NAME, SimpleDAOMariaDB.class.getCanonicalName());

				Command task = new OneMillionInsertsTask();
				task.execute();
		}
}
