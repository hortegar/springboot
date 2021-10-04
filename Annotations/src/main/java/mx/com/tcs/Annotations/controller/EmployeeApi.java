package mx.com.tcs.Annotations.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import io.swagger.annotations.Api;
import mx.com.tcs.Annotations.exception.CustomException;
import mx.com.tcs.Annotations.model.AuthenticationRequest;
import mx.com.tcs.Annotations.model.AuthenticationResponse;
import mx.com.tcs.Annotations.model.Employee;
import mx.com.tcs.Annotations.utils.ProfileEmployee;

/**
 * The Interface EmployeeController.
 */
@Api(tags = "Employee")
public interface EmployeeApi {

	/**
	 * Creates the authentication token.
	 *
	 * @param authenticationRequest the authentication request
	 * @return the response entity
	 * @throws Exception the exception
	 */
	public ResponseEntity<AuthenticationResponse> createAuthenticationToken(AuthenticationRequest authenticationRequest)
			throws CustomException;

	/**
	 * Gets the employee.
	 *
	 * @param id the id
	 * @return the employee
	 */
	public ResponseEntity<Employee> getEmployee(UUID id) throws CustomException;

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public ResponseEntity<List<Employee>> getAll() throws CustomException;

	/**
	 * Gets the by profile.
	 *
	 * @param profile the profile
	 * @return the by profile
	 */
	public ResponseEntity<List<Employee>> getByProfile(ProfileEmployee profile) throws CustomException;

	/**
	 * Creates the employee.
	 *
	 * @param employee the employee
	 * @return the response entity
	 */
	public ResponseEntity<Employee> createEmployee(Employee employee, BindingResult result) throws CustomException;

	/**
	 * Update employee.
	 *
	 * @param employee the employee
	 * @return the response entity
	 */
	public ResponseEntity<Employee> updateEmployee(Employee employee) throws CustomException;

	/**
	 * Delete employee.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	public ResponseEntity<Employee> deleteEmployee(UUID id) throws CustomException;

}
