package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class RefillPistolAction extends Action {

	@Override
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
