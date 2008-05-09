package com.text_adventure.parser;

import java.util.HashMap;
import java.util.Map;

import com.text_adventure.parser.verbs.CloseVerb;
import com.text_adventure.parser.verbs.GameVerb;
import com.text_adventure.parser.verbs.GameVerbAlias;
import com.text_adventure.parser.verbs.LookVerb;
import com.text_adventure.parser.verbs.OpenVerb;
import com.text_adventure.parser.verbs.PutVerb;
import com.text_adventure.parser.verbs.TakeVerb;
import com.text_adventure.parser.verbs.ThrowVerb;
import com.text_adventure.parser.verbs.UseVerb;
import com.text_adventure.parser.verbs.WalkVerb;

/**
 * The class responsible for getting and parsing out all player input
 * @author mcook
 */
public class ParserSystem {
	/**
	 * A map to let us find verbs easily
	 */
	private Map<String, GameVerb> wordToVerbMap = new HashMap<String, GameVerb>();
	
	/**
	 * Initializes our verb list with all the code knows about
	 */
	public ParserSystem() {
		// First, we setup the verbs
		
		addVerb(new LookVerb());
		addVerb(new TakeVerb());
		addVerb(new PutVerb());
		addVerb(new OpenVerb());
		addVerb(new CloseVerb());
		addVerb(new UseVerb());
		addVerb(new ThrowVerb());
		addVerb(new WalkVerb());
		
		// Now some aliases
		
		addVerb(new GameVerbAlias(getVerbFromWord("take"), "pick-up"));
		addVerb(new GameVerbAlias(getVerbFromWord("walk"), "move"));
		addVerb(new GameVerbAlias(getVerbFromWord("walk"), "go"));
	}
	
	/**
	 * Add the given verb to our map of verbs
	 * @param verb The verb to add
	 */
	public void addVerb(GameVerb verb) {
		wordToVerbMap.put(verb.getVerb(), verb);
	}
	
	/**
	 * Get the verb for a given word
	 * @param word The word to look for
	 * @return The GameVerb
	 */
	public GameVerb getVerbFromWord(String word) {
		return wordToVerbMap.get(word);
	}
}
