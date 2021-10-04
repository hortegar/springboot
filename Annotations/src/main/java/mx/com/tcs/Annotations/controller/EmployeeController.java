package mx.com.tcs.Annotations.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import mx.com.tcs.Annotations.business.EmployeeService;
import mx.com.tcs.Annotations.exception.CustomException;
import mx.com.tcs.Annotations.model.AuthenticationRequest;
import mx.com.tcs.Annotations.model.AuthenticationResponse;
import mx.com.tcs.Annotations.model.Employee;
import mx.com.tcs.Annotations.security.ApiUserDetailsService;
import mx.com.tcs.Annotations.utils.JwtUtil;
import mx.com.tcs.Annotations.utils.ProfileEmployee;

/**
 * The Class EmployeeControllerImpl.
 */
@RestController
@RequestMapping("/api")
public class EmployeeController implements EmployeeApi {

	/** The employee service. */
	@Autowired
	private EmployeeService employeeService;

	/** The authentication manager. */
	@Autowired
	private AuthenticationManager authenticationManager;

	/** The api user details service. */
	@Autowired
	private ApiUserDetailsService apiUserDetailsService;

	/** The jwt util. */
	@Autowired
	private JwtUtil jwtUtil;

	/**
	 * Creates the authentication token.
	 *
	 * @param authenticationRequest the authentication request
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
			@RequestBody final AuthenticationRequest authenticationRequest) throws CustomException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect username or password");
		}

		final UserDetails userDetails = apiUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}

	/**
	 * Gets the employee.
	 *
	 * @param id the id
	 * @return the employee
	 */
	@GetMapping("/getEmployee/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable(name = "id") final UUID id) throws CustomException {
		var employee = employeeService.getEmployee(id);
		if (employee != null) {
			return ResponseEntity.ok(employee);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@GetMapping("/getAll")
	public ResponseEntity<List<Employee>> getAll() throws CustomException {
		var employees = employeeService.getAll();
		if (!employees.isEmpty()) {
			return ResponseEntity.ok(employees);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	/**
	 * Gets the by profile.
	 *
	 * @param profile the profile
	 * @return the by profile
	 */
	@GetMapping("/getByProfile")
	public ResponseEntity<List<Employee>> getByProfile(@RequestParam(value = "profile") final ProfileEmployee profile)
			throws CustomException {

		var employees = employeeService.getByProfile(profile);
		if (!employees.isEmpty()) {
			return ResponseEntity.ok(employees);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found");
	}

	/**
	 * Creates the employee.
	 *
	 * @param employee the employee
	 * @return the response entity
	 */
	@PostMapping("/create")
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody final Employee employee, BindingResult result)
			throws CustomException {

		try {
			var response = employeeService.create(employee);
			if (response == null) {
				throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Try again later");
			}
			return ResponseEntity.ok(response);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	/**
	 * Update employee.
	 *
	 * @param employee the employee
	 * @return the response entity
	 */
	@PutMapping("/update")
	public ResponseEntity<Employee> updateEmployee(@RequestBody final Employee employee) throws CustomException {
		try {
			var response = employeeService.update(employee);
			if (response == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
			}
			return ResponseEntity.ok(response);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	/**
	 * Delete employee.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(name = "id") final UUID id) throws CustomException {
		var response = employeeService.delete(id);
		if (response == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found");
		}
		return ResponseEntity.ok(response);
	}

}
