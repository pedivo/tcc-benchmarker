package br.com.machado.pedro.ivo.tasks;

import br.com.machado.pedro.ivo.dao.SimpleDAOMySql;
import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import org.junit.Test;

public class SelectAllWithoutPaginationByIndexedAttributeHandlerTaskTest {

		@Test
		public void printEntity() {
				System.setProperty(DAOFactory.SIMPLE_DAO_FOR_NAME, SimpleDAOMySql.class.getCanonicalName());

				//Initialize
				SimpleDAO dao = DAOFactory.getInstance();
				DAOFactory.requeue(dao);
				Command task = new SelectAllWithoutPaginationByIndexedAttributeHandlerTask();
				task.execute();
		}
}
