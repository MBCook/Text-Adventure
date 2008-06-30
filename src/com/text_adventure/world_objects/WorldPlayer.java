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
	 * @param name The name of this object
	 * @param description The description of this object
	 * @param state The state of this object
	 * @param container Lets us know if we are allowed to contain children
	 */
	public WorldPlayer(WorldObject parent, List<WorldObject> children,
									String name, String description, PossibleStates state, boolean container) {
		super(parent, children, name, description, state, container);
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
		// We are not a room.
		return false;
	}
	
	public boolean isPlayer() {
		// We are the player. We rule.
		return true;
	}

	public boolean isMoveable() {
		// We can't be picked up, so this is just incase.
		return false;
	}
}
