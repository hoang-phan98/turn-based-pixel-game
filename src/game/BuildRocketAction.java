package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Item;

/**
 * Represents the Action that can be used by the user in order 
 * to build the Rocket once they are next to the Rocket Pad
 */
public class BuildRocketAction extends Action {
	private Location location;
	
	public BuildRocketAction(Actor actor, Location location) {
		this.location = location;
	}
	
	
	@Override
	/**
	 * Checks if the location contains the Rocket Engine as well as Rocket Body
	 * If it does, adds the Rocket to the location and removes the player,
	 * thus ending the game
	 * 
	 * @param actor The actor performing the action, which will be the Player
	 * @return a description of what happened that can be displayed to the user.
	 */
	public String execute(Actor actor, GameMap map) {
		Item rocket = new Item("Rocket", 'R');
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
			map.addItem(rocket, this.location.x(), this.location.y());
			map.add(new Floor(), this.location);
			map.removeActor(actor);
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
