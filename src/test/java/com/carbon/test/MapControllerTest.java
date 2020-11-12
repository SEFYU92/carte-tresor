package com.carbon.test;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.tuple.MutablePair;
import org.junit.Test;

import com.carbon.apps.controller.ArrayMapController;
import com.carbon.apps.controller.MapControllerInterface;
import com.carbon.apps.model.mapmodel.ArrayMapModel;
import com.carbon.apps.model.mapmodel.MapModel;
import com.carbon.apps.model.mapobjects.Treasure;
import com.carbon.apps.util.FileParser;
import com.carbon.apps.util.ResourceLoader;

public class MapControllerTest {
	
	@Test
	public void testArrayMapController(){
		
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
		MapControllerInterface controller = new ArrayMapController((ArrayMapModel) model);
				
		controller.processNextStep();
		assertEquals("Lara", controller.getModel().getAdventurers().get(0).getName());
		assertEquals(new MutablePair<Integer, Integer>(1,2), controller.getModel().getAdventurers().get(0).getPosition());
		assertEquals('S', controller.getModel().getAdventurers().get(0).getDirection());
		assertEquals(0, controller.getModel().getAdventurers().get(0).getTreasures());
				
		controller.processNextStep();
		assertEquals(new MutablePair<Integer, Integer>(1,3), controller.getModel().getAdventurers().get(0).getPosition());
		assertEquals('S', controller.getModel().getAdventurers().get(0).getDirection());
		assertEquals(1, controller.getModel().getAdventurers().get(0).getTreasures());
		assertEquals(2, ((Treasure)((ArrayMapModel)controller.getModel()).getMapArray()[1][3]).getQuantity());
				
		controller.processNextStep();
		assertEquals(new MutablePair<Integer, Integer>(1,3), controller.getModel().getAdventurers().get(0).getPosition());
		assertEquals('O', controller.getModel().getAdventurers().get(0).getDirection());
		assertEquals(1, controller.getModel().getAdventurers().get(0).getTreasures());
				
		controller.processNextStep();
		assertEquals(new MutablePair<Integer, Integer>(0,3), controller.getModel().getAdventurers().get(0).getPosition());
		assertEquals('O', controller.getModel().getAdventurers().get(0).getDirection());
		assertEquals(2, controller.getModel().getAdventurers().get(0).getTreasures());
		assertEquals(1, ((Treasure)((ArrayMapModel)controller.getModel()).getMapArray()[0][3]).getQuantity());
		
		controller.processNextStep();
		assertEquals(new MutablePair<Integer, Integer>(0,3), controller.getModel().getAdventurers().get(0).getPosition());
		assertEquals('N', controller.getModel().getAdventurers().get(0).getDirection());
		
		controller.processNextStep();
		assertEquals(new MutablePair<Integer, Integer>(0,2), controller.getModel().getAdventurers().get(0).getPosition());
		assertEquals('N', controller.getModel().getAdventurers().get(0).getDirection());
		
		controller.processNextStep();
		assertEquals(new MutablePair<Integer, Integer>(0,2), controller.getModel().getAdventurers().get(0).getPosition());
		assertEquals('O', controller.getModel().getAdventurers().get(0).getDirection());
		
		controller.processNextStep();
		assertEquals(new MutablePair<Integer, Integer>(0,2), controller.getModel().getAdventurers().get(0).getPosition());
		assertEquals('S', controller.getModel().getAdventurers().get(0).getDirection());
		
		controller.processNextStep();
		assertEquals(new MutablePair<Integer, Integer>(0,3), controller.getModel().getAdventurers().get(0).getPosition());
		assertEquals('S', controller.getModel().getAdventurers().get(0).getDirection());
		assertEquals(3, controller.getModel().getAdventurers().get(0).getTreasures());
		assertEquals(0, ((Treasure)((ArrayMapModel)controller.getModel()).getMapArray()[0][3]).getQuantity());
	
	}
	
	@Test
	public void testMultipleAdventurers() {
		
		ResourceLoader loader = new ResourceLoader();
		String path = loader.getResourceDefaultFile("testfile2.txt");
		/*
		C - 3 - 4
		M - 1 - 0
		M - 2 - 1
		T - 0 - 3 - 2
		T - 1 - 3 - 3
		A - Lara - 1 - 1 - S - AADADAGGA
		A - Lauren - 0 - 0 - N - DDDDDDDDDDDDDDDDD
		*/
		
		FileParser parser = new FileParser(path);
		
		MapModel model = new ArrayMapModel(parser.getDimensions(), parser.getMapObjects(), parser.getAdventurers());
		MapControllerInterface controller = new ArrayMapController((ArrayMapModel) model);
		
		controller.processAllSteps();;
		
		assertEquals(2, controller.getModel().getAdventurers().size());
		assertEquals("Lara", controller.getModel().getAdventurers().get(0).getName());
		assertEquals("Lauren", controller.getModel().getAdventurers().get(1).getName());
		
	}

}
