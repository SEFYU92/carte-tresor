package com.carbon.apps.model.mapmodel;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.carbon.apps.model.mapobjects.Adventurer;
import com.carbon.apps.model.mapobjects.MapObjectInterface;

public abstract class ConcreteMapModel implements MapModel {

	private Pair<Integer, Integer> dimensions;
	
	private List<Adventurer> adventurers;
	
	protected List<MapObjectInterface> mapObjects;
	
	protected int maxNumberOfActions;

	public ConcreteMapModel(Pair<Integer, Integer> dimensions, List<MapObjectInterface> mapObjects, List<Adventurer> adventurers) {

		this.dimensions = dimensions;
		this.adventurers = adventurers;
		this.mapObjects = mapObjects;
	
	}

	@Override
	public Pair<Integer, Integer> getDimentions() {
		return dimensions;
	}

	@Override
	public List<Adventurer> getAdventurers() {
		return adventurers;
	}
	
	@Override
	public List<MapObjectInterface> getMapOjects () {
		return mapObjects;
	}
	
	@Override
	public String toString() {
		String line1 = "C - " + dimensions.getLeft() + " - " + dimensions.getRight();
		String lines2 = mapObjects.stream().map(x -> x.toString()).reduce("",(x,y) -> x + "\n" + y);
		String lines3 = adventurers.stream().map(x -> x.toString()).reduce("",(x,y) -> x + "\n" + y);
		return line1 + lines2 + lines3 + "\n";
	}

}
