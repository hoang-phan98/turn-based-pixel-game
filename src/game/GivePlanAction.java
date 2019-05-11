package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Represents the Action allowing the Player to give Q the Rocket Plan in exchange
 * for the Rocket Body
 */
public class GivePlanAction extends Action {
	private Actor target;
	private RocketBody rocketBod = new RocketBody();
	
	public GivePlanAction(Actor target) {
		this.target = target;
	}
	
	@Override
	/**
	 * If the Player has the Rocket Plan in the inventory, it is replaced with the
	 * Rocket Body, and Q disappears from the map. Otherwise Q will tell them to
	 * go find the plans first.
	 */
	public String execute(Actor actor, GameMap map) {
		for(Item item: actor.getInventory()) {
			if(item instanceof RocketPlan) {
				actor.removeItemFromInventory(item);
				actor.addItemToInventory(rocketBod);
				map.removeActor(this.target);
				return "Q disappears with a cheery wave!";
			}
		}
		return "Q said: Go find the Rocket Plan first!";
	}
	@Override
	public String menuDescription(Actor actor) {
		return "Give Rocket Plan to Q";
	}
	@Override
	public String hotKey() {
		return "";
	}
	
	
}
