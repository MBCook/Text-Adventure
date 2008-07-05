package com.text_adventure.parser.verbs;

import java.util.List;

import com.text_adventure.exception.GamestateChangeException;
import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.ParserToken;
import com.text_adventure.world_objects.GameWorld;
import com.text_adventure.world_objects.WorldObject;

/**
 * Prints some basic information about the player
 * @author mcook
 */
public class StatusVerb extends GameVerb {
	public void executeVerb(GameWorld world, List<ParserToken> sentence)
							throws InvalidActionException, InvalidGrammarException,
										UnknownObjectException, GamestateChangeException {
		if (sentence.size() != 1) {
			throw new InvalidGrammarException("The status command doesn't take parameters.");
		}
		
		System.out.println("Your current status is: " + world.getPlayer().getState() + ".");
		System.out.println("You have taken " + world.getPlayer().getSteps() + " steps.");
		System.out.println("Your score is: " + world.getPlayer().getScore() + ".");
		
		// Now handle their inventory
		
		List<WorldObject> inventory = world.getPlayer().getChildren();
		
		if ((inventory != null) && (inventory.size() > 0)) {
			System.out.println("Your inventory contains:");
			
			for (WorldObject object : inventory) {
				System.out.println("\t" + addArticleToObject(object.getName()));
			}
		}
	}

	public String getHelp() {
		return "Print player status";
	}

	public String getExtendedHelp() {
		return "Status prints out a few little stats about the player. It takes no arguments.";
	}
	
	public String getVerb() {
		return "status";
	}
	
	private String addArticleToObject(String thing) {
		char firstLetter = thing.charAt(0);
		
		if ((firstLetter == 'a') || (firstLetter == 'e') || (firstLetter == 'i') ||
				(firstLetter == 'o') || (firstLetter == 'u') || (firstLetter == 'h')) {
			return "an " + thing;
		} else {
			return "a " + thing;
		}
	}
}
