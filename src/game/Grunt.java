package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a class of hostile actors towards the player
 */
public class Grunt extends Actor {
	private Random rand = new Random();

	/**
	 * Grunts have 10hp and is represented with a 'g'
	 */
	public Grunt(String name, Actor player) {
		super(name, 'g', 5, 10);
		Item key = new Key("key", 'k');
		this.addItemToInventory(key);
		addBehaviour(new FollowBehaviour(player));
	}

	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}
	
	
	@Override
	/**
	 * A Grunt's attack will deal 5 damage
	 */
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(5, "slaps");
	}

	@Override
	/**
	 * Grunts will always try to follow the player first. If the are next to them,
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
		while(action instanceof DropItemAction || action instanceof PickUpItemAction) {
			action = actions.get(rand.nextInt(actions.size()));
		}
		
		return action;
	}
}
