package com.text_adventure.parser.verbs;

import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.world_objects.GameWorld;

/**
 * A verb the user can use in the game's parser
 * @author mcook
 */
public interface GameVerb {
	/**
	 * Handles the execution of this verb
	 * @param world The game world we are executing in
	 * @param params Strings of the extra stuff passed into this verb
	 */
	public void executeVerb(GameWorld world, String... params) throws InvalidActionException,
																		InvalidGrammarException,
																		UnknownObjectException;
	
	/**
	 * Print out help on how to use this command
	 */
	public void printHelp();
}
