package br.com.machado.pedro.ivo.index.store;

import br.com.machado.pedro.ivo.beans.enums.OperationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class FileIndexStore implements IndexStore {

		private static final Logger LOGGER = LoggerFactory.getLogger(FileIndexStore.class);

		private static final String           LOG_FORMAT = "[%s] OPERATION - TOTAL_TIME[%d], OPERATION[%s] ENGINE[%s] TASK[%s] METADATA[%s]";
		private              BufferedWriter   bw         = null;
		private              SimpleDateFormat sf         = new SimpleDateFormat("yyyy-MM-dd");

		public void store(long totalTime, OperationType operation, String engine, String taskId, Map<String, Object> metadata) {

				try {
						getWriter().write(String.format(LOG_FORMAT, new Date().toString(), totalTime, operation, engine, taskId, metadata));
						getWriter().newLine();
						getWriter().flush();
				}
				catch (IOException e) {
						LOGGER.error("Method[store] Unknown Error m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
		}

		private BufferedWriter getWriter() {
				if (this.bw == null) {
						try {
								bw = new BufferedWriter(new FileWriter("BENCHMARK " + sf.format(new Date()) + " .log", true));
						}
						catch (IOException e) {
								LOGGER.error("Method[getWriter] Unknown Error m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
						}
				}
				return bw;
		}

}
