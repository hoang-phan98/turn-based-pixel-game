package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.*;

public class Ninja extends Actor {
	
	// Ninjas have 25 hitpoints and are always represented with an n
	public Ninja(String name, Actor player) {
		super(name, 'n', 5, 25);
		Item key = new Key("key", 'k');
		this.addItemToInventory(key);
		addBehaviour(new StunAndMoveBackBehaviour(player));
	}
	
	private ActionFactory ninjaBehaviour = new StunAndMoveBackBehaviour(null);

	private void addBehaviour(ActionFactory behaviour) {
		this.ninjaBehaviour = behaviour;
	}
	
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		return this.ninjaBehaviour.getAction(this, map);
	}
	

}
