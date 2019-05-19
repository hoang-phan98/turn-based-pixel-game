package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.Item;

/**
 * Represents the Action that can be used by the user in order 
 * to build the Rocket once they are next to the Rocket Pad
 */
public class BuildRocketAction extends Action {
	private Location location;
	private GameMap mapTo;
	
	public BuildRocketAction(Location location, GameMap map) {
		this.location = location;
		this.mapTo = map;
	}
	
	
	@Override
	/**
	 * Checks if the location contains the Rocket Engine as well as Rocket Body
	 * If it does, adds the Rocket to the location which the player can use to go to the Moon
	 * 
	 * @param actor The actor performing the action, which will be the Player
	 * @return a description of what happened that can be displayed to the user.
	 */
	public String execute(Actor actor, GameMap map) {
		Item rocket = new Rocket();
		rocket.getAllowableActions().add(new MoveActorAction(mapTo.at(location.x(), location.y()), " to the Moon!"));
		
		boolean hasBody = false;
		boolean hasEngine = false;
		
		for(Item item: actor.getInventory()) {
			if(item instanceof RocketBody) {
				hasBody = true;
			}
			if(item instanceof RocketEngine) {
				hasEngine = true;
			}
		}
		if(hasBody && hasEngine) {
			for(Item item: actor.getInventory()) {
				if(item instanceof RocketBody) {
					actor.removeItemFromInventory(item);
				}
				if(item instanceof RocketEngine) {
					actor.removeItemFromInventory(item);
				}
			}
			map.addItem(rocket, this.location.x(), this.location.y());
			return "The Rocket has been created!";
		}

		return "You must first collect the Rocket Body and Rocket Engine!";
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Create the Rocket";
	}

	@Override
	public String hotKey() {
		return "";
	}
	
}
