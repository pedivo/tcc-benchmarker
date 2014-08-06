package br.com.machado.pedro.ivo.tasks;

import java.util.HashMap;
import java.util.Map;
import br.com.machado.pedro.ivo.beans.enums.OperationType;
import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import br.com.machado.pedro.ivo.entity.beans.generic.Country;
import br.com.machado.pedro.ivo.index.store.factory.IndexStoreFactory;

/**
 * 
 * That task will just do a simple count query by a indexed field
 * 
 * @author Pedro
 * 
 */
public class SelectByCountryWithPaginationByNonIndexedAttributeTask implements Command {

	private static final String					TASK_ID		= "SELECT_ALL_WITH_PAGINATION_BY_NON_INDEXED_ATTIRBUTE";
	private static final OperationType	operation	= OperationType.SELECT;
	private static final int						pagesize	= 100;
	private Long												total;
	private Country											country;
	private Map<String, Object>					metadata	= new HashMap<>();

	public SelectByCountryWithPaginationByNonIndexedAttributeTask(Long total, Country country) {
		this.total = total;
		this.country = country;
		metadata.put("country", country.toString());
		metadata.put("pagesize", pagesize);
	}

	@Override
	public void execute() {
		int offset = 0;
		while (offset <= total) {
			Long totalTime = DAOFactory.createSimpleDAO().selectByNonIndexedCountry(country, offset, pagesize);
			
			metadata.put("offset", offset);
			
			IndexStoreFactory.getIndexStore().store(totalTime, operation, DAOFactory.createSimpleDAO().getEngine(), TASK_ID, metadata);
			offset = offset + pagesize;
		}
	}

	@Override
	public void run() {
		this.execute();
	}

}