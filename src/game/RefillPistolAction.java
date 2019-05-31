package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Action used by the player in order to refill an empty water pistol
 * when they are next to a water terrain
 */
public class RefillPistolAction extends Action {

	@Override
	/**
	 * If the Actor does not have a water pistol, does nothing and tells them to find it
	 * If the Water pistol is already full, does nothing and informs them of this
	 * If the Water pistol is empty, sets its empty status to false
	 */
	public String execute(Actor actor, GameMap map) {
		if(!(actor.getWeapon() instanceof WaterPistol)) {
			return actor + " must first find the Water Pistol!";
		}
		
		if(((WaterPistol) actor.getWeapon()).isEmpty) {
			((WaterPistol) actor.getWeapon()).refill();
			return "The Water Pistol has been refilled!";
		}
		
		return "The Water Pistol is already full!";
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " refills the water pistol";
	}

	@Override
	public String hotKey() {
		return "";
	}

}
