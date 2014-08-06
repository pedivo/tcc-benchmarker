package br.com.machado.pedro.ivo.tasks;

import org.junit.Test;

public class SelectAllWithoutPaginationByNonIndexedAttributeTaskTest {

	@Test
	public void printEntity() {
		Command task = new SelectAllWithoutPaginationByNonIndexedAttributeTask();
		task.execute();
	}
}
