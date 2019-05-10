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
	private boolean blocked = false;
	
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
	 * Do nothing if the target is not within 5 squares in either direction,
	 * or if they are not in a straight line with the ninja.
	 * Has a 50% chance of missing and is blocked by walls and doors.
	 * If it hits the target's turn is skipped for 2 rounds.
	 * Has no effect if the target is already stunned.
	 * The ninja then moves one step away from the target
	 */	
	public String execute(Actor actor, GameMap map) {
		String description = "";
		
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);
		Range xs, ys;

		int currentDistance = distance(here, there);
		
		// Do nothing if target not within 5 squares in either direction 
		// or if the ninja is not a straight line with the target
		if ((currentDistance > 4) || !(here.x() == there.x() || here.y() == there.y())) {
			return actor + " does nothing";
		} else {
			// Checks whether the stun powder can be blocked by any terrain in the way
			xs = new Range(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
			ys = new Range(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);
			for (int x : xs) {
				for (int y : ys) {
					if(map.at(x, y).getGround().blocksThrownObjects()) {
						description = "The bag of powder has been blocked!";
						this.blocked = true;
					}
				}
			}
			// 50% chance of not stunning the player
			if (rand.nextBoolean() && !this.blocked) {
				description = actor + " has missed the player";
			} else {
				// Stun the player
				if(this.target instanceof StunnablePlayer && !this.blocked) {
					if(((StunnablePlayer) this.target).getStunCounter() == 0) {
						((StunnablePlayer) this.target).increaseStunCounter(2);
						description = actor + " has stunned the player";
					} else {
						description = "The player is already stunned! The powder has no effect";
					}
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
