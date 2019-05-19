package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

public class AttackYugoAction extends Action {
	
	protected Actor actor;
	protected Actor subject;
	protected Random rand = new Random();

	public AttackYugoAction(Actor actor, Actor subject) {
		this.actor = actor;
		this.subject = subject;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		if(((FinalBoss) this.subject).invulnerable) {
			return subject + " is invulnerable!";
		}
		
		Weapon weapon = actor.getWeapon();
		if(weapon instanceof WaterPistol) {
			weapon = new IntrinsicWeapon(5, "punches");
		}
				
		if (rand.nextBoolean()) {
			return actor + " misses " + subject + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + subject + " for " + damage + " damage.";

		subject.hurt(damage);
		if (!subject.isConscious()) {
			//Drop all the items that are droppable. 
			Item sleepingActor = new Item("Sleeping " + subject, '%');
			map.locationOf(subject).addItem(sleepingActor);
			for (Item item : subject.getInventory()) {
				for (Action action : item.getAllowableActions()) {
					if (action instanceof DropItemAction) {
						action.execute(subject, map);
						break;
					}
				}
			}
			map.removeActor(subject);
			result += System.lineSeparator() + subject + " is knocked out.";
		}

		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + this.subject;
	}

	@Override
	public String hotKey() {
		return "";
	}

}
