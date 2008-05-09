package com.text_adventure.parser.special_words;

import com.text_adventure.world_objects.WorldDirection;

/**
 * A word representing a direction
 * @author mcook
 */
public class GameDirectionWord extends GameSpecialWord {
	/**
	 * The direction we represent
	 */
	private WorldDirection direction = null;
	
	/**
	 * Setup our word and the direction it represents
	 * @param word The word we represent
	 * @param direction The direction that goes with that word
	 */
	public GameDirectionWord(String word, WorldDirection direction) {
		super(word);
		
		this.direction = direction;
	}
	
	/**
	 * Get the direction we represent
	 * @return The direction
	 */
	public WorldDirection getDirection() {
		return direction;
	}
	
	public boolean isDirection() {
		return true;
	}

	public boolean isPreposition() {
		return false;
	}
}
