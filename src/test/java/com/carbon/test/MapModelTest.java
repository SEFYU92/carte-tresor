package com.carbon.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.carbon.apps.model.mapmodel.ArrayMapModel;
import com.carbon.apps.model.mapmodel.MapModel;
import com.carbon.apps.model.mapobjects.AdventurerHolderInterface;
import com.carbon.apps.model.mapobjects.MapObjectInterface;
import com.carbon.apps.model.mapobjects.Mountain;
import com.carbon.apps.model.mapobjects.Treasure;
import com.carbon.apps.util.FileParser;
import com.carbon.apps.util.ResourceLoader;

public class MapModelTest {
	
	@Test
	public void testArrayMapModel() {
		
		/* test file :
	  	C - 3 - 4
		M - 1 - 0
		M - 2 - 1
		T - 0 - 3 - 2
		T - 1 - 3 - 3
		A - Lara - 1 - 1 - S - AADADAGGA
		*/
		
		ResourceLoader loader = new ResourceLoader();
		String path = loader.getResourceDefaultFile();
		
		FileParser parser = new FileParser(path);
		
		MapModel model = new ArrayMapModel(parser.getDimensions(), parser.getMapObjects(), parser.getAdventurers());
		MapObjectInterface[][] map = ((ArrayMapModel)model).getMapArray();
		
		assertEquals(map.length, 3);
		assertEquals(map[0].length, 4);
		assertTrue(map[1][0] instanceof Mountain);
		assertTrue(map[2][1] instanceof Mountain);
		assertTrue(map[0][3] instanceof Treasure);
		assertTrue(map[1][3] instanceof Treasure);
		assertTrue(((AdventurerHolderInterface)map[1][1]).isHoldingAdventurer());
		assertFalse(map[1][2].isObstacle());
		assertFalse(map[1][3].isObstacle());
		assertFalse(map[0][1].isObstacle());
		
	}

}
