package com.textadventure.adventures;

import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.world_objects.GameWorld;

public interface Adventure {
	/**
	 * Get the world, all setup to play, from this adventure
	 * @return A world, with a player, ready to go
	 * @throws InvalidActionException
	 */
	public GameWorld getWorld() throws InvalidActionException ;
}
