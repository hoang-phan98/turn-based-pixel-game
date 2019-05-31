package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * An Action that moves the Actor.
 */
public class ReturnToEarthAction extends Action {

	private Location moveToLocation;
	private String hotKey;
	private Actor actor;

	/**
	 * Constructor to create an Action that will move the Actor to a given Location.
	 *
	 *
	 * @param moveToLocation Location to move to
	 * @param actor The actor being moved
	 */
	public ReturnToEarthAction(Location moveToLocation, Actor actor) {
		this.moveToLocation = moveToLocation;
		this.hotKey = "";
	}

	/**
	 * Allow the Actor to be moved.
	 *
	 * Overrides Action.execute()
	 *
	 * @see Action#execute(Actor, GameMap)
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of the Action suitable for the menu
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		map.moveActor(actor, moveToLocation);
		return menuDescription(actor);
	}

	/**
	 * Returns a description of this movement suitable to display in the menu.
	 *
	 * @param actor The actor performing the action.
	 * @return a String, description of what the action has executed
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " out of air, safety removed back to Earth!";
	}

	/**
	 * Returns this Action's hotkey.
	 *
	 * @return the hotkey
	 */
	@Override
	public String hotKey() {
		return hotKey;
	}
}
