package game; 

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class QuitGame extends Action {
	
	public QuitGame(Actor actor) {
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
		map.removeActor(actor);
		return "You have quit the game";
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Quit game";
	}

	@Override
	public String hotKey() {
		return "";
	}
	
}
