package com.carbon.apps.util;

import com.carbon.apps.model.mapobjects.Adventurer;
import com.carbon.apps.model.mapobjects.MapObjectInterface;
import com.carbon.apps.model.mapobjects.Mountain;
import com.carbon.apps.model.mapobjects.Treasure;

/**
 *Factory that Instantiates map objects depending on their char type on the input file
 */
public class MapObjectFactory {

	public MapObjectInterface createMapObject (char type, String[] data) {
		
		MapObjectInterface object = null;
		
		switch (type) {
		
			case 'M': 
				object = new Mountain(data);
				break;
				
			case 'T' :
				object = new Treasure(data);
				break;
				
			case 'A' :
				object = new Adventurer(data);
				
		}
		
		return object;
	}

}
