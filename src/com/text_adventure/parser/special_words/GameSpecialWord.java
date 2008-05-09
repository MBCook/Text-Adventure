package com.text_adventure.parser.special_words;

import com.text_adventure.parser.ParserToken;

/**
 * A special word that is neither an object (table) or verb (walk)
 * @author mcook
 */
public abstract class GameSpecialWord extends ParserToken {
	/**
	 * The special word we represent
	 */
	private String specialWord = null;

	/**
	 * Initialize and set our special word
	 * @param specialWord The word we represent
	 */
	public GameSpecialWord(String specialWord) {
		this.specialWord = specialWord;
	}
	
	/**
	 * Get the word that this special word represents
	 * @return The word
	 */
	public String getSpecialWord() {
		return specialWord;
	}
	
	/**
	 * Return if we are a preposition
	 * @return If we are a preposition
	 */
	public abstract boolean isPreposition();
	
	/**
	 * Return if we are a direction
	 * @return If we are a direction
	 */
	public abstract boolean isDirection();
}
