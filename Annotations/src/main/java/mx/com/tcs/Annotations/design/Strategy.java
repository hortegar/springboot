package mx.com.tcs.Annotations.design;

import mx.com.tcs.Annotations.model.Employee;

/**
 * The Interface Strategy.
 */
@FunctionalInterface
public interface Strategy {

	/**
	 * Sets the up profile.
	 *
	 * @param employee the new up profile
	 */
	void setupProfile(Employee employee);

}
