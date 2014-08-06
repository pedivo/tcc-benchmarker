package br.com.machado.pedro.ivo.tasks;

import org.junit.Test;

public class CountByNonIndexedAttributeTaskTest {

	@Test
	public void printEntity() {
		Command task = new CountByNonIndexedAttributeTask();
		task.execute();
	}
}
