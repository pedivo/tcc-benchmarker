package br.com.machado.pedro.ivo.tasks;

import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by pedivo on 30/08/14.
 */
public class ExecutionTask implements Command {

		private Set<Command> tasks;

		public ExecutionTask() {
				initilizePool();
				tasks = new LinkedHashSet<>();
				tasks.add(new OneMillionInsertsTask());
				tasks.add(new CountByIndexedAttributeHandlerTask());
				tasks.add(new CountByNonIndexedAttributeHandlerTask());
				tasks.add(new HundredKUpdatesTask());
				tasks.add(new SelectAllWithPaginationByNonIndexedAttributeHandlerTask());
				tasks.add(new SelectAllWithPaginationByIndexedAttributeHandlerTask());
				tasks.add(new SelectAllWithoutPaginationByNonIndexedAttributeHandlerTask());
				tasks.add(new SelectAllWithoutPaginationByIndexedAttributeHandlerTask());
				tasks.add(new OneMillionDeletesTask());
		}

		private void initilizePool() {
				SimpleDAO dao = DAOFactory.getInstance();
				DAOFactory.requeue(dao);
		}

		private void cleanPool() {
				DAOFactory.close();
		}

		@Override public void execute() {
				for (Command task : tasks) {
						//initilizePool();
						task.execute();
						//cleanPool();
				}
		}

		@Override public void run() {
				this.execute();
		}
}
