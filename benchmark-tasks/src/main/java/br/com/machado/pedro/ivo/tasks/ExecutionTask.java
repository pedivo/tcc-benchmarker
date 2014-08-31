package br.com.machado.pedro.ivo.tasks;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by pedivo on 30/08/14.
 */
public class ExecutionTask implements Command {

		private Set<Command> tasks;

		public ExecutionTask() {
				tasks = new LinkedHashSet<>();
				tasks.add(new OneMillionInsertsTask());
				tasks.add(new CountByIndexedAttributeTask());
				tasks.add(new CountByNonIndexedAttributeTask());
				tasks.add(new HundredKUpdatesTask());
				tasks.add(new SelectAllWithPaginationByNonIndexedAttributeHandlerTask());
				tasks.add(new SelectAllWithPaginationByIndexedAttributeHandlerTask());
				tasks.add(new SelectAllWithoutPaginationByNonIndexedAttributeHandlerTask());
				tasks.add(new SelectAllWithoutPaginationByIndexedAttributeHandlerTask());
				tasks.add(new OneMillionDeletesTask());
		}

		@Override public void execute() {
				for (Command task : tasks) {
						task.execute();
				}
		}

		@Override public void run() {
				this.execute();
		}
}
