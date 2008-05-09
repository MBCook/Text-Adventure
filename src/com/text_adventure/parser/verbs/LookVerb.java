package com.text_adventure.parser.verbs;

import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.world_objects.GameWorld;

/**
 * The verb to look around the game world
 * @author mcook
 */
public class LookVerb implements GameVerb {
	public void executeVerb(GameWorld world, String... params)
							throws InvalidActionException, InvalidGrammarException,
																UnknownObjectException {
		System.out.println("You looked around. You didn't care.");
	}

	public String getHelp() {
		return "Looks at stuff";
	}

	public String getVerb() {
		return "look";
	}
}
