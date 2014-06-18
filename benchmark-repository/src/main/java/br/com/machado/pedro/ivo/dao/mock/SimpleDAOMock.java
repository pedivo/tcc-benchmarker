package br.com.machado.pedro.ivo.dao.mock;

import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import br.com.machado.pedro.ivo.entity.mock.SimpleEntityMock;

public class SimpleDAOMock extends SimpleDAO<SimpleEntityMock> {

	@Override
	public long save(SimpleEntityMock entity) {
		try {
			Thread.sleep(70l);
		}
		catch (InterruptedException e) {

		}
		return 70l;
	}

	@Override
	public String getEngine() {
		return "MOCK";
	}

}
