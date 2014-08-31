package br.com.machado.pedro.ivo.tasks;

import org.junit.Test;

public class OneMillionDeletesTaskTest {

	@Test
	public void printEntity() {
		Command task = new OneMillionDeletesTask();
		task.execute();
	}
}
