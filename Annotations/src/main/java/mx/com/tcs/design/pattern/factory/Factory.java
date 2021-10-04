package mx.com.tcs.design.pattern.factory;

import lombok.Getter;

/**
 * The Class Factory.
 */
public class Factory {

	/**
	 * A factory for creating Person objects.
	 */
	public static class PersonFactory {

		/**
		 * Gets the person.
		 *
		 * @param name   the name
		 * @param gender the gender
		 * @return the person
		 */
		public static Person getPerson(String name, String gender) {
			if (gender.equalsIgnoreCase("M")) {
				return new Male(name);
			} else if (gender.equals("F")) {
				return new Female(name);
			}
			return null;
		}
	}

}

@Getter
abstract class Person {

	private String name;

	Person(String name) {
		this.name = name;
	}
}

class Male extends Person {

	Male(String name) {
		super(name);
	}
}

class Female extends Person {

	Female(String name) {
		super(name);
	}
}
