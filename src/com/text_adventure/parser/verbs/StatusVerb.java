package com.text_adventure.parser.verbs;

import java.util.List;

import com.text_adventure.exception.GamestateChangeException;
import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.ParserToken;
import com.text_adventure.world_objects.GameWorld;

/**
 * Prints some basic information about the player
 * @author mcook
 */
public class StatusVerb extends GameVerb {
	public void executeVerb(GameWorld world, List<ParserToken> sentence)
							throws InvalidActionException, InvalidGrammarException,
										UnknownObjectException, GamestateChangeException {
		System.out.println("Your current status is: " + world.getPlayer().getState() + ".");
		System.out.println("You have taken " + world.getPlayer().getSteps() + " steps.");
		System.out.println("Your score is: " + world.getPlayer().getScore() + ".");
	}

	public String getHelp() {
		return "Print player status";
	}

	public String getVerb() {
		return "status";
	}
}
