package com.text_adventure.parser.verbs;

import java.util.List;

import com.text_adventure.exception.GamestateChangeException;
import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.ParserToken;
import com.text_adventure.parser.special_words.GameSpecialWord;
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
		
		if (sentence.size() == 1) {	// No other words, just "look"
			// Look around the room. This is acomplished by simply printing the room description
			
			object = world.getRoom();
		} else {
			// We're looking at a specific object.
			
			for (int i = 1; i < sentence.size(); i++) {
				ParserToken token = sentence.get(i);
				
				if (token instanceof GameSpecialWord) {
					// It's something like "at", which we'll ignore
					
					continue;
				} else if (token instanceof WorldThing) {
					object = (WorldObject) token;
					
					break;
				}
			}
			
			// If we still don't have an object, we're confused
			
			if (object == null) {
				throw new InvalidGrammarException("You need to specify something to look at, or nothing to simply look around");
			}
		}

		System.out.println(object.getDescription());
	}

	public String getHelp() {
		return "Looks at stuff";
	}

	public String getVerb() {
		return "look";
	}
}
