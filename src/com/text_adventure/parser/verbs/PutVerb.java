package com.text_adventure.parser.verbs;

import java.util.List;

import com.text_adventure.exception.GamestateChangeException;
import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.ParserToken;
import com.text_adventure.world_objects.GameWorld;

/**
 * The verb to put an obect somewhere else
 * @author mcook
 */
public class PutVerb extends GameVerb {
	public void executeVerb(GameWorld world, List<ParserToken> sentence)
							throws InvalidActionException, InvalidGrammarException,
											UnknownObjectException, GamestateChangeException {
		System.out.println("You don't feel like giving up any of your stuff.");
	}

	public String getHelp() {
		return "Puts stuff";
	}

	public String getExtendedHelp() {
		return "This command isn't implemted.";
	}
	
	public String getVerb() {
		return "put";
	}
}
