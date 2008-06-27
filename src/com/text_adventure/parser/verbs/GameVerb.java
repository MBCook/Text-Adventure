package com.text_adventure.parser.verbs;

import java.util.List;

import com.text_adventure.exception.GamestateChangeException;
import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.ParserToken;
import com.text_adventure.world_objects.GameWorld;

/**
 * A verb the user can use in the game's parser
 * @author mcook
 */
public abstract class GameVerb extends ParserToken {
	/**
	 * Handles the execution of this verb
	 * @param world The game world we are executing in
	 * @param sentence The sentence (tokenized) that was parsed
	 */
	public abstract void executeVerb(GameWorld world, ParserToken... sentence)
											throws InvalidActionException, InvalidGrammarException,
														UnknownObjectException, GamestateChangeException;

	/**
	 * Conveniece method for us
	 * @param world The game world we are executing in
	 * @param sentence The sentence (tokenized) that was parsed
	 */
	public void executeVerb(GameWorld world, List<ParserToken> sentence)
											throws InvalidActionException, InvalidGrammarException,
														UnknownObjectException, GamestateChangeException {
		executeVerb(world, (ParserToken[]) sentence.toArray());
	}
	
	/**
	 * Return the help string for this verb
	 * @return The help string
	 */
	public abstract String getHelp();
	
	/**
	 * Get the word that this verb represents
	 * @return The word
	 */
	public abstract String getVerb();
}
