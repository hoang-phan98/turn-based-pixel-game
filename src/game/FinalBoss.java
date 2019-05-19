package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.AttackAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;

public class FinalBoss extends Actor {
	private boolean invulnerable = true;
	
	public FinalBoss() {
		super("Yugo Max", 'Y', 5, 100);
	}
	
	@Override
	public void hurt(int hitpoint) {
		if(this.invulnerable) {
			return;
		} else {
			this.hitPoints -= hitpoint;
		}
	}
	
	public void breakExoskeleton() {
		this.invulnerable = false;
	}
	
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(8, "eviscerates");
	}
	
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		actions.add(new AttackAction(otherActor, this));
		actions.add(new SquirtWaterAction(otherActor, this));
		return actions;
	}

}
