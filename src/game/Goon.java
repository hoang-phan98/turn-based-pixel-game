package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.*;

/**
 * Represents a class of hostile actors towards the player
 */
public class Goon extends Actor {
	private Random rand = new Random();
	
	double ranInt = Math.random();
	
	/**
	 * Goons will have 10hp and is represented with an 'o'
	 */
	
	public Goon(String name, Actor player) {
		super(name, 'o', 5, 10);
		Item key = new Key();
		this.addItemToInventory(key);
		addBehaviour(new FollowBehaviour(player));
		addBehaviour(new InsultBehaviour(player));
	}

	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}

	/**
	 * A Goon's attack will do twice the damage of a Grunt
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(8, "hits");
	}

	@Override
	/**
	 * Goons will always try to follow the player first. If the are next to them,
	 * then they will perform a random allowed action other than DropItem and 
	 * PickUpItem.
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
