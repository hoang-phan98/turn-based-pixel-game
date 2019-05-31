package game;

import edu.monash.fit2099.engine.Item;

/**
 * A special item that can be used to walk around the moon base
 */
public class SpaceSuit extends Item {

	public SpaceSuit() {
		super("Space suit", 'S');
		this.addSkill(Skills.MOONWALKER);
	}
	
}
