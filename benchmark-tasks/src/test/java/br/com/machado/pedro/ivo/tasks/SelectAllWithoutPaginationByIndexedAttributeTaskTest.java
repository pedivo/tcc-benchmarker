package br.com.machado.pedro.ivo.tasks;

import org.junit.Test;

public class SelectAllWithoutPaginationByIndexedAttributeTaskTest {

	@Test
	public void printEntity() {
		Command task = new SelectAllWithoutPaginationByIndexedAttributeTask();
		task.execute();
	}
}
