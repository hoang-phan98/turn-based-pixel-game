package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Represents the Action allowing the Player to talk to Q. 
 */
public class TalkToQAction extends Action {

	@Override
	/**
	 * Q's response will vary depending on whether the player 
	 * has the rocket plan in their inventory
	 */
	public String execute(Actor actor, GameMap map) {
		for(Item item: actor.getInventory()) {
			if(item.toString() == "Rocket Plan") {
				return "Q: Hand them over, I don’t have all day!";
			}
		}
		return "Q: I can give you something that will help, but I’m going to need the plans";
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return "Talk to Q";
	}

	@Override
	public String hotKey() {
		return "";
	}

}
