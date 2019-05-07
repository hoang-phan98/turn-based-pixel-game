package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class Q extends Actor{

	public Q(String name, char displayChar, int priority, int hitPoints) {
		super(name, displayChar, priority, hitPoints);
		addBehaviour(new WanderBehaviour());
	}
	
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();
	
	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}	
	
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		actions.add(new GivePlanAction(otherActor, this));
		actions.add(new TalkToQAction(otherActor, this));
		return actions;
	}
	
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		return super.playTurn(actions,  map,  display);
	}
}
