package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * Represents a non-traversable terrain to Actors but allows them to 
 * refill the water pistol
 */
public class Water extends Ground {

	public Water() {
		super('~');
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	/**
	 * Support the action which allows the Actor to refill their water pistol
	 */
	public Actions allowableActions(Actor actor, Location location, String direction){
		return new Actions(new RefillPistolAction());
	}
}