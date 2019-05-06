package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;

public class Door extends Ground {
	private boolean isLocked = true;

	public Door() {
		super(']');
	}
	
	@Override
	/**
	 *  Actor will not be able to enter unless they have a Key, or if the Door is already unlocked
	 */
	public boolean canActorEnter(Actor actor) {
		if(!this.isLocked) {
			return true;
		}
		
		for(Item item : actor.getInventory()) {
			if(item.toString() == "key") {
				actor.removeItemFromInventory(item);
				this.isLocked = false;
				this.displayChar = '.';
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
