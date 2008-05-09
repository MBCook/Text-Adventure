package com.text_adventure.parser.verbs;

import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.ParserToken;
import com.text_adventure.world_objects.GameWorld;

/**
 * The verb to use something in the game world (a key, a match, etc)
 * @author mcook
 */
public class UseVerb extends GameVerb {
	public void executeVerb(GameWorld world, ParserToken... sentence)
							throws InvalidActionException, InvalidGrammarException,
																UnknownObjectException {
		System.out.println("Users are losers. You don't want to be one.");
	}

	public String getHelp() {
		return "Uses things";
	}

	public String getVerb() {
		return "use";
	}
}
