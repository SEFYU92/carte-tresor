package com.carbon.apps.controller;

import java.util.Iterator;
import java.util.List;

import com.carbon.apps.model.mapmodel.ArrayMapModel;
import com.carbon.apps.model.mapobjects.Adventurer;
import com.carbon.apps.model.mapobjects.AdventurerHolderInterface;
import com.carbon.apps.model.mapobjects.MapObjectInterface;
import com.carbon.apps.model.mapobjects.Treasure;

public class ArrayMapController extends ConcreteMapController implements MapControllerInterface {

	private MapObjectInterface[][] map;

	public ArrayMapController(ArrayMapModel model) {

		this.model = model;
		map = model.getMapArray();

	}
	
	/**
	 * Processes map before any action, for adventurers starting on top of treasures
	 */
	@Override
	public void processFirstStep() {
		
		model.getAdventurers().forEach(x -> {
			
			tryPickupTreasure(x);
			
		});
		
	}
	
	/**
	 * Iterates through the adventurers, execute their actions, then updates the map accordingly
	 */
	@Override
	public void processNextStep() {
		
		if(currentActionIndex < model.getMaxNumberOfActions()) {
			
			model.getAdventurers().forEach(x -> {

				char action = x.getNextMove();

				if (action == 'A') {
					moveAdventurer(x);
				}else {
					turnAdventurer(x, action);
				}

			});
			
			currentActionIndex ++;
			
		}

	}
	
	@Override
	public void processAllSteps() {
		
		processFirstStep();
		
		for(int i = currentActionIndex; i < model.getMaxNumberOfActions(); i++) {
			processNextStep();
		}
		
		removeEmptyTreasures();
		
	}
	
	private void turnAdventurer(Adventurer adventurer, char action) {
		
		if(action == 'D') {
			adventurer.turnRight();
		}else if(action == 'G') {
			adventurer.turnLeft();
		}
		
	}
	
	private void tryPickupTreasure(Adventurer adventurer) {
		
		int x = adventurer.getPosition().getLeft();
		int y = adventurer.getPosition().getRight();
		
		if(map[x][y] instanceof Treasure && ((Treasure) map[x][y]).canTakeTreasure()) {
			adventurer.pickupTreasure();
		}
		
	}

	private void moveAdventurer(Adventurer adventurer) {
		
		int departureX = adventurer.getPosition().getLeft();
		int departureY = adventurer.getPosition().getRight();
		
		((AdventurerHolderInterface)map[departureX][departureY]).freeHeldAdventurer();
		
		switch (adventurer.getDirection()) {
		
		case 'N': if(canMoveUp(adventurer))adventurer.moveUp();break;
		case 'E': if(canMoveRight(adventurer))adventurer.moveRight();break;
		case 'S': if(canMoveDown(adventurer))adventurer.moveDown();break;
		case 'O': if(canMoveLeft(adventurer))adventurer.moveLeft();break;
		
		}
		
		int arrivalX = adventurer.getPosition().getLeft();
		int arrivalY = adventurer.getPosition().getRight();
		
		((AdventurerHolderInterface)map[arrivalX][arrivalY]).holdAdventurer(adventurer);
		
		tryPickupTreasure(adventurer);
		
	}

	private boolean canMoveUp(Adventurer adventurer) {

		int x = adventurer.getPosition().getLeft();
		int y = adventurer.getPosition().getRight();

		if (y > 0 && (!map[x][y - 1].isObstacle())) {
			return true;
		} else {
			return false;
		}

	}

	private boolean canMoveRight(Adventurer adventurer) {

		int x = adventurer.getPosition().getLeft();
		int y = adventurer.getPosition().getRight();

		if (x < map.length-1 && (!map[x+1][y].isObstacle())) {
			return true;
		} else {
			return false;
		}

	}

	private boolean canMoveDown(Adventurer adventurer) {

		int x = adventurer.getPosition().getLeft();
		int y = adventurer.getPosition().getRight();

		if (y < map[0].length-1 && (!map[x][y + 1].isObstacle())) {
			return true;
		} else {
			return false;
		}

	}

	private boolean canMoveLeft(Adventurer adventurer) {

		int x = adventurer.getPosition().getLeft();
		int y = adventurer.getPosition().getRight();

		if (x > 0 && (!map[x-1][y].isObstacle())) {
			return true;
		} else {
			return false;
		}

	}
	
	private void removeEmptyTreasures() {
		
		List<MapObjectInterface> objects = model.getMapOjects();
		
		Iterator<MapObjectInterface> iterator = objects.iterator();
		while(iterator.hasNext()) {
			MapObjectInterface object = iterator.next();
			if(object instanceof Treasure && ((Treasure)object).getQuantity() == 0) {
				iterator.remove();
			}
		}
		
	}

}
