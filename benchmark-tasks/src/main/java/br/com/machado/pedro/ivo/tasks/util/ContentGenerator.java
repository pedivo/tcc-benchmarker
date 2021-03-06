package br.com.machado.pedro.ivo.tasks.util;

import br.com.machado.pedro.ivo.entity.beans.generic.Country;

import java.util.Date;
import java.util.Random;

public class ContentGenerator {

		private static final Country[] countries = Country.values();

		/**
		 * Create a new fake Date
		 *
		 * @return java.util.DAte
		 */
		public static Date createDate() {
				Random r = new Random();
				long t1 = System.currentTimeMillis() + r.nextInt();
				long t2 = t1 + 2 * 60 * 1000 + r.nextInt(60 * 1000) + 1;
				return new Date(t2);
		}

		/**
		 * Create a new fake String with a specific length
		 *
		 * @return String.class
		 */
		public static String createString(int length) {
				StringBuffer buffer = new StringBuffer();
				String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@";

				int charactersLength = characters.length();

				for (int i = 0; i < length; i++) {
						double index = Math.random() * charactersLength;
						buffer.append(characters.charAt((int) index));
				}
				return buffer.toString();
		}

		/**
		 * Create a new fake Country
		 *
		 * @return String.class
		 */
		public static Country createCountry(Long index) {
				if (index >= countries.length) {
						Long nIndex = index % countries.length;
						return createCountry(nIndex);
				}
				return countries[index.intValue()];
		}
}
