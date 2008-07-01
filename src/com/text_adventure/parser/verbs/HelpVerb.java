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
		if (sentence.size() == 1) {
			// They didn't sepcify a target, print general help
			
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
		} else if (sentence.size() == 2) {
			ParserToken token = sentence.get(1);
			
			if (token instanceof GameVerb) {
				// They want help with a verb, print it's extended help
				
				GameVerb verb = (GameVerb) token;
				
				System.out.println(verb.getExtendedHelp());
			} else {
				// They didn't give us a verb, that's a gramatical error
				
				throw new InvalidGrammarException("The help command must be called alone, or with a verb.");
			}
		} else {
			// They put too many words in there, so complain
			
			throw new InvalidGrammarException("The help command only takes one parameter, a verb.");
		}
	}

	public String getHelp() {
		return "Prints all known verbs, explains commands";
	}

	public String getExtendedHelp() {
		return "help [VERB]\n\nCalling help with an argument (i.e. \"help walk\") will tell you how to use that command. Without an argument help prints a list of known commands.";
	}
	
	public String getVerb() {
		return "help";
	}
}
