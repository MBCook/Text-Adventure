package com.text_adventure.parser.verbs;

import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.ParserToken;
import com.text_adventure.world_objects.GameWorld;

/**
 * The verb to throw something (a tomato, etc)
 * @author mcook
 */
public class ThrowVerb extends GameVerb {
	public void executeVerb(GameWorld world, ParserToken... sentence)
							throws InvalidActionException, InvalidGrammarException,
																UnknownObjectException {
		System.out.println("You throw a loud temper tantrum.");
		
		try {
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getHelp() {
		return "Throws things";
	}

	public String getVerb() {
		return "throw";
	}
}
