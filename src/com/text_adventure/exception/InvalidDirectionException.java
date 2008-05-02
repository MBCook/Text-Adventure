package com.text_adventure.exception;

/**
 * An exception to mark someone tried to go in a direction that they can't
 * @author mcook
 */
public class InvalidDirectionException extends InvalidActionException {
	/**
	 * Setup the message while creating the exception
	 * @param message The message to use
	 */
	public InvalidDirectionException(String message) {
		super(message);
	}
}
