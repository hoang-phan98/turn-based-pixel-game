package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.Item;

public class SpaceTravelAction extends MoveActorAction {
	private Location moveTo;
	
	public SpaceTravelAction(Location moveToLocation, String direction, String hotKey) {
		super(moveToLocation, direction, hotKey);
		this.moveTo = moveToLocation;
	}


	public SpaceTravelAction(Location moveToLocation, String direction) {
		super(moveToLocation, direction);
		this.moveTo = moveToLocation;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		for(Item item: actor.getInventory()) {
			if(item instanceof SpaceSuit) {
				map.moveActor(actor, moveTo);
				return menuDescription(actor);
			}
		}
		return "You need SpaceSuit to travel Space";
	}
	

}
