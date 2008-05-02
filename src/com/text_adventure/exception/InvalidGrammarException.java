package com.text_adventure.exception;

/**
 * An exception to mark that something the user intered makes no sense
 * @author mcook
 */
public class InvalidGrammarException extends Exception {
	/**
	 * Setup the message while creating the exception
	 * @param message The message to use
	 */
	public InvalidGrammarException(String message) {
		super(message);
	}
}
