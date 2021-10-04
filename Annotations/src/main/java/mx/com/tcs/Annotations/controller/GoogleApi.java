package mx.com.tcs.Annotations.controller;

import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import mx.com.tcs.Annotations.exception.CustomException;

/**
 * The Interface GoogleApi.
 */
@Api(tags = "Google")
public interface GoogleApi {

	/**
	 * Publish message.
	 *
	 * @param message the message
	 * @return the response entity
	 */
	public ResponseEntity<String> publishMessage(String message) throws CustomException;

}
