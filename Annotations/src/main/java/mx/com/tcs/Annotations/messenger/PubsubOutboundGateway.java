package mx.com.tcs.Annotations.messenger;

import org.springframework.integration.annotation.MessagingGateway;

/**
 * The Interface PubsubOutboundGateway.
 */
@MessagingGateway(defaultRequestChannel = "outputChannel")
public interface PubsubOutboundGateway {
	
	/**
	 * Send to pub sub.
	 *
	 * @param text the text
	 */
	void sendToPubSub(String text);
}
