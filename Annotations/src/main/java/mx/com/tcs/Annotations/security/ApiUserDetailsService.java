package mx.com.tcs.Annotations.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The Class ApiUserDetailsService.
 */
@Service
public class ApiUserDetailsService implements UserDetailsService {

	/** The user. */
	@Value("${tcs.api.security.user}")
	private String USER;

	/** The password. */
	@Value("${tcs.api.security.password}")
	private String PASSWORD;

	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User(USER, PASSWORD, new ArrayList<>());
	}

}
