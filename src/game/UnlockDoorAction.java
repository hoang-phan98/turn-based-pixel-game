package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * Represents the action used to unlock a locked door
 */
public class UnlockDoorAction extends Action{
	private String direction;
	private Location doorLocation;
	
	public UnlockDoorAction(String direction, Location doorLocation) {
		this.direction = direction;
		this.doorLocation = doorLocation;
	}

	@Override
	/**
	 * First checks if the player has a key in their inventory
	 * If they do, removes the key from the inventory and replace
	 * the door with a traversable floor terrain permanently
	 */
	public String execute(Actor actor, GameMap map) {
		for(Item item: actor.getInventory()) {
			if(item instanceof Key) {
				map.add(new Floor(), this.doorLocation);
				actor.removeItemFromInventory(item);
				return "Door has been unlocked";
			}
		}
		return "You must find a key in order to unlock the door";
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Unlock the Door to the " + this.direction;
	}

	@Override
	public String hotKey() {
		return "";
	}

}
