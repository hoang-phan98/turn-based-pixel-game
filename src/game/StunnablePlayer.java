package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Player;
import edu.monash.fit2099.engine.SkipTurnAction;

/**
 * An extension of the Player class that supports the Stun effect of Ninjas
 */
public class StunnablePlayer extends Player {
	
	private int stunCounter = 0;
	
	public StunnablePlayer(String name, char displayChar, int priority, int hitPoints) {
		super(name, displayChar, priority, hitPoints);
	}
	
	@Override
	/**
	 * If the stunCounter of the player is greater than 0, their turn will be skipped
	 * and the counter decreases by 1
	 * Otherwise behaves exactly as the original Player class
	 */
	public Action playTurn(Actions actions, GameMap map, Display display) {
		if(this.stunCounter > 0) {
			this.decreaseStunCounter();
			return new SkipTurnAction();
		}
		return super.playTurn(actions, map, display);
	}
	
	/**
	 * Call this method when the Ninja successfully stuns the player
	 * The effect will last for two turns
	 */
	public void increaseStunCounter() {
		this.stunCounter += 2;
	}
	
	/**
	 * Decrease stunCounter by 1
	 */
	public void decreaseStunCounter() {
		this.stunCounter -= 1;
	}
	
	/**
	 * @return The current value of the stun counter
	 * @return
	 */
	public int getStunCounter() {
		return this.stunCounter;
	}
}
