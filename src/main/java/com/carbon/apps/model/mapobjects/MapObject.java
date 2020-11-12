package com.carbon.apps.model.mapobjects;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public abstract class MapObject implements MapObjectInterface {
	
	//protected because some subclasses may have different logic for position
	protected Pair <Integer, Integer> position;
	
	public MapObject (String[] data) {
		
		position = new ImmutablePair<Integer, Integer> (Integer.parseInt(data[0]), Integer.parseInt(data[1]));
		
	}
	
	public MapObject () {
		//Default constructor
	}

	@Override
	public Pair<Integer, Integer> getPosition() {
		
		return position;
		
	}
	
	@Override
	public boolean isObstacle() {
		return false;
	}
	
	@Override
	public String toString() {
		
		return position.getLeft() + " - " + position.getRight();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MapObject) {
			if(this.getPosition().equals(((MapObject)obj).getPosition())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Integer.parseInt(this.getPosition().getLeft().toString() + this.getPosition().getRight().toString());
	}
	
}
