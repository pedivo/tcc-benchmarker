package br.com.machado.pedro.ivo.index.store;

import java.util.Map;
import br.com.machado.pedro.ivo.beans.enums.OperationType;
import br.com.machado.pedro.ivo.beans.exceptions.ValidationException;
import br.com.machado.pedro.ivo.client.IndexClient;
import br.com.machado.pedro.ivo.jaxrs.IndexRestClient;

public class RESTIndexStore implements IndexStore {

	private static final String	LOG_FORMAT	= "TOTAL_TIME[%d], OPERATION[%s] ENGINE[%s] TASK[%s] METADATA[%s]";

	public void store(long totalTime, OperationType operation, String engine, String taskId, Map<String, Object> metadata) {
		IndexClient client = new IndexRestClient();
		try {
			client.add(totalTime, operation, engine, taskId, metadata);
		}
		catch (ValidationException e) {
			System.out.println(String.format(LOG_FORMAT, totalTime, operation, engine, taskId, metadata));
		}
		catch (Exception e) {
			System.out.println(String.format(LOG_FORMAT, totalTime, operation, engine, taskId, metadata));
		}
	}

}
