package game; 
 
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction; 
 
public class ProduceOxygenTankAction extends Action {  
	private Location location;
	private GameMap mapTo;
	
	public ProduceOxygenTankAction(Location location, GameMap map) {
		this.location = location;
		this.mapTo = map;
	}
	
	@Override
	/**
	 * Checks if the location contains the Rocket Engine as well as Rocket Body
	 * If it does, adds the Rocket to the location which the player can use to go to the Moon
	 * 
	 * @param actor The actor performing the action, which will be the Player
	 * @return a description of what happened that can be displayed to the user.
	 */
	public String execute(Actor actor, GameMap map) {
		Item oxygenTank = new OxygenTank();
		map.addItem(oxygenTank, this.location.x(), this.location.y());
		return "Oxygen Tank has been dispensed!";
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Press button on Oxygen Dispenser to create Oxygen Tank";
	}

	@Override
	public String hotKey() {
		return "";
	}
	
}
