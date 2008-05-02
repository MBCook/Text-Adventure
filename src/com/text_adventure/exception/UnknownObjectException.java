package com.text_adventure.exception;

/**
 * An exception to mark we don't know what the thing the user asked for is
 * @author mcook
 */
public class UnknownObjectException extends Exception {
	/**
	 * Setup the message while creating the exception
	 * @param name The name of the object we can't find
	 */
	public UnknownObjectException(String name) {
		super(name);
	}
}
