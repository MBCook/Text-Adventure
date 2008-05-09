package com.text_adventure.parser.verbs;

import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.world_objects.GameWorld;

/**
 * The verb to put an obect somewhere else
 * @author mcook
 */
public class PutVerb implements GameVerb {
	public void executeVerb(GameWorld world, String... params)
							throws InvalidActionException, InvalidGrammarException,
																UnknownObjectException {
		System.out.println("You don't feel like giving up any of your stuff.");
	}

	public String getHelp() {
		return "Puts stuff";
	}

	public String getVerb() {
		return "put";
	}
}
