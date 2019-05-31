package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;

public class MoonGround extends Ground {

	public MoonGround() {
		super(',');
	}
	
	@Override
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
