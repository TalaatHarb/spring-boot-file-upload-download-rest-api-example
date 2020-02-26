package net.talaatharb.filedemo.exception;

/**
 * Exception class for problems with file storage
 * 
 * @author mharb
 *
 */
public class FileStorageException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Public constructor
	 * 
	 * @param message
	 */
	public FileStorageException(String message) {
		super(message);
	}

	/**
	 * Public constructor with nested cause
	 * 
	 * @param message
	 * @param cause
	 */
	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
