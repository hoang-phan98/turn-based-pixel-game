package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.*;

public class Goon extends Actor {

	// Goons have 100 hitpoints (twice that of a Grunt) and are always represented with a o
	public Goon(String name, Actor player) {
		super(name, 'o', 5, 100);
		addBehaviour(new FollowBehaviour(player));
	}

	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}

	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		//Insert code to throw insult
		//10% chance insult on every turn
		
		return super.playTurn(actions,  map,  display);
	}
}
