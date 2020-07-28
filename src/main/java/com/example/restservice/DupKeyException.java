package com.example.restservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Entry already exists, duplicate key violation" )
public class DupKeyException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DupKeyException(String message) {
        super(message);
    }
}

