package br.com.machado.pedro.ivo.tasks;

import br.com.machado.pedro.ivo.beans.enums.OperationType;
import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import br.com.machado.pedro.ivo.entity.generic.SimpleEntity;
import br.com.machado.pedro.ivo.index.store.factory.IndexStoreFactory;
import br.com.machado.pedro.ivo.tasks.util.ContentGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This task insert a new SimpleEntity
 *
 * @author Pedro
 */
public class SimpleEntityUpdateTask implements Command {

		private SimpleEntity entity;
		private static final String        TASK_ID   = "SIMPLE_ENTITY_UPDATE";
		private static final OperationType operation = OperationType.UPDATE;
		private static final Logger        LOGGER    = LoggerFactory.getLogger(SimpleEntityUpdateTask.class);

		public SimpleEntityUpdateTask(Long id) {
				try {
						SimpleDAO dao = DAOFactory.getInstance();
						entity = dao.findById(id);
						entity.setId(id);
						entity.setBirthday(ContentGenerator.createDate());
						entity.setFirstname(ContentGenerator.createString(20));
						entity.setLastname(ContentGenerator.createString(30));
						entity.setCity(ContentGenerator.createString(10));
						entity.setEmail(ContentGenerator.createString(50));
						entity.setIndexedCountry(ContentGenerator.createCountry(entity.getId()));
						entity.setNotIndexedCountry(ContentGenerator.createCountry(entity.getId()));

						DAOFactory.requeue(dao);
				}
				catch (Exception e) {
						LOGGER.error("Method[] Exception m[{}] stack[{}] id[{}]", e.getMessage(), e.getStackTrace(), id);
				}
		}

		@Override
		public void execute() {
				/**
				 * Will update a SimpleEntity
				 *
				 */
				SimpleDAO dao = DAOFactory.getInstance();
				Long totalTime = dao.update(entity);

				IndexStoreFactory.getIndexStore().store(totalTime, operation, dao.getEngine(), TASK_ID, null);

				DAOFactory.requeue(dao);
		}

		public boolean isEntityOk() {
				System.out.println(entity.toString());
				return (entity.getId() != null && entity.getBirthday() != null && entity.getCity() != null && entity.getEmail() != null && entity.getFirstname() != null
						&& entity
						.getLastname() != null);
		}

		@Override
		public void run() {
				this.execute();
		}

}
