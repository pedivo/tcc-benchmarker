package br.com.machado.pedro.ivo.tasks;

import org.junit.Test;

public class CountByNonIndexedAttributeHandlerTaskTest {

	@Test
	public void printEntity() {
		Command task = new CountByNonIndexedAttributeHandlerTask();
		task.execute();
	}
}
