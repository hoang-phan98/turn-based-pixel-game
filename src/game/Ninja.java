package game;

import edu.monash.fit2099.engine.*;

/**
 * Represents a class of hostile actors towards the player
 */
public class Ninja extends Actor {
	
	/**
	 * Ninjas are represented with an 'n' and has 5hp
	 */
	public Ninja(String name, Actor player) {
		super(name, 'n', 5, 5);
		Item key = new Key();
		this.addItemToInventory(key);
		addBehaviour(new StunAndMoveBackBehaviour(player));
	}
	
	private ActionFactory ninjaBehaviour = new StunAndMoveBackBehaviour(null);

	private void addBehaviour(ActionFactory behaviour) {
		this.ninjaBehaviour = behaviour;
	}
	
	@Override
	/**
	 * Ninjas have a unique behaviour which comprises of them doing nothing until
	 * they sense that a player in within 5 squares of them. Once this is happens,
	 * Ninjas will try to stun the player and moves 1 square away. They will never 
	 * perform random actions as the other NPC actors.
	 */
	public Action playTurn(Actions actions, GameMap map, Display display) {
		return this.ninjaBehaviour.getAction(this, map);
	}
	

}
