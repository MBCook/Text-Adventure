package com.text_adventure.parser.verbs;

import java.util.List;

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
	public void executeVerb(GameWorld world, List<ParserToken> sentence)
							throws InvalidActionException, InvalidGrammarException,
													UnknownObjectException, GamestateChangeException {
		if (sentence.size() != 1) {
			throw new InvalidGrammarException("The exit doesn't take any parameters.");
		}
		
		throw new PlayerDeathException("You commit seppuku, then black out.");
	}

	public String getHelp() {
		return "Exits the game";
	}

	public String getExtendedHelp() {
		return "Exits the game world, killing you in the process. Afterward, it prints out your status.";
	}
	
	public String getVerb() {
		return "exit";
	}
}
