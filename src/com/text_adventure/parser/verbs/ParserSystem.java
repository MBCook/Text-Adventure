package com.text_adventure.parser.verbs;

import java.util.HashMap;
import java.util.Map;

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
