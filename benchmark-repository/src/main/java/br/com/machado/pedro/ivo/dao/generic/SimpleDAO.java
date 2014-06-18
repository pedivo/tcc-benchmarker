package br.com.machado.pedro.ivo.dao.generic;

import br.com.machado.pedro.ivo.entity.generic.SimpleEntity;

public abstract class SimpleDAO<T extends SimpleEntity> {

	public abstract long save(T entity);

	public abstract String getEngine();

}
