package br.com.machado.pedro.ivo.tasks;

import org.junit.Test;

public class CountByIndexedAttributeTaskTest {

	@Test
	public void printEntity() {
		Command task = new CountByIndexedAttributeTask();
		task.execute();
	}
}
