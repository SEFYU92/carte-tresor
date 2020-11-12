package com.carbon.test;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.tuple.MutablePair;
import org.junit.Test;

import com.carbon.apps.model.mapobjects.Adventurer;

public class AdventurerTest {
	
	@Test
	public void testAdventurer() {
		
		String[] data = "Lara - 1 - 1 - S - AADADAGGA".split(" - ");
		Adventurer adventurer = new Adventurer(data);
		
		adventurer.moveUp();
		adventurer.moveRight();
		adventurer.turnRight();
		adventurer.turnRight();
		adventurer.pickupTreasure();
		adventurer.pickupTreasure();
		
		assertEquals(2, adventurer.getTreasures());
		assertEquals(new MutablePair<>(2,0), adventurer.getPosition());
		assertEquals(9, adventurer.getMoveSequenceSize());
		assertEquals('N', adventurer.getDirection());
		
		adventurer.turnRight();
		assertEquals('E', adventurer.getDirection());
		adventurer.turnLeft();
		assertEquals('N', adventurer.getDirection());
		adventurer.turnLeft();
		assertEquals('O', adventurer.getDirection());
		adventurer.turnLeft();
		assertEquals('S', adventurer.getDirection());
		adventurer.turnLeft();
		assertEquals('E', adventurer.getDirection());
		
	}

}
