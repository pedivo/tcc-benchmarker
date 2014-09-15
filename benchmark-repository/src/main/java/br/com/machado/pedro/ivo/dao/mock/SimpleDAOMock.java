package br.com.machado.pedro.ivo.dao.mock;

import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import br.com.machado.pedro.ivo.entity.beans.generic.Country;
import br.com.machado.pedro.ivo.entity.generic.SimpleEntity;
import br.com.machado.pedro.ivo.entity.mock.SimpleEntityImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleDAOMock extends SimpleDAO {

		@Override
		public Long save(SimpleEntity entity) {
				try { Thread.sleep(10l); } catch (InterruptedException e) {}
				return 10l;
		}

		@Override
		public Long update(SimpleEntity entity) {
				try { Thread.sleep(10l); } catch (InterruptedException e) {}
				return 10l;
		}

		@Override
		public Long deleteById(Long id) {
				try { Thread.sleep(5l); } catch (InterruptedException e) {}
				return 5l;
		}

		@Override
		public String getEngine() {
				return "MOCK";
		}

		@Override
		public Long countByIndexedCountry(Country country) {
				try { Thread.sleep(50l); } catch (InterruptedException e) {}
				super.result = 10000L;
				return 50l;
		}

		@Override
		public Long countByNonIndexedCountry(Country country) {
				try { Thread.sleep(100l); } catch (InterruptedException e) {}
				super.result = 10000L;
				return 100l;
		}

		@Override
		public Long selectByIndexedCountry(Country country) {
				try { Thread.sleep(500l); } catch (InterruptedException e) {}
				return 500l;
		}

		@Override
		public Long selectByNonIndexedCountry(Country country) {
				try { Thread.sleep(550l); } catch (InterruptedException e) {}
				return 550l;
		}

		@Override
		public Long selectByIndexedCountry(Country country, int offset, int pagesize) {
				try { Thread.sleep(30l); } catch (InterruptedException e) {}
				return 30l;
		}

		@Override
		public Long selectByNonIndexedCountry(Country country, int offset, int pagesize) {
				try { Thread.sleep(60l); } catch (InterruptedException e) {}
				return 60l;
		}

		@Override public boolean isConnected() {
				return true;
		}

		@Override public void reconnect() {

		}

		@Override public void close() {

		}

		@Override
		public SimpleEntity findById(Long id) {
				return new SimpleEntityImpl(id);
		}

}
