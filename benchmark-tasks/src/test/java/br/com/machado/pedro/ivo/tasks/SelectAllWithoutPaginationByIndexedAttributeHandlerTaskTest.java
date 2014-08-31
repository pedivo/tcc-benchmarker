package br.com.machado.pedro.ivo.tasks;

import org.junit.Test;

public class SelectAllWithoutPaginationByIndexedAttributeHandlerTaskTest {

	@Test
	public void printEntity() {
		Command task = new SelectAllWithoutPaginationByIndexedAttributeHandlerTask();
		task.execute();
	}
}
