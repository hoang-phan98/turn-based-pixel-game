package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.DropItemAction;

/**
 * An Item that will be needed for BuildRocketAction. 
 * Only Doctor Maybe will have a RocketEngine in his inventory
 * at the start of the game.
 * Can be picked up my any player
 */
public class RocketEngine extends Item {
	public RocketEngine(String name, char displayChar) {
		super(name, displayChar);
		allowableActions = new Actions(new DropItemAction(this));
	}
}
