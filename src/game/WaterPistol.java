package game;

import edu.monash.fit2099.engine.WeaponItem;

public class WaterPistol extends WeaponItem {
	protected boolean isEmpty = true;

	public WaterPistol() {
		super("Water Pistol", '*', 5, "punches");
	}
	
	public void empty() {
		this.isEmpty = true;
	}
}
