package game; 
 
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
 
public class ProduceOxygenTankAction extends Action {  
	private Location location;
	
	public ProduceOxygenTankAction(Location location, GameMap map) {
		this.location = location;
	}
	
	@Override
	/**
	 * Creates a new OxygenTank Item and adds it to the input location.
	 * Adds the skill PATIENCE to the actor performing the action.
	 * 
	 * @param actor The actor performing the action, which will be the Player
	 * @param map The map the actor is on
	 * @return a description of what happened that can be displayed to the user.
	 */
	public String execute(Actor actor, GameMap map) {
		Item oxygenTank = new OxygenTank();
		map.addItem(oxygenTank, this.location.x(), this.location.y());
		actor.addSkill(Skills.PATIENCE);
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
