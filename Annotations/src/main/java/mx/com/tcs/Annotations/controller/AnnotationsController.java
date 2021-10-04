package mx.com.tcs.Annotations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mx.com.tcs.Annotations.business.CollectionService;
import mx.com.tcs.Annotations.model.CollectionResponse;
import mx.com.tcs.Annotations.model.RequestFrequency;

//@RestController
//@RequestMapping(value = "/dev")
@Slf4j
public class AnnotationsController {

	@Autowired
	private CollectionService service;

	@GetMapping(value = "/revert")
	public ResponseEntity<CollectionResponse> revertString(@RequestParam(value = "string") String str) {
		CollectionResponse response = new CollectionResponse();
		response.setRevert(service.revert(str));
		log.info("response... " + response);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping(value = "/frequency")
	public ResponseEntity<CollectionResponse> findFrequency(@RequestBody RequestFrequency frequency) {
		CollectionResponse response = new CollectionResponse();
		response.setFinder(service.findFrequency(frequency));
		if (response.getFinder() == 0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
