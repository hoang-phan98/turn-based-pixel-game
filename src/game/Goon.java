package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.*;

public class Goon extends Actor {
	private Random rand = new Random();
	
	double ranInt = Math.random();
	// Goons have 100 hitpoints (twice that of a Grunt) and are always represented with a o
	public Goon(String name, Actor player) {
		super(name, 'o', 5, 10);
		Item key = new Key("key", 'k');
		this.addItemToInventory(key);
		addBehaviour(new FollowBehaviour(player));
		addBehaviour(new InsultBehaviour(player));
	}

	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}
	//A goon hit will have double the hitpoints of a Grunt slap
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "hits");
	}

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
