package br.com.machado.pedro.ivo.tasks;

import org.junit.Test;

public class CountByIndexedAttributeHandlerTaskTest {

	@Test
	public void printEntity() {
		Command task = new CountByIndexedAttributeHandlerTask();
		task.execute();
	}
}
