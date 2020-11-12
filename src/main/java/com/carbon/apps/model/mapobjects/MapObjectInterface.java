package com.carbon.apps.model.mapobjects;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Interface for any map object
 */
public interface MapObjectInterface {
	
	public Pair<Integer, Integer> getPosition();
	
	/**
	 * @return false if this object cannot be crossed by an adventurer at the moment
	 */
	public boolean isObstacle();

}
