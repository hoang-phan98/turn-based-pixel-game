package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Item;

public class BuildRocketAction extends Action {
	private Actor actor;
	private Location location;
	
	public BuildRocketAction(Actor actor, Location location) {
		this.actor = actor;
		this.location = location;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		Item rocket = new Item("Rocket", 'R');
		
		for(Item item : this.actor.getInventory()) {
			if(item.toString() == "Rocket Body") {
				for(Item item1: this.actor.getInventory()) {
					if(item1.toString() == "Rocket Engine") {
						map.addItem(rocket, this.location.x(), this.location.y());
						map.add(new Floor(), this.location);
						return "The Rocket has been created!";
					}
				}
				break;
			}

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
