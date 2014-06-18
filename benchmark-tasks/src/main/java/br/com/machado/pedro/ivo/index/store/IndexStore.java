package br.com.machado.pedro.ivo.index.store;

import java.util.Map;
import br.com.machado.pedro.ivo.beans.enums.OperationType;

public interface IndexStore {

	public void store(long totalTime, OperationType operation, String engine, String taskId, Map<String, Object> metadata);
}
