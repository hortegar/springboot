package mx.com.tcs.Annotations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.tcs.Annotations.exception.CustomException;
import mx.com.tcs.Annotations.messenger.PubsubOutboundGateway;
import mx.com.tcs.Annotations.utils.Constants;

/**
 * The Class GoogleController.
 */
@RestController
@RequestMapping("/gcp")
public class GoogleController implements GoogleApi {

	/** The pubsub. */
	@Autowired
	private PubsubOutboundGateway pubsub;

	/**
	 * Publish message.
	 *
	 * @param message the message
	 * @return the response entity
	 */
	@PostMapping("/publishMessage")
	public ResponseEntity<String> publishMessage(@RequestParam("message") String message) throws CustomException {
		pubsub.sendToPubSub(message);
		return new ResponseEntity<String>(Constants.PUB_SUB, HttpStatus.OK);
	}
	
}
