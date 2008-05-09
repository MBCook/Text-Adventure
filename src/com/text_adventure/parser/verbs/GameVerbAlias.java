package com.text_adventure.parser.verbs;

import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.ParserToken;
import com.text_adventure.world_objects.GameWorld;

/**
 * A class to point to a verb so that go and walk can mean the same thing
 * @author mcook
 */
public class GameVerbAlias extends GameVerb {
	/**
	 * The verb this alias points to
	 */
	private GameVerb targetVerb = null;
	/**
	 * The name of this alias
	 */
	private String alias = null;
	
	/**
	 * Constructor to create this verb
	 * @param targetVerb The verb this alias points to
	 * @param alias The alias of that verb we want to use
	 */
	public GameVerbAlias(GameVerb targetVerb, String alias) {
		this.targetVerb = targetVerb;
		this.alias = alias;
	}
	
	public void executeVerb(GameWorld world, ParserToken... sentence)
			throws InvalidActionException, InvalidGrammarException,
			UnknownObjectException {
		targetVerb.executeVerb(world, sentence);
	}

	public String getVerb() {
		return alias;
	}

	public String getHelp() {
		// Aliases don't have help
		
		return null;
	}
}
