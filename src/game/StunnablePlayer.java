package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Player;
import edu.monash.fit2099.engine.SkipTurnAction;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * An extension of the Player class that supports the Stun effect of Ninjas
 */
public class StunnablePlayer extends Player {
	private Location safety;
	private int stunCounter = 0;
	private int oxygenPoints = 0;
	
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
		if(map.groundAt(map.locationOf(this)) instanceof Floor) {
			this.safety = map.locationOf(this);
		}
		if(this.hasSkill(Skills.PATIENCE)) {
			for(Action action: actions) {
				if(action instanceof PickUpItemAction) {
					actions.remove(action);
				}
				if(action instanceof ProduceOxygenTankAction) {
					actions.remove(action);
				}
			}
			this.removeSkill(Skills.PATIENCE);
		}
		
		if(this.stunCounter > 0) {
			this.decreaseStunCounter();
			return new SkipTurnAction();
		}
		for(Item item: this.getInventory()) {
			if(item instanceof OxygenTank) {
				this.stockUpOxygen();
				this.removeItemFromInventory(item);
			}
		}
		if(map.locationOf(this).getGround() instanceof MoonGround) {
			if(this.oxygenPoints==0) {
				return new ReturnToEarthAction(this.safety, this);
			}
			this.useOxygen();
		}
		return super.playTurn(actions, map, display);
	}
	
	/**
	 * Call this method when the Ninja successfully stuns the player
	 * The effect will last for two turns
	 */
	public void increaseStunCounter(int num) {
		this.stunCounter += num;
	}
	
	/**
	 * Decrease stunCounter by 1
	 */
	public void decreaseStunCounter() {
		this.stunCounter -= 1;
	}
	
	public void stockUpOxygen() {
		this.oxygenPoints += 10;
	}
	
	public void useOxygen() {
		this.oxygenPoints -= 1;
	}
	
	/**
	 * @return The current value of the stun counter
	 * 
	 */
	public int getStunCounter() {
		return this.stunCounter;
	}
}
