package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.World;

public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(), new LockedDoor(), new Water(), new RocketPadGround());
		GameMap gameMap;

		List<String> map = Arrays.asList(
				".......................",
				"....#####....######....",
				"....#...#....#....#....",
				"....#...]....#....#....",
				"....#####....##]###....",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................");
		gameMap = new GameMap(groundFactory, map);
		world.addMap(gameMap);
		
        List<String> map2 = Arrays.asList(
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				"...........x...........",
				".......................",
				"................~~~~~~~",
				"................~~~~~~~",
				"................~~~~~~~",
				"................~~~~~~~");
        
        GameMap gameMap2 = new GameMap(groundFactory, map2);
        world.addMap(gameMap2);
        
        List<String> moon = Arrays.asList(
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				"...........x...........",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................");
        
        GameMap moonBase = new GameMap(groundFactory, moon);
        world.addMap(moonBase);
        
        Item teleporter1 = Item.newFurniture("Teleporter", '>');
        teleporter1.getAllowableActions().add(new MoveActorAction(gameMap2.at(1, 5), "to Map 2!"));
        gameMap.addItem(teleporter1, 22, 5);
        
        Item teleporter2 = Item.newFurniture("Teleporter", '<');
        teleporter2.getAllowableActions().add(new MoveActorAction(gameMap.at(22, 5), "to Map 1!"));
        gameMap2.addItem(teleporter2, 0, 5);
		
		Actor player = new StunnablePlayer("Player", '@', 1, 100);
		world.addPlayer(player, gameMap2, 5, 11);
		
		Grunt grunt = new Grunt("Mongo", player);
		gameMap.addActor(grunt, 0, 0);
		Grunt grunt2 = new Grunt("Norbert", player);
		gameMap.addActor(grunt2,  10, 10);
		Ninja ninja = new Ninja("Ninja", player);
		gameMap.addActor(ninja, 5, 5);
		Goon goon = new Goon("Heckler", player);
		gameMap.addActor(goon, 7, 7);
		MiniBoss DoctorMaybe = new MiniBoss("Doctor Maybe");
		gameMap.addActor(DoctorMaybe, 16, 2);
		
		Grunt grunt3 = new Grunt("Monga", player);
		gameMap2.addActor(grunt3, 0, 0);
		Grunt grunt4 = new Grunt("Norberto", player);
		gameMap2.addActor(grunt4,  10, 10);
		Goon goon1 = new Goon("Hecklerb", player);
		gameMap2.addActor(goon1, 7, 7);
		
		Q q = new Q();
		gameMap2.addActor(q, 8, 8);
		
		Item rocketPlan = new RocketPlan();
		gameMap.addItem(rocketPlan, 6, 2);
		
		Item rocket = new Rocket();
		rocket.getAllowableActions().add(new MoveActorAction(gameMap2.at(11, 5), "to Earth!"));
		moonBase.addItem(rocket, 11, 5);
		
		Location rocketPadLocation = new Location(gameMap2, 11, 5);
		Item rocketPad = new RocketPad(moonBase, rocketPadLocation);
		gameMap2.addItem(rocketPad, 11, 5);
		gameMap2.add(new RocketPadGround(), rocketPadLocation);
		
		FinalBoss yugoMaxx = new FinalBoss(player);
		moonBase.addActor(yugoMaxx, 0, 0);
		WaterPistol waterPistol = new WaterPistol();
		moonBase.addItem(waterPistol, 6, 5);
		
		player.addItemToInventory(new RocketBody());
		player.addItemToInventory(new RocketEngine());
		
		world.run();
	}
}
