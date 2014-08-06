package br.com.machado.pedro.ivo.dao.generic;

import br.com.machado.pedro.ivo.entity.beans.generic.Country;
import br.com.machado.pedro.ivo.entity.generic.SimpleEntity;

public abstract class SimpleDAO<T extends SimpleEntity> {

	public abstract Long save(T entity);

	public abstract String getEngine();

	public abstract Long countByIndexedCountry(Country country);

	public abstract Long countByNonIndexedCountry(Country country);

	public abstract Long selectByIndexedCountry(Country country);

	public abstract Long selectByNonIndexedCountry(Country country);

	public abstract T findById(Long id);

	public abstract Long update(T entity);

	public abstract Long deleteById(Long id);

	public abstract Long selectByIndexedCountry(Country country, int offset, int pagesize);

	public abstract Long selectByNonIndexedCountry(Country country, int offset, int pagesize);

}
