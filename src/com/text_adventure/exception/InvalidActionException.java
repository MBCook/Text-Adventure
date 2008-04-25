package com.text_adventure.exception;

/**
 * An exception to mark that something illegal tried to happen
 * @author mcook
 */
public class InvalidActionException extends Exception {
	/**
	 * Setup the message while creating the exception
	 * @param message The message to use
	 */
	public InvalidActionException(String message) {
		super(message);
	}
}
