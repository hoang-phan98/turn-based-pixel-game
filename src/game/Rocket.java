package game;

import edu.monash.fit2099.engine.Item;

/**
 * Represents the Rocket item that is created by the BuildRocketAction
 * and can be used to travel to/from the moon
 */
public class Rocket extends Item {
	public Rocket() {
		super("Rocket", 'R');
		allowableActions.clear();
	}
}
