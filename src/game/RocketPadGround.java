package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * A special extension of the Ground class that only the player may enter
 * This is to prevent other actors from using the Rocket to travel between maps
 */
public class RocketPadGround extends Ground {

	public RocketPadGround() {
		super('x');
	}
	
	@Override
	/**
	 * Only the player may enter this location
	 */
	public boolean canActorEnter(Actor actor) {
		if(actor instanceof StunnablePlayer) {
			return true;
		}
		return false;
	}

}
