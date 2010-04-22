package com.textadventure.adventures.referential;

import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.world_objects.GameWorld;
import com.text_adventure.world_objects.PossibleStates;
import com.text_adventure.world_objects.WorldDirection;
import com.text_adventure.world_objects.WorldRoom;
import com.text_adventure.world_objects.WorldThing;
import com.text_adventure.world_objects.factories.WorldThingFactory;
import com.textadventure.adventures.Adventure;

public class ReferentialAdventure implements Adventure {
	// Setup the special objects in the game world
	
	private WorldThing bucket;
	private WorldThing amulet;
	private WorldThing pringles;
	private WorldThing key;
	private WorldThing mound;
	private WorldThing spade;
	private WorldThing plank;
	private WorldThing computer;
	private WorldThing earplugs;
	private WorldThing toothbrush;
	private WorldThing dvd;
	private WorldThing token;
	private WorldThing printout;

	public GameWorld getWorld() throws InvalidActionException {
		createObjects();
		
		return null;
	}
	
	/**
	 * Make all the objects that we need which either move, or start holding moving objects
	 * @throws InvalidActionException If you try to add something to a non-container
	 */
	private void createObjects() throws InvalidActionException {
		 bucket = WorldThingFactory.create("bucket",
					"A well worn old bucket, made out of a thin tin like metal. Inside the bucket is some old " +
					"water so clear, it makes your dryer lint look translucent. Smells faintly of amonia.",
					PossibleStates.NORMAL).finish();
		 amulet = WorldThingFactory.create("amulet",
					"Strange and scratched, the back is engraved \"Amulet of Cheez Sawce\". An unearthly " +
					"orange color, the ceramic amulet looks a bit like a melted wedge of cheese.",
					PossibleStates.NORMAL).finish();
		 pringles = WorldThingFactory.create("pringles",
					"An old can of Blueberry & Hazelnut Pringles, permenantly attached to the floor by some kind of goo.",
					PossibleStates.CLOSED).makeUnmovable().makeContainer().addChild(amulet).finish();
					// The can is immovable because it's attached to the floor
		 key = WorldThingFactory.create("key",
					"The Golden Key of Desitny is known far an wide. It is said that the posessor of the key " +
					"will have car trouble if the key isn't used by the next full moon. This tragic curse " +
					"helped lead to the downfall of Billy Mumphry.",
					PossibleStates.NORMAL).finish();
		 mound = WorldThingFactory.create("mound",
					"An odd mound of caked dirt, your hands can't make a dent in it.",
					PossibleStates.NORMAL).makeUnmovable().makeContainer().addChild(key).finish();
					// Attached to floor, holds the key
		 spade = WorldThingFactory.create("spade",
					"A simple rusty spade, it was clearly used for decades. The name 'Sam' is barely " +
					"legible on the handle, having faded from use.",
					PossibleStates.NORMAL).finish();
		 plank = WorldThingFactory.create("plank",
					"An Extendi-Plank(r). According to the label, it's a long piece of poplar word, treated " +
					"with \"global warming age chemicals\" to allow it to be compressed to a handy pocket size, " +
					"but easily extended again to cross any chasm.",
					PossibleStates.NORMAL).finish();
		 computer = WorldThingFactory.create("computer",
					"A NAVI brand laptop computer, with a faded tangerine plastic case. NAVI \"Knownledge " +
					"Navigator\"s are well known for being highly expandable, as well as cute.",
					PossibleStates.NORMAL).finish();
		 earplugs = WorldThingFactory.create("earplugs",
					"Made of only the cheapest and most uncomfortable rubber, these earplugs look like they would " +
					"be very uncomfortable for your ears in a way that only could only be matched by an unsharpened " +
					"pencil covered with a thin layer of candle wax. At least they they block sound... suposedly.",
					PossibleStates.NORMAL).finish();
		 toothbrush = WorldThingFactory.create("toothbrush",
					"Forged from a sparkly green plastic, this brush still has most of it's bristles, but the " +
					"backwards ADA logo imprinted on the handle feels rough to your skin while at the same time " +
					"failing to impart any sense of quality.",
					PossibleStates.NORMAL).finish();
		 dvd = WorldThingFactory.create("dvd",
					"It's hard to know what was on this DVD. One side is scratched up, while the other looks like " +
					"it was accidently run through a disc resurfacer accidently once.",
					PossibleStates.NORMAL).finish();
		 token = WorldThingFactory.create("token",
					"First designed by Robert Patten in 1963, the Funzone Industries game token became a part " +
					"of every suburban child's adolescent experience, until a series of unfortunate injuries " +
					"involving the Kick-A-Warthog game developed into bankrupting lawsuits in the late 80s.",
					PossibleStates.NORMAL).finish();
		 printout = WorldThingFactory.create("printout",
					"Printed on crinkled reused paper, the text on this printout is unreadable gibberish to you, " +
					"the result of either an elaborate cipher or a crossed wire in the printer cable. The " +
					"back of the paper contains a faded hardcopy of a webcomic.",
					PossibleStates.NORMAL).finish();
	}
	
