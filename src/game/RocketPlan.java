package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * An Item that can be exchanged for the RocketBody via Q
 * Is found in a locked room and can be picked up
 */
public class RocketPlan extends Item {
	public RocketPlan() {
		super("Rocket Plan", 'p');
		allowableActions = new Actions(new PickUpItemAction(this));
	}
}
