package com.text_adventure.exception;

/**
 * An exception to mark that the player just killed themselves
 * @author mcook
 */
public class PlayerDeathException extends GamestateChangeException {
	/**
	 * Setup the message while creating the exception
	 * @param message The message to use
	 */
	public PlayerDeathException(String message) {
		super(message);
	}
}
