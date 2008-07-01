package com.text_adventure.parser.verbs;

import java.util.List;

import com.text_adventure.exception.GamestateChangeException;
import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.ParserToken;
import com.text_adventure.world_objects.GameWorld;

/**
 * The verb to take an object or pick it up
 * @author mcook
 */
public class TakeVerb extends GameVerb {
	public void executeVerb(GameWorld world, List<ParserToken> sentence)
							throws InvalidActionException, InvalidGrammarException,
											UnknownObjectException, GamestateChangeException {
		System.out.println("You don't feel like taking anything.");
	}

	public String getHelp() {
		return "Takes stuff";
	}

	public String getExtendedHelp() {
		return "This command isn't implemted.";
	}
	
	public String getVerb() {
		return "take";
	}
}
