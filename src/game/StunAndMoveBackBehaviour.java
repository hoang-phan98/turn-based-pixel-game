package game;

import java.util.Random;

import edu.monash.fit2099.engine.*;

/**
 *	Represents the behaviour of a ninja once it comes in range of a player 
 *
 */
public class StunAndMoveBackBehaviour extends Action implements ActionFactory {
	private Actor target;
	private Random rand = new Random();
	
	public StunAndMoveBackBehaviour(Actor actor) {
		this.target = actor;
	}
	
	private int distance(Location a, Location b) {
		return Math.max(Math.abs(a.x() - b.x()), Math.abs(a.y() - b.y()));
	}
	
	@Override
	/**
	 * If the target is no longer on the map, actor will skip the turn
	 */
	public Action getAction(Actor actor, GameMap map) {
		if(map.locationOf(this.target) == null) {
			return new SkipTurnAction();
		}
		return this;
	}
	
	@Override
	/**
	 * Do nothing if the player is not within 5 squares
	 * Has a 50% chance of missing. If it hits the target is stunned for 2 rounds
	 * Then moves one step away from the target
	 */
	public String execute(Actor actor, GameMap map) {
		String description = "";
		
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);

		int currentDistance = distance(here, there);
		
		// Do nothing if player not within 5 squares
		if (currentDistance > 4) {
			return actor + " does nothing";
		}
		
		// 50% chance of not stunning the player
		if (rand.nextBoolean()) {
			description = actor + " has missed the player";
		} else {
			// Stun the player
			if(this.target instanceof StunnablePlayer) {
				if(((StunnablePlayer) this.target).getStunCounter() == 0) {
					((StunnablePlayer) this.target).increaseStunCounter();
					description = actor + " has stunned the player";
				} else {
					description = "The player is already stunned! The powder has no effect";
				}
			}
		}
		
		// Move one square away from player
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				int newDistance = distance(destination, there);
				if (newDistance > currentDistance) {
					map.moveActor(actor, destination);
					return description;
				}
			}
		}
		
		return description;
	}
	
	
	@Override
	public String menuDescription(Actor actor) {
		return "";
	}

	@Override
	public String hotKey() {
		return "";
	}



}
