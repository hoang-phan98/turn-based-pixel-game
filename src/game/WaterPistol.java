package game;

import edu.monash.fit2099.engine.WeaponItem;

public class WaterPistol extends WeaponItem {
	protected boolean isEmpty = true;

	public WaterPistol() {
		super("Water Pistol", '*', 0, "squirts");
	}
	
	public void empty() {
		this.isEmpty = true;
	}
	
	public void refill() {
		this.isEmpty = false;
	}
}
