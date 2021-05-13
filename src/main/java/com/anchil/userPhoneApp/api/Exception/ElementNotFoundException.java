package com.anchil.userPhoneApp.api.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElementNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = -5218143265247846948L;
	
	private static final Logger logger = LoggerFactory.getLogger(ElementNotFoundException.class);

	  public ElementNotFoundException(String message) {
	    super(message);
	    logger.error("ElementNotFoundException -> "+message);
	  }

}
