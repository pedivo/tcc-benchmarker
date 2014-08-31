package br.com.machado.pedro.ivo.tasks;

import org.junit.Test;

public class SelectAllWithPaginationByNonIndexedAttributeTaskHandlerTest {

	@Test
	public void printEntity() {
		Command task = new SelectAllWithPaginationByNonIndexedAttributeHandlerTask();
		task.execute();
	}
}
