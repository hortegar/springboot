package mx.com.tcs.Annotations.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data

/**
 * Instantiates a new authentication request.
 *
 * @param username the username
 * @param password the password
 */
@AllArgsConstructor

/**
 * Instantiates a new authentication request.
 */
@NoArgsConstructor
public class AuthenticationRequest {

	/** The username. */
	private String username;
	
	/** The password. */
	private String password;

}
