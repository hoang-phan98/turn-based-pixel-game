package game;

import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Weapon;

/**
 * The action used to break Yugo Maxx's exoskeleton using a Water Pistol
 */
public class SquirtWaterAction extends Action {

	private Actor target;
	private Random rand = new Random();
	private Weapon weapon;
	
	public SquirtWaterAction(Actor actor, Actor target) {
		this.target = target;
		this.weapon = actor.getWeapon();
	}
	
	@Override
	/**
	 * If Yugo's exoskeleton is already broken, informs the player and does nothing
	 * If the player does not have the water pistol, tells them to go find it
	 * If the water pistol is empty, tells them to go refill it
	 * Otherwise, empties the water pistol and attempts to break Yugo's exoskeleton
	 * This has a 70% success rate (a number between 0-9 is greater than 2)
	 * If it fails, does nothing, otherwise remove invulnerability status from Yugo
	 */
	public String execute(Actor actor, GameMap map) {
		if(!((FinalBoss) this.target).invulnerable){
			return "Yugo's exoskeleton has already been broken";
		}
		
		if(!(this.weapon instanceof WaterPistol)) {
			return "You must first find the water pistol";
		} else {
			if (((WaterPistol) this.weapon).isEmpty){
				return "The water pistol is empty! You must refill it!";
			}
			
			int n = rand.nextInt(10);
			((WaterPistol) this.weapon).empty();
			if(n > 2) {				
				((FinalBoss) this.target).breakExoskeleton();
				return "Yugo's exoskeleton has been broken!";
			} else {				
				return "The water has no effect on the exoskeleton!";
			}
		}
		
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " squirt water at " + this.target;
	}

	@Override
	public String hotKey() {
		return "";
	}
	
}
