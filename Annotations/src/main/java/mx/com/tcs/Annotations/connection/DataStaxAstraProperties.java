package mx.com.tcs.Annotations.connection;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;

/**
 * The Class DataStaxAstraProperties.
 */
@ConfigurationProperties(prefix = "datastax.astra")

/**
 * Gets the client secret.
 *
 * @return the client secret
 */
@Getter
public class DataStaxAstraProperties {

	/** The secure connect bundle. */
	private File secureConnectBundle;

	/** The client id. */
	@Value("${client-id}")
	private String CLIENT_ID;

	/** The client secret. */
	@Value("${client-secret}")
	private String CLIENT_SECRET;

	/**
	 * Sets the secure connect bundle.
	 *
	 * @param secureConnectBundle the new secure connect bundle
	 */
	public void setSecureConnectBundle(File secureConnectBundle) {
		this.secureConnectBundle = secureConnectBundle;
	}

}
