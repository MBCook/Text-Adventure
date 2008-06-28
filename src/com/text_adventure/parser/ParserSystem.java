package com.text_adventure.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.special_words.GameDirectionWord;
import com.text_adventure.parser.special_words.GamePrepositionWord;
import com.text_adventure.parser.special_words.GameSpecialWord;
import com.text_adventure.parser.verbs.CloseVerb;
import com.text_adventure.parser.verbs.ExitVerb;
import com.text_adventure.parser.verbs.GameVerb;
import com.text_adventure.parser.verbs.GameVerbAlias;
import com.text_adventure.parser.verbs.HelpVerb;
import com.text_adventure.parser.verbs.LookVerb;
import com.text_adventure.parser.verbs.OpenVerb;
import com.text_adventure.parser.verbs.PutVerb;
import com.text_adventure.parser.verbs.TakeVerb;
import com.text_adventure.parser.verbs.ThrowVerb;
import com.text_adventure.parser.verbs.UseVerb;
import com.text_adventure.parser.verbs.WalkVerb;
import com.text_adventure.world_objects.GameWorld;
import com.text_adventure.world_objects.WorldDirection;
import com.text_adventure.world_objects.WorldObject;

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
	 * A map to let us find special words easily
	 */
	private Map<String, GameSpecialWord> wordToSpecialWordMap = new HashMap<String, GameSpecialWord>();
	
	/**
	 * A list of words, like 'the', that we could care less about
	 */
	private Set<String> noiseWords = new HashSet<String>();
	
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
		addVerb(new ExitVerb());
		addVerb(new HelpVerb());
		
		// Now some aliases
		
		addVerb(new GameVerbAlias(getVerbFromWord("take"), "pick-up"));
		addVerb(new GameVerbAlias(getVerbFromWord("put"), "put-down"));
		addVerb(new GameVerbAlias(getVerbFromWord("put"), "set-down"));
		addVerb(new GameVerbAlias(getVerbFromWord("walk"), "move"));
		addVerb(new GameVerbAlias(getVerbFromWord("walk"), "go"));
		addVerb(new GameVerbAlias(getVerbFromWord("exit"), "quit"));
		
		// Now some special words we'll want
		
		addSpecialWord(new GamePrepositionWord("in", WorldPreposition.IN));
		addSpecialWord(new GamePrepositionWord("at", WorldPreposition.AT));
		addSpecialWord(new GamePrepositionWord("on", WorldPreposition.ON));
		addSpecialWord(new GameDirectionWord("north", WorldDirection.NORTH));
		addSpecialWord(new GameDirectionWord("east", WorldDirection.EAST));
		addSpecialWord(new GameDirectionWord("south", WorldDirection.SOUTH));
		addSpecialWord(new GameDirectionWord("west", WorldDirection.WEST));
		
		// And the noise words
		
		addNoiseWord("the");
		addNoiseWord("to");
		addNoiseWord("that");
	}
	
	/**
	 * Add the given verb to our map of verbs
	 * @param verb The verb to add
	 */
	public void addVerb(GameVerb verb) {
		wordToVerbMap.put(verb.getVerb(), verb);
	}
	
	/**
	 * Adds a word to the list of noise words we're ignoring
	 * @param word The word to add
	 */
	public void addNoiseWord(String word) {
		noiseWords.add(word);
	}
	
	/**
	 * Get the verb for a given word
	 * @param word The word to look for
	 * @return The verb
	 */
	public GameVerb getVerbFromWord(String word) {
		return wordToVerbMap.get(word);
	}
	
	/**
	 * Get the words that we can recognise
	 * @return The words in a set
	 */
	public Set<String> getWordsForHelp() {
		return wordToVerbMap.keySet();
	}
	
	/**
	 * Add the given special word to our map of special words
	 * @param verb The special word to add
	 */
	public void addSpecialWord(GameSpecialWord specialWord) {
		wordToSpecialWordMap.put(specialWord.getSpecialWord(), specialWord);
	}
	
	/**
	 * Get the special word for a given word
	 * @param word The word to look for
	 * @return The special word
	 */
	public GameSpecialWord getSpecialWordFromWord(String word) {
		return wordToSpecialWordMap.get(word);
	}
	
	/**
	 * Take the given sentence and turn it into a list of parser tokens
	 * @param world The game world we're operating in
	 * @param sentence The sentence to parse
	 * @return A list of tokens
	 * @throws UnknownObjectException If there is a reference to a word we don't know
	 * 									or to an object the user isn't near
	 */
	public List<ParserToken> parseSentence(GameWorld world, String sentence)
																	throws UnknownObjectException {
		List<ParserToken> result = new ArrayList<ParserToken>();
		
		// First, sanity check the sentence
		
		if ((sentence == null) || (sentence.trim().length() == 0)) {
			throw new IllegalArgumentException("Sentence was null or empty");
		}
		
		// Clear the sentence of punctuation we might run into
		
		sentence = sentence.replaceAll("[,\\.\\?:;'\"]", "");
		
		// OK, split it into words
		
		String[] words = ParserHelper.splitStringIntoWords(sentence.toLowerCase());
		
		// Now go through each word looking for the correct object
		
		for (String word : words) {
			if ((word != null) && (word.trim().length() > 0)) {
				// We've got a word. Find out what it is
				
				word = word.trim();
				
				// Is it a word that is just noise to us?
				
				if (noiseWords.contains(word)) {
					continue;	// Skip it
				}
				
				// First, find out if it's a verb
				
				ParserToken token = getVerbFromWord(word);
				
				if (token != null) {
					// 'Twas a verb, save it and move on
					
					result.add(token);
					
					continue;
				}
				
				// Maybe it's a special word
				
				token = getSpecialWordFromWord(word);
				
				if (token != null) {
					// 'Twas a special word, save it and move on
					
					result.add(token);
					
					continue;
				}
				
				// OK. It better be a game object
				
				token = world.getObjectByName(word);
				
				if (token != null) {
					// It was an object in the game. Does the player have access to it?
					// First check the stuff the player has
					
					WorldObject thing = world.getPlayer().containsObjectWithName(word);
					
					if (thing != null) {
						result.add(thing);
						
						continue;
					}
					
					// Now check the stuff in the current room
					
					thing = world.getRoom().containsObjectWithName(word);
					
					if (thing != null) {
						result.add(thing);
						
						continue;
					}
					
					// If we're here, the object exists but the player doesn't have access to it
					
					throw new UnknownObjectException("You don't have access to anything called '" + word + "'");
					
				}
				
				// We have no idea what this word is. Throw an exception
				
				throw new UnknownObjectException("Your word '" + word + "' is unknown to this world");
			}
		}
		
		// That's all. If we got here we have the sentence
		
		return result;
	}
	
	/**
	 * Check that the sentence meets our basic syntax rules
	 * @param tokens The tokens that make up the sentence
	 * @throws InvalidGrammarException If things aren't kosher
	 */
	public void checkGrammar(List<ParserToken> tokens) throws InvalidGrammarException {
		// Check that things start with a verb
		
		if (!(tokens.get(0) instanceof GameVerb)) {
			throw new InvalidGrammarException("Sentances should be commands. Channel your inner Klingon");
		}
		
		// Check that the verb at the start of the sentence is the only verb
		
		for (int i = 1; i < tokens.size(); i++) {
			if (tokens.get(i) instanceof GameVerb) {
				throw new InvalidGrammarException("No non-predicate verb phrases, Mr. English Major");
			}
		}
		
		// Make sure sentences don't end on prepositions

		if ((tokens.get(tokens.size() - 1) instanceof GamePrepositionWord)) {
			throw new InvalidGrammarException("A preposition is a terrible thing to end a sentence on");
		}
	}
}
