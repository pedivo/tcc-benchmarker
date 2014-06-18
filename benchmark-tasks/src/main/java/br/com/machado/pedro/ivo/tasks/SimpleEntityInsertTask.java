package br.com.machado.pedro.ivo.tasks;

import br.com.machado.pedro.ivo.beans.enums.OperationType;
import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import br.com.machado.pedro.ivo.entity.factory.EntityFactory;
import br.com.machado.pedro.ivo.entity.generic.SimpleEntity;
import br.com.machado.pedro.ivo.index.store.factory.IndexStoreFactory;
import br.com.machado.pedro.ivo.tasks.util.ContentGenerator;

/**
 * 
 * This task insert a new SimpleEntity
 * 
 * @author Pedro
 * 
 */
public class SimpleEntityInsertTask implements Command {

	private SimpleEntity								entity;
	private static long									COUNTER		= 0;
	private static final String					TASK_ID		= "SIMPLE_ENTITY_INSERT";
	private static final OperationType	operation	= OperationType.INSERT;

	public SimpleEntityInsertTask() {
		COUNTER++;
		entity = EntityFactory.createSimpleEntity();
		entity.setId(new Long(COUNTER));
		entity.setBirthday(ContentGenerator.createDate());
		entity.setFirstname(ContentGenerator.createString(20));
		entity.setLastname(ContentGenerator.createString(30));
		entity.setCity(ContentGenerator.createString(10));
		entity.setEmail(ContentGenerator.createString(50));
	}

	@Override
	public void execute() {
		/**
		 * Will create 1 million SimpleEntities
		 * 
		 */
		Long totalTime = DAOFactory.createSimpleDAO().save(entity);
		IndexStoreFactory.getIndexStore().store(totalTime, operation, DAOFactory.createSimpleDAO().getEngine(), TASK_ID, null);
	}

	public boolean isEntityOk() {
		System.out.println(entity.toString());
		return (entity.getId() != null && entity.getBirthday() != null && entity.getCity() != null && entity.getEmail() != null && entity.getFirstname() != null && entity
				.getLastname() != null);
	}

	@Override
	public void run() {
		this.execute();
	}

}
