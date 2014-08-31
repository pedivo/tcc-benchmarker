package br.com.machado.pedro.ivo.tasks;

import org.junit.Test;

public class SelectAllWithPaginationByIndexedAttributeTaskHandlerTest {

	@Test
	public void printEntity() {
		Command task = new SelectAllWithPaginationByIndexedAttributeHandlerTask();
		task.execute();
	}
}
