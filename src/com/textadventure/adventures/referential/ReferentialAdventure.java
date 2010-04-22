package com.textadventure.adventures.referential;

import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.world_objects.GameWorld;
import com.text_adventure.world_objects.PossibleStates;
import com.text_adventure.world_objects.WorldObject;
import com.text_adventure.world_objects.WorldThing;
import com.textadventure.adventures.Adventure;

public class ReferentialAdventure implements Adventure {
	// Setup the special objects in the game world
	
	private WorldThing bucket = new WorldThing(null, null, "bucket",
				"A well worn old bucket, made out of a thin tin like metal. Inside the bucket is some old " +
				"water so clear, it makes your dryer lint look translucent. Smells faintly of amonia.",
				false, "object", PossibleStates.NORMAL, true);
	private WorldThing amulet = new WorldThing(null, null, "amulet",
				"Strange and scratched, the back is engraved \"Amulet of Cheez Sawce\". An unearthly " +
				"orange color, the ceramic amulet looks a bit like a melted wedge of cheese.",
				false, "object", PossibleStates.NORMAL, true);
	private WorldThing pringles = new WorldThing(null, null, "pringles",	// To hold the amulet
				"An old can of Blueberry & Hazelnut Pringles, permenantly attached to the floor.",
				true, "object", PossibleStates.CLOSED, false);				// Can't move, stuck to floor
	private WorldThing key = new WorldThing(null, null, "key",
				"The Golden Key of Desitny is known far an wide. It is said that the posessor of the key " +
				"will have car trouble if the key isn't used by the next full moon. This tragic curse " +
				"helped lead to the downfall of Billy Mumphry.",
				false, "object", PossibleStates.NORMAL, false);
	private WorldThing mound = new WorldThing(null, null, "mound",			// To hold the key
				"An odd mound of caked dirt, your hands can't make a dent in it.",
				true, "object", PossibleStates.NORMAL, false);
	private WorldThing spade = new WorldThing(null, null, "spade",
				"A simple rusty spade, it was clearly used for decades. The name 'Sam' is barely " +
				"legible on the handle, having faded from use.",
				false, "object", PossibleStates.NORMAL, false);
	private WorldThing plank = new WorldThing(null, null, "plank",
				"An Extendi-Plank(r). According to the label, it's a long piece of poplar word, treated " +
				"with \"global warming age chemicals\" to allow it to be compressed to a handy pocket size, " +
				"but easily extended again to cross any chasm.",
				false, "object", PossibleStates.NORMAL, false);
	private WorldThing computer = new WorldThing(null, null, "computer",
				"A NAVI brand laptop computer, with a faded tangerine plastic case. NAVI \"Knownledge " +
				"Navigator\"s are well known for being highly expandable, as well as cute.",
				false, "object", PossibleStates.NORMAL, false);
	private WorldThing earplugs = new WorldThing(null, null, "earplugs",
				"Made of only the cheapest and most uncomfortable rubber, these earplugs look like they would " +
				"be very uncomfortable for your ears in a way that only could only be matched by an unsharpened " +
				"pencil covered with a thin layer of candle wax. At least they they block sound... suposedly.",
				false, "object", PossibleStates.NORMAL, false);
	private WorldThing toothbrush = new WorldThing(null, null, "toothbrush",
				"Forged from a sparkly green plastic, this brush still has most of it's bristles, but the " +
				"backwards ADA logo imprinted on the handle feels rough to your skin while at the same time " +
				"failing to impart any sense of quality.",
				false, "object", PossibleStates.NORMAL, false);
	private WorldThing dvd = new WorldThing(null, null, "dvd",
				"It's hard to know what was on this DVD. One side is scratched up, while the other looks like " +
				"it was accidently run through a disc resurfacer accidently once.",
				false, "object", PossibleStates.NORMAL, false);
	private WorldThing token = new WorldThing(null, null, "token",
				"First designed by Robert Patten in 1963, the Funzone Industries game token became a part " +
				"of every suburban child's adolescent experience, until a series of unfortunate injuries " +
				"involving the Kick-A-Warthog game developed into bankrupting lawsuits in the late 80s.",
				false, "object", PossibleStates.NORMAL, false);
	private WorldThing printout = new WorldThing(null, null, "printout",
				"Printed on crinkled reused paper, the text on this printout is unreadable gibberish to you, " +
				"the result of either an elaborate cipher or a crossed wire in the printer cable. The " +
				"back of the paper contains a faded hardcopy of a webcomic.",
				false, "object", PossibleStates.NORMAL, false);
	
	public GameWorld getWorld() throws InvalidActionException {
		return null;
	}
}
