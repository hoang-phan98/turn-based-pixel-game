package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.*;

public class Ninja extends Actor {
	
	// Ninjas have 25 hitpoints and are always represented with an n
	public Ninja(String name, Actor player) {
		super(name, 'n', 5, 25);
		addBehaviour(new StunAndMoveBack(player));
	}
	
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}
	
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			return action;
		}
		return null;
	}
	

}
