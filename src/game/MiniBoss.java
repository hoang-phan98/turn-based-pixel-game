package game;

import java.util.Random;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.*;

/**
 * Represents a class of hostile actors towards the player
 */
public class MiniBoss extends Actor {
	
	private Random rand = new Random();
	

	/**
	 * MiniBoss will have 1hp and is represented with an 'o'
	 */
	public MiniBoss(String name) {
		super(name, 'd', 5, 1);
		Item rocketEngine = Item.newInventoryItem("Rocket Engine", 'e');
		this.addItemToInventory(rocketEngine);
	}
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}
	/**
	 * A MiniBoss's attack will do a fraction the damage of a Grunt
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(1, "feebly slaps");
	}
	/**
	 * MiniBoss will stay still until encountered by a Player. If they are next to them,
	 * then they will perform a random allowed action other than DropItem and 
	 * PickUpItem.
	 */
	@Override
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
