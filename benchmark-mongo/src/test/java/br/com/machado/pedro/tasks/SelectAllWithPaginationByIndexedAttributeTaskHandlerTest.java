package br.com.machado.pedro.tasks;

import br.com.machado.pedro.ivo.dao.SimpleDAOMongoDB;
import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import br.com.machado.pedro.ivo.tasks.Command;
import br.com.machado.pedro.ivo.tasks.SelectAllWithPaginationByIndexedAttributeHandlerTask;
import org.junit.Test;

public class SelectAllWithPaginationByIndexedAttributeTaskHandlerTest {

		@Test
		public void printEntity() {
				System.setProperty(DAOFactory.SIMPLE_DAO_FOR_NAME, SimpleDAOMongoDB.class.getCanonicalName());

				//Initialize
				SimpleDAO dao = DAOFactory.getInstance();
				DAOFactory.requeue(dao);

				Command task = new SelectAllWithPaginationByIndexedAttributeHandlerTask();
				task.execute();
		}
}
