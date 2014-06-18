package br.com.machado.pedro.ivo.dao.mock;

import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import br.com.machado.pedro.ivo.entity.beans.generic.Country;
import br.com.machado.pedro.ivo.entity.mock.SimpleEntityMock;

public class SimpleDAOMock extends SimpleDAO<SimpleEntityMock> {

	@Override
	public long save(SimpleEntityMock entity) {
		try { Thread.sleep(70l); } catch (InterruptedException e) {}
		return 70l;
	}

	@Override
	public String getEngine() {
		return "MOCK";
	}

	@Override
	public Long countByIndexedCountry(Country country) {
		try { Thread.sleep(100l); } catch (InterruptedException e) {}
		return 100l;
	}

	@Override
	public Long countByNonIndexedCountry(Country country) {
		try { Thread.sleep(200l); } catch (InterruptedException e) {}
		return 200l;
	}

	@Override
	public Long selectByIndexedCountry(Country country) {
		try { Thread.sleep(1000l); } catch (InterruptedException e) {}
		return 1000l;
	}

	@Override
	public Long selectByNonIndexedCountry(Country country) {
		try { Thread.sleep(2000l); } catch (InterruptedException e) {}
		return 2000l;
	}

}
