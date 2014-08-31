package br.com.machado.pedro.ivo.tasks;

import org.junit.Test;

public class HundredKUpdatesTaskTest {

	@Test
	public void printEntity() {
		Command task = new HundredKUpdatesTask();
		task.execute();
	}
}
