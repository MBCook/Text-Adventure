package com.text_adventure.parser.verbs;

import com.text_adventure.exception.GamestateChangeException;
import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.PlayerDeathException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.ParserToken;
import com.text_adventure.world_objects.GameWorld;

/**
 * The verb to close something in the game world (a door, a box, etc)
 * @author mcook
 */
public class ExitVerb extends GameVerb {
	public void executeVerb(GameWorld world, ParserToken... sentence)
							throws InvalidActionException, InvalidGrammarException,
													UnknownObjectException, GamestateChangeException {
		System.out.println("Coward.");
		
		throw new PlayerDeathException("You commit seppuku");
	}

	public String getHelp() {
		return "Exits the game";
	}

	public String getVerb() {
		return "exut";
	}
}
