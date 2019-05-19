package game;

import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Weapon;

public class SquirtWaterAction extends Action {
	private Actor actor;
	private Actor target;
	private Random rand = new Random();
	private Weapon weapon;
	
	public SquirtWaterAction(Actor actor, Actor target) {
		this.actor = actor;
		this.target = target;
		this.weapon = actor.getWeapon();
	}
	
	@Override
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
