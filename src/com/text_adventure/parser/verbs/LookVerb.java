package com.text_adventure.parser.verbs;

import java.util.List;

import com.text_adventure.exception.GamestateChangeException;
import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.ParserToken;
import com.text_adventure.world_objects.GameWorld;
import com.text_adventure.world_objects.WorldObject;
import com.text_adventure.world_objects.WorldThing;

/**
 * The verb to look around the game world
 * @author mcook
 */
public class LookVerb extends GameVerb {
	public void executeVerb(GameWorld world, List<ParserToken> sentence)
							throws InvalidActionException, InvalidGrammarException,
											UnknownObjectException, GamestateChangeException {
		// OK. Let's see what we've got. We are either looking around the room, or looking at something

		WorldObject object = null;
		
		int sentenceSize = sentence.size();
		
		if (sentenceSize == 1) {	// No other words, just "look"
			// Look around the room. This is acomplished by simply printing the room description
			
			object = world.getRoom();
			
			System.out.println(object.getDescription());
		} else if ((sentenceSize == 2) || (sentenceSize == 3)) {
			// OK, some quick grammar check stuff. If the second word is "at", we'll skip it
			
			int indexToLookAt = 1;
			
			if (world.getParser().getSpecialWordFromWord("at").equals(sentence.get(indexToLookAt))) {
				// It's an "at", ignore it
				
				indexToLookAt++;
			}
			
			// We're looking at a specific object.
			
			ParserToken token = sentence.get(indexToLookAt);
				
			if (token instanceof WorldThing) {
				object = (WorldObject) token;
				
				System.out.println(object.getDescription());
			} else {
				// The word we were given is not a thing, complalin
				
				throw new InvalidGrammarException("You need to specify something to look at, or nothing to simply look around.");
			}
		} else {
			// They gave us a sentence that was too long. Throw a grammer exception
			
			throw new InvalidGrammarException("You need to specify something to look at, or nothing to simply look around.");
		}
	}

	public String getHelp() {
		return "Looks at stuff";
	}

	public String getExtendedHelp() {
		return "look [[at] OBJECT]\n\nIf given an object, it prints out a description of that object. If no object is given, looks around the room you are currently in.";
	}
	
	public String getVerb() {
		return "look";
	}
}
