package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

public class InsultAction extends Action {
	private Actor actor;
	private Actor target;
	private Random rand = new Random();
//	private List listOfInsults = new 

	public InsultAction(Actor actor, Actor target) {
		this.actor = actor;
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (rand.nextBoolean()) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

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
