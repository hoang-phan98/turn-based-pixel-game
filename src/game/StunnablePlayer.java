package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Player;
import edu.monash.fit2099.engine.SkipTurnAction;

public class StunnablePlayer extends Player {
	
	private int stunCounter = 0;
	
	public StunnablePlayer(String name, char displayChar, int priority, int hitPoints) {
		super(name, displayChar, priority, hitPoints);
	}
	
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		if(this.stunCounter > 0) {
			this.decreaseStunCounter();
			return new SkipTurnAction();
		}
		return super.playTurn(actions, map, display);
	}
	
	public void increaseStunCounter() {
		this.stunCounter += 2;
	}
	
	public void decreaseStunCounter() {
		this.stunCounter -= 1;
	}
	
	public int getStunCounter() {
		return this.stunCounter;
	}
}
