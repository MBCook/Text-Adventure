package com.text_adventure.exception;

/**
 * An exception to mark that the player just won the game
 * @author mcook
 */
public class PlayerWinException extends GamestateChangeException {
	/**
	 * Setup the message while creating the exception
	 * @param message The message to use
	 */
	public PlayerWinException(String message) {
		super(message);
	}
}
