package com.text_adventure.world_objects;

import java.util.List;

/**
 * The object that represents the player of the game
 * @author mcook
 */
public class WorldPlayer extends WorldObject implements Comparable<WorldObject> {
	/**
	 * The player's score in the world
	 */
	private int score = 0;
	/**
	 * The number of steps the player has taken
	 */
	private int steps = 0;
	
	/**
	 * Set us up with the things we know about
	 * @param parent Our parent
	 * @param children Our children
	 * @param siblings Our siblings
	 * @param name The name of this object
	 * @param description The description of this object
	 * @param container Lets us know if we are allowed to contain children
	 * @param type The type of object this is (furnature, key, whatever)
	 */
	public WorldPlayer(WorldObject parent, List<WorldObject> children, List<WorldObject> siblings,
													String name, String description, boolean container) {
		super(parent, children, null, name, description, container);
	}
	
	/**
	 * Set the player's score
	 * @param score The score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Increment the player's score by the given number of points
	 * @param points The number of points to change the score by
	 */
	public void incrementScore(int points) {
		score += points;
	}
	
	/**
	 * Decrement the player's score by the given number of points
	 * @param points The number of points to change the score by
	 */
	public void decrementScore(int points) {
		score -= points;
	}
	
	/**
	 * Get the player's score
	 * @return The score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Set the number of steps the player has taken
	 * @param steps The number of steps
	 */
	public void setSteps(int steps) {
		this.steps = steps;
	}
	
	/**
	 * Increment the number of steps the player has taken
	 */
	public void incrementSteps() {
		steps++;
	}
	
	/**
	 * Get the number of steps the player has taken
	 * @return The number of steps
	 */
	public int getSteps() {
		return steps;
	}
	
	public boolean isRoom() {
		return false;
	}
	
	public boolean isPlayer() {
		return false;
	}
	
	public boolean supportsSiblings() {
		return false;
	}
}
