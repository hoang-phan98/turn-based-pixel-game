package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.SkipTurnAction;

public class WanderBehaviour implements ActionFactory {
	private Random rand = new Random();
	private ArrayList<Exit> exits = new ArrayList<Exit>();
	
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		for (Exit exit : here.getExits()) {
			exits.add(exit);
		}
		
		Exit exit = exits.get(rand.nextInt(exits.size()));
		Location destination = exit.getDestination();
		
		int counter = 0;
		while(!destination.canActorEnter(actor) && counter < exits.size()) {
			exit = exits.get(rand.nextInt(exits.size()));
			destination = exit.getDestination();
			counter++;
		}
		
		if (destination.canActorEnter(actor)) {
			return new MoveActorAction(destination, exit.getName());	
		}
		
		return new SkipTurnAction();
	}

}
