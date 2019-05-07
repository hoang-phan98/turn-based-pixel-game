package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.*;

public class Goon extends Actor {
	
	double ranInt = Math.random();
	// Goons have 100 hitpoints (twice that of a Grunt) and are always represented with a o
	public Goon(String name, Actor player) {
		super(name, 'o', 5, 100);
		Item key = new Key("key", 'k');
		this.addItemToInventory(key);
		addBehaviour(new FollowBehaviour(player));
		//addBehaviour(new InsultAction(player));
	}

	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}
	//A goon hit will have double the hitpoints of a Grunt slap
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		if (ranInt<=0.9) {
			return new IntrinsicWeapon(10, "Insults and hits");
		}
		return new IntrinsicWeapon(10, "hits");
	}

	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		
		return super.playTurn(actions,  map,  display);
	}
}
