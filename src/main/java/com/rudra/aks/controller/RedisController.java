package com.rudra.aks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rudra.aks.listener.UserMessageListener;
import com.rudra.aks.publisher.MessagePublisher;

@RestController
@RequestMapping(path="/redis")
public class RedisController {
	
	@Autowired
	MessagePublisher	publisher;
	
	@Autowired
	UserMessageListener	listener;
	
	@RequestMapping(path = "/add/{message}", method = RequestMethod.GET )
	public ResponseEntity<?> 	addMessageToQueue(@PathVariable(required=true) String message) {
		try {
			publisher.publishMessage(message);
			return new ResponseEntity<String>("message published", HttpStatus.OK);
		} catch( Exception e) { }
		return new ResponseEntity<String>("Message not published", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(path = "/get", method = RequestMethod.GET )
	public ResponseEntity<?> 	getMessageList() {
		try {
			String messages = listener.messageList();
			return new ResponseEntity<>(messages, HttpStatus.OK);
		} catch( Exception e) { }
		return new ResponseEntity<String>("Message not published", HttpStatus.BAD_REQUEST);
	}
}
