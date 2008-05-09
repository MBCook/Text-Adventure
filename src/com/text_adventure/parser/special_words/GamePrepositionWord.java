package com.text_adventure.parser.special_words;

import com.text_adventure.parser.WorldPreposition;

/**
 * Parser representation of a preposition
 * @author mcook
 */
public class GamePrepositionWord extends GameSpecialWord {
	/**
	 * The preposition that we represent
	 */
	private WorldPreposition preposition = null;
	
	/**
	 * Initial us with a word to represent a preposition
	 * @param word
	 * @param preposition
	 */
	public GamePrepositionWord(String word, WorldPreposition preposition) {
		super(word);
		
		this.preposition = preposition;
	}
	
	/**
	 * Get the preposition that we stand in for
	 * @return The preposition
	 */
	public WorldPreposition getPreposition() {
		return preposition;
	}
	
	public boolean isDirection() {
		return false;
	}

	public boolean isPreposition() {
		return true;
	}
}
