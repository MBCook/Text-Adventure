package com.text_adventure.parser.verbs;

import java.util.List;

import com.text_adventure.exception.GamestateChangeException;
import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.ParserToken;
import com.text_adventure.world_objects.GameWorld;
import com.text_adventure.world_objects.PossibleStates;
import com.text_adventure.world_objects.WorldThing;

/**
 * The verb to close something in the game world (a door, a box, etc)
 * @author mcook
 */
public class CloseVerb extends GameVerb {
	public void executeVerb(GameWorld world, List<ParserToken> sentence)
							throws InvalidActionException, InvalidGrammarException,
										UnknownObjectException, GamestateChangeException {
		// We should be given one object to work with, no extraneous words
		
		if (sentence.size() < 2) {
			throw new InvalidGrammarException("You must specify what you would like to close.");
		} else if (sentence.size() > 2) {
			throw new InvalidGrammarException("You must specify only one thing to close.");
		} else if (!(sentence.get(1) instanceof WorldThing)) {
			throw new InvalidGrammarException("You must specify something you can actually close.");
		}
		
		// If we're here, we have an actual object. It should be open.
		
		WorldThing object = (WorldThing) sentence.get(1);
		
		if (!object.isStateChangeable()) {
			throw new InvalidActionException("The " + object.getName() + " can't be closed.");
		} else if (object.getState().equals(PossibleStates.OPEN)) {
			// Do it!
			
			object.setState(PossibleStates.CLOSED);
			
			System.out.println("You closed the " + object.getName());
		} else {
			// It wasn't open, so it can't be closed
			
			throw new InvalidActionException("The " + object.getName() + " wasn't open.");
		}
	}

	public String getHelp() {
		return "Closes things";
	}

	public String getExtendedHelp() {
		return "close OBJECT";
	}
	
	public String getVerb() {
		return "close";
	}
}
