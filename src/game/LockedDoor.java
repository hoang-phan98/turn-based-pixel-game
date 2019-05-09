package game;

import edu.monash.fit2099.demo.WindowSmashAction;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * Represents a non-traversable terrain to Actors that can be unlocked
 */
public class LockedDoor extends Ground {
	public LockedDoor() {
		super(']');
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		return new Actions(new UnlockDoorAction(direction, location));
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
