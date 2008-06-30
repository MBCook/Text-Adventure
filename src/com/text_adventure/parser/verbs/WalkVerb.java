package com.text_adventure.parser.verbs;

import java.util.List;

import com.text_adventure.exception.GamestateChangeException;
import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.ParserToken;
import com.text_adventure.parser.special_words.GameDirectionWord;
import com.text_adventure.world_objects.GameWorld;
import com.text_adventure.world_objects.WorldRoom;

/**
 * The verb to move about the game world
 * @author mcook
 */
public class WalkVerb extends GameVerb {
	public void executeVerb(GameWorld world, List<ParserToken> sentence)
							throws InvalidActionException, InvalidGrammarException,
										UnknownObjectException, GamestateChangeException {
		// Get the direction they want to move in
		
		GameDirectionWord direction = null;
		
		for (int i = 1; i < sentence.size(); i++) {
			ParserToken token = sentence.get(i);
			
			if (token instanceof GameDirectionWord) {
				direction = (GameDirectionWord) token;
				break;
			}
		}
		
		if (direction == null) {
			throw new InvalidGrammarException("You must specify the direction to move in.");
		}	
		
		// There is a process to this. First, make sure there is actually a room where they want to go
		
		WorldRoom sourceRoom = world.getRoom();
		WorldRoom targetRoom = sourceRoom.getRoomInDirection(direction.getDirection());
		
		if (targetRoom == null) {
			// No room there
			
			throw new InvalidActionException("There is no room to your " + direction.getSpecialWord() + ".");
		}
		
		// OK, the room is there. Let's see if they can enter the new room (throws exception if they can't)
		
		targetRoom.attemptToEnter();
		
		// If we're here, they can enter the new room. See if they can leave this one (throws exception if not)
		
		sourceRoom.attemptToExit();
		
		// That was good too huh? Move them, increase the step count, and print a message
		
		world.getPlayer().setSteps(world.getPlayer().getSteps() + 1);
		
		world.setRoom(targetRoom);
		
		System.out.println("You moved to the " + direction.getSpecialWord() + ".");
	}

	public String getHelp() {
		return "Walks around";
	}

	public String getVerb() {
		return "walk";
	}
}
