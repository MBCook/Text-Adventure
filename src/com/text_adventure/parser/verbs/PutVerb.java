package com.text_adventure.parser.verbs;

import java.util.List;

import com.text_adventure.exception.GamestateChangeException;
import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.ParserToken;
import com.text_adventure.parser.special_words.GamePrepositionWord;
import com.text_adventure.world_objects.GameWorld;
import com.text_adventure.world_objects.PossibleStates;
import com.text_adventure.world_objects.WorldObject;
import com.text_adventure.world_objects.WorldThing;

/**
 * The verb to put an obect down (or somewhere)
 * @author mcook
 */
public class PutVerb extends GameVerb {
	public void executeVerb(GameWorld world, List<ParserToken> sentence)
							throws InvalidActionException, InvalidGrammarException,
											UnknownObjectException, GamestateChangeException {
		GamePrepositionWord in = (GamePrepositionWord) world.getParser().getSpecialWordFromWord("in");
		
		// OK, we should only have one argument: the thing to take. It should be a WorldThing
		
		if (sentence.size() < 2) {
			throw new InvalidGrammarException("You must specify what you would like to take.");
		} else if (sentence.size() > 4) {
			throw new InvalidGrammarException("You must specify only one thing to take, and a place to put it.");
		} else if (!(sentence.get(1) instanceof WorldThing)) {
			throw new InvalidGrammarException("You must specify something you can actually take.");
		} else if (!(sentence.get(sentence.size() - 1) instanceof WorldThing)) {
			// The last thing needs to be a thing, no matter what
			throw new InvalidGrammarException("You must specify somewhere to put it.");
		} else if ((sentence.size() == 4) && (!sentence.get(2).equals(in))) {
			throw new InvalidGrammarException("The only preposition you can use is 'in'.");
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
		
		// Now figure out where it goes
		
		WorldObject destination = world.getRoom();
		
		if (sentence.size() > 2) {
			// OK, they specified a destination
			
			destination = (WorldObject) sentence.get(sentence.size() - 1);
			
			// Sanity checks
			
			if (!destination.isContainer()) {
				throw new InvalidActionException("You can not put anything in the " + destination.getName());
			} else if (!PossibleStates.OPEN.equals(destination.getState())) {
				throw new InvalidActionException("The " + destination.getName() + " isn't open.");
			}
		}
		
		// I guess it's OK to move it. Take it from the inventory, add it to the destionation, and reparent
		
		world.getPlayer().removeChild(object);
		
		destination.addChild(object);
		
		object.setParent(destination);
		
		// That's it
		
		if (destination != world.getRoom()) {
			System.out.println("You put the the " + object.getName() + " in the " + destination.getName());
		} else {
			System.out.println("You put down the " + object.getName() + ".");
		}
	}

	public String getHelp() {
		return "Puts stuff down or in other stuff";
	}

	public String getExtendedHelp() {
		return "put OBJECT [[in] OBJECT]";
	}
	
	public String getVerb() {
		return "put";
	}
}
