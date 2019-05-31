package game;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/* 
 * 
 */ 
 
public class OxygenDispenser extends Item {
	/***
	 * 
	 * @param map, the map the item is to be created on
	 * @param location, the location the item is put on
	 */
	public OxygenDispenser(GameMap map, Location location) {
		super("Oxygen Dispenser", 'D');
			allowableActions.clear();
			allowableActions.add(new ProduceOxygenTankAction(location, map));
	}
}
