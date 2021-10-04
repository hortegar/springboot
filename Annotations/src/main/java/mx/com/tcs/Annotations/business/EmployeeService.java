package mx.com.tcs.Annotations.business;

import java.util.List;
import java.util.UUID;

import mx.com.tcs.Annotations.model.Employee;
import mx.com.tcs.Annotations.utils.ProfileEmployee;

/**
 * The Interface EmployeeService.
 */
public interface EmployeeService {

	/**
	 * Gets the employee.
	 *
	 * @param Id the id
	 * @return the employee
	 */
	Employee getEmployee(UUID Id);

	/**
	 * Gets the by profile.
	 *
	 * @param profile the profile
	 * @return the by profile
	 */
	List<Employee> getByProfile(ProfileEmployee profile);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<Employee> getAll();

	/**
	 * Creates the.
	 *
	 * @param employee the employee
	 * @return the employee
	 */
	Employee create(Employee employee);

	/**
	 * Update.
	 *
	 * @param employee the employee
	 * @return the employee
	 */
	Employee update(Employee employee);

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the employee
	 */
	Employee delete(UUID id);
}
