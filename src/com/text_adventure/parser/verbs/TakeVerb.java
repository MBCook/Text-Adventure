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
 * The verb to take an object or pick it up
 * @author mcook
 */
public class TakeVerb extends GameVerb {
	public void executeVerb(GameWorld world, List<ParserToken> sentence)
							throws InvalidActionException, InvalidGrammarException,
											UnknownObjectException, GamestateChangeException {
		GamePrepositionWord in = (GamePrepositionWord) world.getParser().getSpecialWordFromWord("in");
		GamePrepositionWord from = (GamePrepositionWord) world.getParser().getSpecialWordFromWord("from");
		
		// OK, we should have up to three arguments
		
		if (sentence.size() < 2) {
			throw new InvalidGrammarException("You must specify what you would like to take.");
		} else if (sentence.size() > 4) {
			throw new InvalidGrammarException("You must specify only one thing to take and where to take it from.");
		} else if (!(sentence.get(1) instanceof WorldThing)) {
			throw new InvalidGrammarException("You must specify something you can actually take.");
		} else if (!(sentence.get(sentence.size() - 1) instanceof WorldThing)) {
			// The last thing needs to be a thing, no matter what
			throw new InvalidGrammarException("You must specify somewhere to take it from.");
		} else if ((sentence.size() == 4) && (!sentence.get(2).equals(in)) && (!sentence.get(2).equals(from))) {
			throw new InvalidGrammarException("The only prepositions you can use are 'in' and 'from'.");
		}
		
		// If we're here, we have an actual object. See if we can take it
		
		WorldThing object = (WorldThing) sentence.get(1);
		
		if (!object.isMovable()) {
			throw new InvalidActionException("You are unable to move the " + object.getName() + ".");
		} else if (PossibleStates.STUCK.equals(object.getState())) {
			throw new InvalidActionException("The " + object.getName() + " is stuck, so you can't move it.");
		}

		// Now figure out where it goes
		
		WorldObject source = world.getRoom();
		
		if (sentence.size() > 2) {
			// OK, they specified a destination
			
			source = (WorldObject) sentence.get(sentence.size() - 1);
			
			// Sanity checks
			
			if (!source.isContainer()) {
				throw new InvalidActionException("You can not take anything in the " + source.getName() + ".");
			} else if (!PossibleStates.OPEN.equals(source.getState())) {
				throw new InvalidActionException("The " + source.getName() + " isn't open.");
			} else if (null == source.containsObjectWithName(object.getName())) {
				// This makes sure the thing they want to take is where they think it is
				throw new InvalidActionException("The " + source.getName() + " doesn't contain the " + object.getName() + ".");
			}
		}
		
		// I guess it's OK to move it. Take it from the thing it's in, add it to the player inventory, and reparent
		
		object.getParent().removeChild(object);
		
		world.getPlayer().addChild(object);
		
		object.setParent(world.getPlayer());
		
		// That's it
		
		if (source != world.getRoom()) {
			System.out.println("You took the " + object.getName() + " from the " + source.getName() + ".");
		} else {
			System.out.println("You took the " + object.getName() + ".");
		}
	}

	public String getHelp() {
		return "Takes stuff";
	}

	public String getExtendedHelp() {
		return "take OBJECT [[in/from] OBJECT]";
	}
	
	public String getVerb() {
		return "take";
	}
}
