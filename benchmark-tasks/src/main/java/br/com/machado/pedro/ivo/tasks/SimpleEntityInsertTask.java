package br.com.machado.pedro.ivo.tasks;

import br.com.machado.pedro.ivo.beans.enums.OperationType;
import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import br.com.machado.pedro.ivo.entity.factory.EntityFactory;
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
public class SimpleEntityInsertTask implements Command {

		private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEntityInsertTask.class);

		private SimpleEntity entity;
		private static       long          COUNTER   = 0;
		private static final String        TASK_ID   = "SIMPLE_ENTITY_INSERT";
		private static final OperationType operation = OperationType.INSERT;

		public SimpleEntityInsertTask() {
				COUNTER++;
				entity = EntityFactory.createSimpleEntity();
				entity.setId(new Long(COUNTER));
				entity.setBirthday(ContentGenerator.createDate());
				entity.setFirstname(ContentGenerator.createString(20));
				entity.setLastname(ContentGenerator.createString(30));
				entity.setCity(ContentGenerator.createString(10));
				entity.setEmail(ContentGenerator.createString(50));
				entity.setIndexedCountry(ContentGenerator.createCountry(entity.getId()));
				entity.setNotIndexedCountry(ContentGenerator.createCountry(entity.getId()));
		}

		@Override
		public void execute() {
				/**
				 * Will create 1 SimpleEntities
				 *
				 */
				SimpleDAO dao = DAOFactory.getInstance();
				try {
						Long totalTime = dao.save(entity);
						IndexStoreFactory.getIndexStore().store(totalTime, operation, dao.getEngine(), TASK_ID, null);
				} catch (Exception e) {
						LOGGER.error("Method[execute] Unknown Error m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
						IndexStoreFactory.getIndexStore().store(0, OperationType.ERROR, dao.getEngine(), TASK_ID, null);
				} finally {
						DAOFactory.requeue(dao);
				}
		}

		public boolean isEntityOk() {
				return (entity.getId() != null && entity.getBirthday() != null && entity.getCity() != null && entity.getEmail() != null && entity.getFirstname() != null
						&& entity
						.getLastname() != null);
		}

		@Override
		public void run() {
				this.execute();
		}

}
