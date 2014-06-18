package br.com.machado.pedro.ivo.index.store;

import java.util.Map;
import br.com.machado.pedro.ivo.beans.enums.OperationType;

public class ConsoleIndexStore implements IndexStore {

	private static final String	LOG_FORMAT	= "OPERATION - TOTAL_TIME[%d], OPERATION[%s] ENGINE[%s] TASK[%s] METADATA[%s]";

	public void store(long totalTime, OperationType operation, String engine, String taskId, Map<String, Object> metadata) {
		System.out.println(String.format(LOG_FORMAT, totalTime, operation, engine, taskId, metadata));
	}

}
