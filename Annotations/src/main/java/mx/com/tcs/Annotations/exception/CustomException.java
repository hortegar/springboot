package mx.com.tcs.Annotations.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * Gets the params.
 *
 * @return the params
 */
@Getter
public class CustomException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The response code. */
	private final HttpStatus responseCode;

	/** The errors. */
	private final List<String> errors;

	/** The params. */
	private final transient Object params;

	/**
	 * Instantiates a new custom exception.
	 *
	 * @param message      the message
	 * @param responseCode the response code
	 */
	public CustomException(final String message, final HttpStatus responseCode) {
		super(message);
		this.responseCode = responseCode;
		this.errors = null;
		this.params = null;
	}

	/**
	 * Instantiates a new custom exception.
	 *
	 * @param message      the message
	 * @param responseCode the response code
	 * @param params       the params
	 */
	public CustomException(final String message, final HttpStatus responseCode, final Object params) {
		super(message);
		this.responseCode = responseCode;
		this.errors = null;
		this.params = params;
	}

}
