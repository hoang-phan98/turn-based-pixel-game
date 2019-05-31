package game;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

/**
 * Extension of the Action class which can be used by the player to quit the game
 */
public class EndGameAction extends Action {
	private World world;
	
	public EndGameAction(World world) {
		this.world = world;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		((EndGameWorld) this.world).quit();
		return "You have quit the Game";
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Quit Game";
	}

	@Override
	public String hotKey() {
		return "0";
	}

}
