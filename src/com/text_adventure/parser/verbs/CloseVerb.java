package com.text_adventure.parser.verbs;

import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.ParserToken;
import com.text_adventure.world_objects.GameWorld;

/**
 * The verb to close something in the game world (a door, a box, etc)
 * @author mcook
 */
public class CloseVerb extends GameVerb {
	public void executeVerb(GameWorld world, ParserToken... sentence)
							throws InvalidActionException, InvalidGrammarException,
																UnknownObjectException {
		System.out.println("You aren't willing to close this chapter in your life.");
	}

	public String getHelp() {
		return "Closes things";
	}

	public String getVerb() {
		return "close";
	}
}
