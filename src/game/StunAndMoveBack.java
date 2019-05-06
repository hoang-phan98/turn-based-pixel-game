package game;

import java.util.Random;

import edu.monash.fit2099.engine.*;


public class StunAndMoveBack extends Action implements ActionFactory {
	private Actor target;
	private Random rand = new Random();
	
	public StunAndMoveBack(Actor actor) {
		this.target = actor;
	}
	
	private int distance(Location a, Location b) {
		return Math.max(Math.abs(a.x() - b.x()), Math.abs(a.y() - b.y()));
	}
	
	@Override
	public Action getAction(Actor actor, GameMap map) {
		return this;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);

		int currentDistance = distance(here, there);
		
		// Do nothing if player not within 5 squares
		if (currentDistance > 4) {
			return actor + " does nothing";
		}
		
		// 50% chance of not stunning the player
		if (rand.nextBoolean()) {
			// Move one square away from player
			for (Exit exit : here.getExits()) {
				Location destination = exit.getDestination();
				if (destination.canActorEnter(actor)) {
					int newDistance = distance(destination, there);
					if (newDistance > currentDistance) {
						map.moveActor(actor, destination);
						return actor + " misses " + target + ".";
					}
				}
			}
		}		
		
		// Stun the player

		
		// Move one square away from player
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				int newDistance = distance(destination, there);
				if (newDistance > currentDistance) {
					map.moveActor(actor, destination);
					return menuDescription(actor);
				}
			}
		}
		
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " has stunned " + target + "!";
	}

	@Override
	public String hotKey() {
		return null;
	}



}
