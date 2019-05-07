package game;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

public class InsultAction extends Action {
	private Actor actor;
	private Actor target;
	private Random rand= new Random();
	private Double chance = 100*rand.nextDouble();
	private String insult;
	private String result;
	private String[] listOfInsults = {"You will never succeed","You've gained weight","Your hairline is receeding"};
	
//	private List<String> listOfInsults = new ArrayList<String>();

	public InsultAction(Actor actor, Actor target) {
		this.actor = actor;
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();
		int damage = weapon.damage();
		

		if (rand.nextBoolean()) {
			return actor + " misses " + target + ".";
		}
		if (chance<30) {
			result = actor + " yells " + listOfInsults[0] + " and " +weapon.verb() + " " + target + " for " + damage + " damage.";
		}
		else if (chance<60) {
			result = actor + " yells " + listOfInsults[1] + " and " +weapon.verb() + " " + target + " for " + damage + " damage.";
		}
		else if (chance<90) {
			result = actor + " yells " + listOfInsults[2] + " and " +weapon.verb() + " " + target + " for " + damage + " damage.";
		}
		else {
			result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		}

		
//		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

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

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}

	@Override
	public String hotKey() {
		return "";
	}

}
