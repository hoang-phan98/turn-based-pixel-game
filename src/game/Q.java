package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

/**
 * Represents a friendly character towards all other actors
 */
public class Q extends Actor{
	
	/**
	 *  Q will be represented with a 'Q' and have 100hp
	 */
	public Q() {
		super("Q", 'Q', 5, 100);
	}
	
	private ActionFactory actionFactory = new WanderBehaviour();
	
    @Override
    /**
     * Q supports actions allowing the otherActor to either talk to him
     * or give him the Rocket Plan in exchange for the Rocket Body
     */
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		actions.add(new GivePlanAction(this));
		actions.add(new TalkToQAction());
		return actions;
	}
	
	@Override
	/**
	 * Q will always randomly wander around the map
	 */
	public Action playTurn(Actions actions, GameMap map, Display display) {
		return this.actionFactory.getAction(this, map);
	}
}
