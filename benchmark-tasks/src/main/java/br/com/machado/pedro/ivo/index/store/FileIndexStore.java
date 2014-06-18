package br.com.machado.pedro.ivo.index.store;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import br.com.machado.pedro.ivo.beans.enums.OperationType;

public class FileIndexStore implements IndexStore {

	private static final String	LOG_FORMAT	= "OPERATION - TOTAL_TIME[%d], OPERATION[%s] ENGINE[%s] TASK[%s] METADATA[%s]";
	private BufferedWriter			bw					= null;
	private SimpleDateFormat		sf					= new SimpleDateFormat("yyyy-MM-dd");

	public void store(long totalTime, OperationType operation, String engine, String taskId, Map<String, Object> metadata) {
		try {
			getWriter().write(String.format(LOG_FORMAT, totalTime, operation, engine, taskId, metadata));
			getWriter().newLine();
			getWriter().flush();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private BufferedWriter getWriter() {
		if (this.bw == null) {
			try {
				bw = new BufferedWriter(new FileWriter("BENCHMARK " + sf.format(new Date()) + " .log", true));
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bw;
	}

}
