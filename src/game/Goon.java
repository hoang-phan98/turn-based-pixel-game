package game;

import edu.monash.fit2099.engine.*;

/**
 * Represents a class of hostile actors towards the player, extended from Grunts that 
 * deals twice the damage
 */
public class Goon extends Grunt {
	/**
	 * Goons will have the same hp as Grunts and will also be represented with a 'g'
	 */
	public Goon(String name, Actor player) {
		super(name, player);
	}

	/**
	 * A Goon's attack will do twice the damage of a Grunt
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(8, "hits");
	}

}
