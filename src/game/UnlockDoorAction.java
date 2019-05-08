package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class UnlockDoorAction extends Action{
	private String direction;
	private Location doorLocation;
	
	public UnlockDoorAction(String direction, Location doorLocation) {
		this.direction = direction;
		this.doorLocation = doorLocation;
	}

	@Override
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
