package com.text_adventure.parser.verbs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.text_adventure.exception.GamestateChangeException;
import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.ParserToken;
import com.text_adventure.world_objects.GameWorld;

/**
 * The verb to list all the words we know
 * @author mcook
 */
public class HelpVerb extends GameVerb {
	public void executeVerb(GameWorld world, List<ParserToken> sentence)
							throws InvalidActionException, InvalidGrammarException,
										UnknownObjectException, GamestateChangeException {
		List<String> words = new ArrayList<String>(world.getParser().getWordsForHelp());
		
		Collections.sort(words);
		
		System.out.println("\nThe universe has decreed the following verbs acceptable:");
		
		for (String word : words) {
			GameVerb verb = world.getParser().getVerbFromWord(word);
			
			System.out.print("\t");
			
			if (verb instanceof GameVerbAlias) {
				GameVerbAlias alias = (GameVerbAlias) verb;
				
				System.out.print(alias.getVerb());
				System.out.print("\t\tAlias for ");
				System.out.println(alias.getTargetVerb().getVerb());
			} else {
				System.out.print(verb.getVerb());
				System.out.print("\t\t");
				System.out.println(verb.getHelp());				
			}
		}
		
		System.out.print("\n");
	}

	public String getHelp() {
		return "Prints all known verbs";
	}

	public String getVerb() {
		return "help";
	}
}
