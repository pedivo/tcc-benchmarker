package br.com.machado.pedro.ivo.tasks;

import org.junit.Test;

public class ExecutionTaskTest {

	@Test
	public void printEntity() {
		Command task = new ExecutionTask();
		task.execute();
	}
}
