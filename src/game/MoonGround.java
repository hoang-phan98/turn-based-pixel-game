package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;

/**
 * Extension of the Ground class that represents the terrain on the 
 * moon base.
 */
public class MoonGround extends Ground {

	public MoonGround() {
		super(',');
	}
	
	@Override
	/** 
	 * Can only be traversed if the Actor has cybernetic enhancements
	 * or if they possess a spacesuit with the skill space traveller
	 */
	public boolean canActorEnter(Actor a) {
		
		if( a.hasSkill(Skills.CYBERENHANCEMENT)) {
			return true;
		}
		
		for(Item item : a.getInventory()) {
			if(item.hasSkill(Skills.MOONWALKER)) {
				return true;
			}
		}
		
		return false;
	}
}
