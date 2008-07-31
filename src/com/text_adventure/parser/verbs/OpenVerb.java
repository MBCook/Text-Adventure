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
 * The verb to open something in the game world (a door, a box, etc)
 * @author mcook
 */
public class OpenVerb extends GameVerb {
	public void executeVerb(GameWorld world, List<ParserToken> sentence)
							throws InvalidActionException, InvalidGrammarException,
											UnknownObjectException, GamestateChangeException {
		// We should be given one object to work with, no extraneous words
		
		if (sentence.size() < 2) {
			throw new InvalidGrammarException("You must specify what you would like to open.");
		} else if (sentence.size() > 2) {
			throw new InvalidGrammarException("You must specify only one thing to open.");
		} else if (!(sentence.get(1) instanceof WorldThing)) {
			throw new InvalidGrammarException("You must specify something you can actually open.");
		}
		
		// If we're here, we have an actual object. It should be closed right now
		
		WorldThing object = (WorldThing) sentence.get(1);
		
		if (object.getState().equals(PossibleStates.CLOSED)) {
			// Do it!
			
			object.setState(PossibleStates.OPEN);
			
			System.out.println("You opened the " + object.getName());
		} else {
			// It wasn't closed, so it can't be opened
			
			throw new InvalidActionException("The " + object.getName() + " wasn't closed.");
		}
	}

	public String getHelp() {
		return "Opens things";
	}

	public String getExtendedHelp() {
		return "open OBJECT";
	}
	
	public String getVerb() {
		return "open";
	}
}
