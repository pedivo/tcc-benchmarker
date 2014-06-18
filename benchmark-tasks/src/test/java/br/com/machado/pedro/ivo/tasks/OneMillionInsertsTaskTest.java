package br.com.machado.pedro.ivo.tasks;

import org.junit.Test;

public class OneMillionInsertsTaskTest {

	@Test
	public void printEntity() {
		Command task = new OneMillionInsertsTask();
		task.execute();
	}
}
