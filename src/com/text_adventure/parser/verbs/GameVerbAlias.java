package com.text_adventure.parser.verbs;

import java.util.List;

import com.text_adventure.exception.GamestateChangeException;
import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.ParserToken;
import com.text_adventure.world_objects.GameWorld;

/**
 * A class to point to a verb so that go and walk can mean the same thing
 * @author mcook
 */
public class GameVerbAlias extends GameVerb {
	/**
	 * The verb this alias points to
	 */
	private GameVerb targetVerb = null;
	/**
	 * The name of this alias
	 */
	private String alias = null;
	
	/**
	 * Constructor to create this verb
	 * @param targetVerb The verb this alias points to
	 * @param alias The alias of that verb we want to use
	 */
	public GameVerbAlias(GameVerb targetVerb, String alias) {
		this.targetVerb = targetVerb;
		this.alias = alias;
	}
	
	public void executeVerb(GameWorld world, List<ParserToken> sentence)
											throws InvalidActionException, InvalidGrammarException,
														UnknownObjectException, GamestateChangeException {
		targetVerb.executeVerb(world, sentence);
	}

	public String getVerb() {
		return alias;
	}

	public GameVerb getTargetVerb() {
		return targetVerb;
	}
	
	public String getExtendedHelp() {
		// Aliases don't have extended help, so return what the target verb does
		
		return targetVerb.getExtendedHelp();
	}
	
	public String getHelp() {
		// Aliases don't have help, so return what the target verb does
		
		return targetVerb.getHelp();
	}
}
