package game;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/* 
 * In order to move around in the moonbase, the player must be wearing a spacesuit and have an adequate 
 * oxygen supply. 
 * Add a spacesuit item to the Earth lair map. Also add an oxygen dispenser. The oxygen dispenser has 
 * a button that, when pressed, causes the dispenser to produce an oxygen tank in its location on the next 
 * turn. The button does not work while the dispenser is producing the tank, or while there is an oxygen 
 * tank in the location. 
 * Every turn spent in the moonbase costs the player one point of oxygen. A new oxygen tank holds 10 
 * points worth of oxygen. If the player runs out of oxygen on the moon, a safety system automatically 
 * transports them back to the rockets location on Earth. 
 */ 
 

public class OxygenDispenser extends Item {

	public OxygenDispenser(GameMap map, Location location) {
		super("Oxygen Dispenser", 'D');
			allowableActions.clear();
			allowableActions.add(new ProduceOxygenTankAction(location, map));
	}
}
