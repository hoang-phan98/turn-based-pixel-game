package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Weapon;

public class InsultBehaviour extends Action implements ActionFactory {
//	private Actor actor;
	private Actor target;
	private Random rand= new Random();
	private Double chance;
	private String result;
	private String[] listOfInsults = {"You will never succeed","You've gained weight","Your hairline is receeding"};
	
//	private List<String> listOfInsults = new ArrayList<String>();

	public InsultBehaviour(Actor target) {
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

		Weapon weapon = actor.getWeapon();
		int damage = weapon.damage();
		

		if (rand.nextBoolean()) {
			return actor + " misses " + target + ".";
		}
		if (rand.nextDouble()<0.1) {
			chance = rand.nextDouble()*100;
			if (chance<3) {
				result = actor + " yells " + listOfInsults[0] + " and " +weapon.verb() + " " + target + " for " + damage + " damage.";
			}
			else if (chance<6) {
				result = actor + " yells " + listOfInsults[1] + " and " +weapon.verb() + " " + target + " for " + damage + " damage.";
			}
			else if (chance<9) {
				result = actor + " yells " + listOfInsults[2] + " and " +weapon.verb() + " " + target + " for " + damage + " damage.";
			}
		}
		else {
			result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		}


		target.hurt(damage);
		if (!target.isConscious()) {
			//Drop all the items that are droppable. 
			Item sleepingActor = new Item("Sleeping " + target, '%');
			map.locationOf(target).addItem(sleepingActor);
			for (Item item : target.getInventory()) {
				for (Action action : item.getAllowableActions()) {
					if (action instanceof DropItemAction) {
						action.execute(target, map);
						break;
					}
				}
			}
			map.removeActor(target);
			result += System.lineSeparator() + target + " is knocked out.";
		}

		return result;

	}
	/**
	 * Overrides ActionFactory.getAction(Actor,GameMap)
	 * 
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return this Action if the target is next to the actor, null otherwise
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);
		for (Exit exit : here.getExits()) {
			if (exit.getDestination() == there) {
				return this;
			}
		}
		return null;
	}
	/**
	 * Overrides Action.menuDescription()
	 *
	 * @see Action#menuDescription(Actor)
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
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
