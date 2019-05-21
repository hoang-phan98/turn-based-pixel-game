package game;

import edu.monash.fit2099.engine.Item;

public class SpaceSuit extends Item {

	public SpaceSuit() {
		super("Space suit", 'S');
		this.addSkill(Skills.SPACETRAVELLER);
	}
	
}
