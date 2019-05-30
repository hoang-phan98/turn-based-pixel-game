package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class InsultAction extends Action {
//	private Actor actor;
	private Actor target;
	private Random rand= new Random();
	private Double chance;
	private String result;
	private String[] listOfInsults = {"You will never succeed","You've gained weight","Your hairline is receeding"};
	
//	private List<String> listOfInsults = new ArrayList<String>();

	public InsultAction(Actor target) {
//		this.actor = actor;
		this.target = target;
	}
	/**
	 * Overrides Action.execute()
	 *
	 * @see Action#execute(Actor, GameMap)
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a string description of the action which occurs
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

			chance = rand.nextDouble();
			if (chance<0.3) {
				result = actor + " yells " + listOfInsults[0];
			}
			else if (chance<0.6) {
				result = actor + " yells " + listOfInsults[1];
			}
			else if (chance<0.9) {
				result = actor + " yells " + listOfInsults[2];
			}
		return result;

	}

	/**
	 * Overrides Action.menuDescription()
	 *
	 * @see Action#menuDescription(Actor)
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " insults " + target;
	}
	/**
	 * Overrides Action.hotKey()
	 *
	 * @see Action#hotKey()
	 */
	@Override
	public String hotKey() {
		return "";
	}

}
