package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * Represents a special Item that supports the BuildRocketAction
 */
public class RocketPad extends Item {

	public RocketPad(GameMap map, Location location) {
		super("Rocket Pad", 'X');
		allowableActions.clear();
		allowableActions.add(new BuildRocketAction(location, map));
	}
}
