package com.text_adventure.parser.verbs;

import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.world_objects.GameWorld;

/**
 * The verb to open something in the game world (a door, a box, etc)
 * @author mcook
 */
public class OpenVerb implements GameVerb {
	public void executeVerb(GameWorld world, String... params)
							throws InvalidActionException, InvalidGrammarException,
																UnknownObjectException {
		System.out.println("You don't want to open what may be Pandora's Box.");
	}

	public String getHelp() {
		return "Opens things";
	}

	public String getVerb() {
		return "open";
	}
}
