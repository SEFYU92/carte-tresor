package com.carbon.apps.model.mapmodel;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.carbon.apps.model.mapobjects.Adventurer;
import com.carbon.apps.model.mapobjects.MapObjectInterface;

/**
 *Data modelisation of a treasure map and adventurers
 */
public interface MapModel {
	
	public Pair<Integer, Integer> getDimentions ();
	public List<Adventurer> getAdventurers ();
	public List<MapObjectInterface> getMapOjects ();
	public int getMaxNumberOfActions ();
	
}
