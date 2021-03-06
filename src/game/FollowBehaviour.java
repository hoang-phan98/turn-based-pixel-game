package game;

import edu.monash.fit2099.engine.*;

/**
 * Moves the Actor towards the another Actor each turn
 * 
 */
public class FollowBehaviour implements ActionFactory {

	private Actor target;

	public FollowBehaviour(Actor subject) {
		this.target = subject;
	}

	@Override
	/**
	 * Returns an action which moves the Actor towards the target,
	 * unless they are already next to them.
	 */
	public Action getAction(Actor actor, GameMap map) {
		if(map.locationOf(this.target) == null) {
			return new SkipTurnAction();
		}
		
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);

		int currentDistance = distance(here, there);
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				int newDistance = distance(destination, there);
				if (newDistance < currentDistance) {
					return new MoveActorAction(destination, exit.getName());
				}
			}
		}

		return null;
	}

	// Manhattan distance.
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}