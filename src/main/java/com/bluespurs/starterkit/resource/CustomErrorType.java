package com.bluespurs.starterkit.resource;

/**
 * Represents custom error with a Status and a Message.
 */
public class CustomErrorType {

	private int status;
	private String message;
	
	/**
	 * Construct an error type from provided status and message.
	 * @param status: An integer representing HTTP status code corresponding to the error.
	 * @param message: A String containing error message.
	 */
	public CustomErrorType(int status, String message) {
		this.status = status;
		this.message = message;
	}

	/**
	 * Gets the status of the error. Corresponds to the HTTP status code.
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Gets the message of the error.
	 */
	public String getMessage() {
		return message;
	}

}
