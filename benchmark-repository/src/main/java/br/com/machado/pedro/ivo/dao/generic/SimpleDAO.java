package br.com.machado.pedro.ivo.dao.generic;

import br.com.machado.pedro.ivo.entity.beans.generic.Country;
import br.com.machado.pedro.ivo.entity.generic.SimpleEntity;

public abstract class SimpleDAO<T extends SimpleEntity> {

	public abstract long save(T entity);

	public abstract String getEngine();

	public abstract Long countByIndexedCountry(Country country);

	public abstract Long countByNonIndexedCountry(Country country);

	public abstract Long selectByIndexedCountry(Country country);

	public abstract Long selectByNonIndexedCountry(Country country);

}
