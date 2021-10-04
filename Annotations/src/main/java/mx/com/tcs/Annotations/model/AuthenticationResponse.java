package mx.com.tcs.Annotations.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Gets the jwt.
 *
 * @return the jwt
 */
@Getter

/**
 * Instantiates a new authentication response.
 *
 * @param jwt the jwt
 */
@AllArgsConstructor
public class AuthenticationResponse {

	/** The jwt. */
	private String jwt;

}
