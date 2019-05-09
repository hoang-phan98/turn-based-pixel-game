package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.DropItemAction;

/**
 * Represent an Item that will be needed for the UnlockDoorAction
 * All NPC apart from Q and Doctor Maybe will have a Key in their inventory
 * at the start of the game
 */
public class Key extends Item {
	public Key() {
		super("Key", 'k');
		allowableActions = new Actions(new DropItemAction(this));
	}
}
