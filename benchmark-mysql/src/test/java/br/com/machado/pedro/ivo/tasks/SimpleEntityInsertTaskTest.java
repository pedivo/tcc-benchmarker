package br.com.machado.pedro.ivo.tasks;

import org.junit.Assert;
import org.junit.Test;

public class SimpleEntityInsertTaskTest {

		@Test
		public void printEntity() {
				SimpleEntityInsertTask task = new SimpleEntityInsertTask();
				Assert.assertTrue(task.isEntityOk());

				task = new SimpleEntityInsertTask();
				Assert.assertTrue(task.isEntityOk());

				task = new SimpleEntityInsertTask();
				Assert.assertTrue(task.isEntityOk());

				task = new SimpleEntityInsertTask();
				Assert.assertTrue(task.isEntityOk());

				task = new SimpleEntityInsertTask();
				Assert.assertTrue(task.isEntityOk());
		}
}
