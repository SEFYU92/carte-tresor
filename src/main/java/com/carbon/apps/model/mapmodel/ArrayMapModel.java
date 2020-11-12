package com.carbon.apps.model.mapmodel;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.carbon.apps.model.mapobjects.Adventurer;
import com.carbon.apps.model.mapobjects.AdventurerHolderInterface;
import com.carbon.apps.model.mapobjects.Land;
import com.carbon.apps.model.mapobjects.MapObjectInterface;

/**
 * A map data model implementation based on a internal two dimensional array
 */
public class ArrayMapModel extends ConcreteMapModel implements MapModel {
	
	private MapObjectInterface[][] map;
	
	public ArrayMapModel(Pair<Integer, Integer> dimensions, List<MapObjectInterface> mapObjects, List<Adventurer> adventurers) {
		
		super(dimensions, mapObjects, adventurers);
		int xSize = dimensions.getLeft();
		int ySize = dimensions.getRight();
		map = new MapObjectInterface[xSize][ySize];
		
		
		
		for(int i = 0; i < map.length; i++) {
			Arrays.setAll(map[i], x -> new Land());
		}
		
		this.mapObjects.forEach(x -> {
			map[x.getPosition().getLeft()][x.getPosition().getRight()] = x;
		});
		
		Iterator<Adventurer> iterator = adventurers.iterator();
		
		while(iterator.hasNext()) {
			Adventurer adventurer = iterator.next();
			if(adventurer.getMoveSequenceSize() > maxNumberOfActions) {
				maxNumberOfActions = adventurer.getMoveSequenceSize(); 
			}
			
			MapObjectInterface mapObject = map[adventurer.getPosition().getLeft()][adventurer.getPosition().getRight()];
			
			if(mapObject instanceof AdventurerHolderInterface) {
				
				AdventurerHolderInterface holder = (AdventurerHolderInterface)mapObject;
				
				if(!holder.isHoldingAdventurer()) {
					holder.holdAdventurer(adventurer);
				}
				
			} else {
				System.err.println("adventurer on forbidden position " + adventurer.getPosition() + " adventurer ignored");
				iterator.remove();
			}
		}
		
	}
	
	/**
	 * @return an array representation of the map
	 */
	public MapObjectInterface[][] getMapArray() {
		return map;
	}

	@Override
	public int getMaxNumberOfActions() {
		return maxNumberOfActions;
	}

}
