package br.com.machado.pedro.ivo.tasks;

import org.junit.Test;

public class SelectAllWithoutPaginationByNonIndexedAttributeHandlerTaskTest {

	@Test
	public void printEntity() {
		Command task = new SelectAllWithoutPaginationByNonIndexedAttributeHandlerTask();
		task.execute();
	}
}
