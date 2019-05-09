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
	private boolean canAttack = false;
	

	/**
	 * MiniBoss will have 5hp (half of a Grunt) and is represented with an 'o'
	 */
	public MiniBoss(String name) {
		super(name, 'd', 5, 5);
		Item rocketEngine = new RocketEngine();
		this.addItemToInventory(rocketEngine);
	}
	
	/**
	 * A MiniBoss's attack will half the damage of a Grunt
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(2, "feebly slaps");
	}
	/**
	 * MiniBoss will stay still unless encountered by another Actor
	 * at which point it will have a 50% chance of attacking and 50%
	 * chance of skipping its turn.
	 */
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		Action attack = null;
		
		for(Action action : actions) {
			if(action instanceof AttackAction) {
				this.canAttack = true;
				attack = action;
			}
		}
		
		if(rand.nextBoolean() && this.canAttack) {
			return attack;
		}
		
		return new SkipTurnAction();
	}
}
