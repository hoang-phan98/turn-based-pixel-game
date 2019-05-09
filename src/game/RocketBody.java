package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.DropItemAction;

/**
 * An Item that will be needed for BuildRocketAction. 
 * Can be exchanged with the Rocket Plan via Q
 */
public class RocketBody extends Item {
	public RocketBody() {
		super("Rocket Body", 'b');
		allowableActions = new Actions(new DropItemAction(this));
	}
}
