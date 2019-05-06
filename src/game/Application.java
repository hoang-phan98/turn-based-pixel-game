package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Player;
import edu.monash.fit2099.engine.World;

public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(), new Door());
		GameMap gameMap;

		List<String> map = Arrays.asList(
				".......................",
				"....#####....######....",
				"....#...#....#....#....",
				"....#...]....#....#....",
				"....#####....##.###....",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................");
		gameMap = new GameMap(groundFactory, map);
		world.addMap(gameMap);
		
		Actor player = new Player("Player", '@', 1, 100);
		world.addPlayer(player, gameMap, 5, 10);
		
		Item key = new Key("key", 'k');
		
		Grunt grunt = new Grunt("Mongo", player);
		grunt.addItemToInventory(key);
		gameMap.addActor(grunt, 0, 0);
		Grunt grunt2 = new Grunt("Norbert", player);
		grunt2.addItemToInventory(key);
		gameMap.addActor(grunt2,  10, 10);
		Goon goon = new Goon("Heckler", player);
		goon.addItemToInventory(key);
		gameMap.addActor(goon, 7, 7);
		
		Item rocketPlan = new Item("Rocket Plan", 'p');
		gameMap.addItem(rocketPlan, 6, 2);
		world.run();
	}
}
