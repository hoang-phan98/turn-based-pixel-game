package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A weapon extension thatcan be used in order to break
 * the final boss, Yugo Maxx's exoskeleton
 */
public class WaterPistol extends WeaponItem {
	protected boolean isEmpty = true;

	/**
	 * The water pistol does 5 damage to normal enemies
	 */
	public WaterPistol() {
		super("Water Pistol", '*', 5, "squirts");
	}
	
	/**
	 * Empties the pistol
	 */
	public void empty() {
		this.isEmpty = true;
	}
	
	/**
	 * Refills the pistol
	 */
	public void refill() {
		this.isEmpty = false;
	}
}
