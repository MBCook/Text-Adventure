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
 * The verb to put an obect down
 * @author mcook
 */
public class PutVerb extends GameVerb {
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
		
		// If we're here, we have an actual object. It should be in our inventory, not stuck, and moveable
		
		WorldThing object = (WorldThing) sentence.get(1);
		
		if (!object.isMoveable()) {
			throw new InvalidActionException("You are unable to put down the " + object.getName() + ".");
		} else if (PossibleStates.STUCK.equals(object.getState())) {
			throw new InvalidActionException("The " + object.getName() + " is stuck, so you can't put it down.");
		} else if (!world.getPlayer().equals(object.getParent())) {
			throw new InvalidActionException("You are not holding the " + object.getName() + ".");
		}
		
		// I guess it's OK to move it. Take it from the inventory, add it to the current room, and reparent
		
		world.getPlayer().removeChild(object);
		
		world.getRoom().addChild(object);
		
		object.setParent(world.getRoom());
		
		// That's it
		
		System.out.println("You put down the " + object.getName() + ".");
	}

	public String getHelp() {
		return "Puts stuff";
	}

	public String getExtendedHelp() {
		return "put OBJECT";
	}
	
	public String getVerb() {
		return "put";
	}
}
