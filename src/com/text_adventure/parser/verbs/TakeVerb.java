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
 * The verb to take an object or pick it up
 * @author mcook
 */
public class TakeVerb extends GameVerb {
	public void executeVerb(GameWorld world, List<ParserToken> sentence)
							throws InvalidActionException, InvalidGrammarException,
											UnknownObjectException, GamestateChangeException {
		// OK, we should only have one argument: the thing to take. It should be a WorldThing
		
		if (sentence.size() < 2) {
			throw new InvalidGrammarException("You must specify what you would like to take.");
		} else if (sentence.size() > 2) {
			throw new InvalidGrammarException("You must specify only one thing to take.");
		} else if (!(sentence.get(1) instanceof WorldThing)) {
			throw new InvalidGrammarException("You must specify something you can actually take.");
		}
		
		// If we're here, we have an actual object. See if we can take it
		
		WorldThing object = (WorldThing) sentence.get(1);
		
		if (!object.isMoveable()) {
			throw new InvalidActionException("You are unable to move the " + object.getName() + ".");
		} else if (PossibleStates.STUCK.equals(object.getState())) {
			throw new InvalidActionException("The " + object.getName() + " is stuck, so you can't move it.");
		}
		
		// I guess it's OK to move it. Take it from the thing it's in, add it to the player inventory, and reparent
		
		object.getParent().removeChild(object);
		
		world.getPlayer().addChild(object);
		
		object.setParent(world.getPlayer());
		
		// That's it
		
		System.out.println("You took the " + object.getName() + ".");
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
