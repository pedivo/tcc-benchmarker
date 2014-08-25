package br.com.machado.pedro.ivo.dao.mock;

import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import br.com.machado.pedro.ivo.entity.beans.generic.Country;
import br.com.machado.pedro.ivo.entity.mock.SimpleEntityMock;

public class SimpleDAOMock extends SimpleDAO<SimpleEntityMock> {

		@Override
		public Long save(SimpleEntityMock entity) {
				try { Thread.sleep(70l); } catch (InterruptedException e) {}
				return 70l;
		}

		@Override
		public Long update(SimpleEntityMock entity) {
				try { Thread.sleep(70l); } catch (InterruptedException e) {}
				return 70l;
		}

		@Override
		public Long deleteById(Long id) {
				try { Thread.sleep(30l); } catch (InterruptedException e) {}
				return 30l;
		}

		@Override
		public String getEngine() {
				return "MOCK";
		}

		@Override
		public Long countByIndexedCountry(Country country) {
				try { Thread.sleep(100l); } catch (InterruptedException e) {}
				super.result = 10000L;
				return 100l;
		}

		@Override
		public Long countByNonIndexedCountry(Country country) {
				try { Thread.sleep(200l); } catch (InterruptedException e) {}
				super.result = 10000L;
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

		@Override
		public Long selectByIndexedCountry(Country country, int offset, int pagesize) {
				try { Thread.sleep(200l); } catch (InterruptedException e) {}
				return 200l;
		}

		@Override
		public Long selectByNonIndexedCountry(Country country, int offset, int pagesize) {
				try { Thread.sleep(400l); } catch (InterruptedException e) {}
				return 400l;
		}

		@Override
		public SimpleEntityMock findById(Long id) {
				return new SimpleEntityMock(id);
		}

}
