package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * Extension of the Actor class which represents the Final Boss, Yugo Maxx
 * He does the same damage as a Goon, and is invulnerable while his exoskeleton
 * stays intact
 */
public class FinalBoss extends Actor {
	protected boolean invulnerable = true;
	private Random rand = new Random();
	
	/**
	 * Yugo Maxx follows the player by default and has cybernetic enhancement
	 * which allows him to move around on the moon freely
	 * 
	 * @param player the player to be followed
	 */
	public FinalBoss(Actor player) {
		super("Yugo Maxx", 'Y', 5, 100);
		addBehaviour(new FollowBehaviour(player));
		this.addSkill(Skills.CYBERENHANCEMENT);
	}
	
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}

	
	@Override
	/**
	 * Does no damage if Yugo Maxx is invulnerable
	 */
	public void hurt(int hitpoint) {
		if(this.invulnerable) {
			return;
		} else {
			this.hitPoints -= hitpoint;
		}
	}
	
	/**
	 * Breaks Yugo Maxx's exoskeleton and his invulnerability status
	 */
	public void breakExoskeleton() {
		this.invulnerable = false;
	}
	
	@Override
	/**
	 * Yugo Maxx does the same damage as a Goon
	 */
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(8, "eviscerates");
	}
	
	@Override
	/**
	 * Other Actors may either attack Yugo, or squirt water at him
	 * in order to break his armour
	 */
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		actions.add(new AttackYugoAction(otherActor, this));
		actions.add(new SquirtWaterAction(otherActor, this));
		return actions;
	}
	
	@Override
	/**
	 * Yugo Maxx will always try to follow the player first. If the are next to them,
	 * then they will perform a random allowed action other than DropItem,
	 * PickUpItem and UnlockDoorAction.
	 */
	public Action playTurn(Actions actions, GameMap map, Display display) {
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		
		Action action = new DropItemAction(null);
		while(action instanceof DropItemAction || action instanceof PickUpItemAction || action instanceof UnlockDoorAction) {
			action = actions.get(rand.nextInt(actions.size()));
		}
		
		return action;
	}

}
