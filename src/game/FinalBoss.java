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

public class FinalBoss extends Actor {
	protected boolean invulnerable = true;
	private Random rand = new Random();
	
	public FinalBoss(Actor player) {
		super("Yugo Max", 'Y', 5, 100);
		addBehaviour(new FollowBehaviour(player));
	}
	
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
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
