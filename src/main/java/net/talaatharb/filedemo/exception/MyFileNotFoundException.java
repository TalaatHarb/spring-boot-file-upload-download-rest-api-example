package net.talaatharb.filedemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * File not found exception
 * 
 * @author mharb
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Public constructor
	 * 
	 * @param message
	 */
	public MyFileNotFoundException(String message) {
		super(message);
	}

	/**
	 * Public constructor with nested exception
	 * 
	 * @param message
	 * @param cause
	 */
	public MyFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
