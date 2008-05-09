package com.text_adventure.parser.verbs;

import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.world_objects.GameWorld;

/**
 * The verb to take an object or pick it up
 * @author mcook
 */
public class TakeVerb implements GameVerb {
	public void executeVerb(GameWorld world, String... params)
							throws InvalidActionException, InvalidGrammarException,
																UnknownObjectException {
		System.out.println("You don't feel like taking anything.");
	}

	public String getHelp() {
		return "Takes stuff";
	}

	public String getVerb() {
		return "take";
	}
}