	/**
	 * Creates the part of the map that contains the pebble trail
	 * @return The first room of the collection containing the pebble trail
	 * @throws InvalidActionException If you try to add something to a non-container
	 */
	private WorldRoom createDungeonPebbleTrailRooms() throws InvalidActionException {
		// We'll start at the end of this little path and work backwards
		
		WorldRoom finalHall = new WorldRoom(null, null, "damp_hall",
					"The long and winding trail of pebles contines from the door at the south end of the corridor, " +
					"through the length of the damp and stuffy corridor. The only light comes from an old string of " +
					"Christmas lights strung along the ceiling. While many of the bulbs are burned out or broken, " +
					"enough are lit for you to see a pile of ants, who seem to have fallen to their deaths from " +
					"one of the many cracks in the ceiling. At the end of the trail, you see an old Pringles can " +
					"that looks like it might be melting.",
					PossibleStates.NORMAL);
		
		finalHall.addSpecialObject(pringles);
		pringles.setParent(finalHall);
		
		// Now we need the pit room, complete with pit
		
		WorldRoom pitRoom = new WorldRoom(null, null, "pit_room",
					"The room is lit by the unearthly glow from the strange door on the eastern side of the room. " +
					"The floor of the western half of the room has almost entirely falled into a large, deep, pit. " +
					"There is an old door leading north, connected to the eastern door by the pebble trail.",
					PossibleStates.NORMAL);
		
		pitRoom.setRoomInDirection(finalHall, WorldDirection.NORTH);
		finalHall.setRoomInDirection(pitRoom, WorldDirection.SOUTH);
		
		WorldThing pit = WorldThingFactory.create("pit",
					"Dark and deep, the only sign anything may exist below is the rare instance of a small " +
					"cloud rising from the depts, causing an unnerving sense of nearby doom. The pit smells " +
					"faintly of burnt strawberry jelly.", PossibleStates.NORMAL).setParent(pitRoom).makeUnmovable().finish();
		
		pitRoom.addSpecialObject(pit);

		// The room with the first segment of the pebble trail
		
		WorldRoom pebbleHall = new WorldRoom(null, null, "pebble_hall",
					"A trail of pebbles leads from the western door down a short hall to a strange " +
					"glowing door, through a roundabout path that would make a cartoonist proud. The floor " +
					"is dusty enough that the action of walking stirs up enough dust to hide your footprints. ",
					PossibleStates.NORMAL);
		
		pebbleHall.setRoomInDirection(pitRoom, WorldDirection.SOUTH);
		pitRoom.setRoomInDirection(pebbleHall, WorldDirection.EAST);
		
		WorldThing trail = WorldThingFactory.create("trail", 
					"A trail of pebbles, each spaced about an inch and a half apart. The pebbles have " +
					"a thin coat of dust, concealing a bland grey color. The stones could hardly be " +
					"less interesting if someone ran road gravel through a rock polisher.",
					PossibleStates.NORMAL).makeUnmovable().finish();
		
		pebbleHall.addSpecialObject(trail);
		trail.setParent(pebbleHall);
		
		// That's it for this series of rooms
		
		return pebbleHall;
	}
	
	/**
	 * Creates the part of the map that contains the clay mound
	 * @return The first room of the collection containing the clay mound
	 * @throws InvalidActionException If you try to add something to a non-container
	 */
	private WorldRoom createClayMoundRooms() throws InvalidActionException {
		// The room at the end, with the mound in it
		
		WorldRoom moundRoom = new WorldRoom(null, null, "mound_room",
					"In the middle of a floor is a large clay mound. The walls and the floor " +
					"are covered in marks and handprints from people who have touched the mound. " +
					"The only clean thing in the room is the portrait on the eastern wall, opposite the door.",
					PossibleStates.NORMAL);
		
		moundRoom.addSpecialObject(mound);
		mound.setParent(moundRoom);

		// TODO: Add necessary stuff to make the spade have to be used on the mound to get to the key.
		
		WorldThing portrait = WorldThingFactory.create("portrait",
					"A large oil portrait of Grover the Great, 3rd Earl of Umptonshire. After failing at " +
					"a series of service industry jobs, he attempted to get a degree and then traveled " +
					"both near and far. He eventually made his fortune in the publishing industry and " +
					"retired quite wealthy.",
					PossibleStates.NORMAL).makeUnmovable().finish();
		
		moundRoom.addSpecialObject(portrait);
		portrait.setParent(moundRoom);
		
		// That's all on this map segment
		
		return null;
	}
}
