package com.text_adventure.parser.verbs;

import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.ParserToken;
import com.text_adventure.world_objects.GameWorld;

/**
 * The verb to move about the game world
 * @author mcook
 */
public class WalkVerb extends GameVerb {
	public void executeVerb(GameWorld world, ParserToken... stentence)
							throws InvalidActionException, InvalidGrammarException,
																UnknownObjectException {
		System.out.println("You are already happy with where you are standing.");
	}

	public String getHelp() {
		return "Walks around";
	}

	public String getVerb() {
		return "walk";
	}
}
