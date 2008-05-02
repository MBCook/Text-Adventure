package com.text_adventure.exception;

/**
 * An exception to mark that the gamestate must change
 * @author mcook
 */
public abstract class GamestateChangeException extends Exception {
	/**
	 * Setup the message while creating the exception
	 * @param message The message to use
	 */
	public GamestateChangeException(String message) {
		super(message);
	}
}
