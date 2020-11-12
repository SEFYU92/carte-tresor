package com.carbon.test;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.junit.Test;

import com.carbon.apps.util.FileParser;
import com.carbon.apps.util.ResourceLoader;

public class FileParserTest {
	
	@Test
	public void testFilepath () {
		
		ResourceLoader loader = new ResourceLoader();
		String path = loader.getResourceDefaultFile();
		
		FileParser parser = new FileParser(path);
		
		/* test file :
		  	C - 3 - 4
			M - 1 - 0
			M - 2 - 1
			T - 0 - 3 - 2
			T - 1 - 3 - 3
			A - Lara - 1 - 1 - S - AADADAGGA
		*/
		
		assertEquals(new ImmutablePair<Integer, Integer>(3,4) , parser.getDimensions());
		
		assertEquals(4 , parser.getMapObjects().size());
		
		assertEquals(1 , parser.getAdventurers().size());

		assertEquals("Lara" , parser.getAdventurers().get(0).getName());
		
		assertEquals('S' , parser.getAdventurers().get(0).getDirection());
		
		assertEquals('A' , parser.getAdventurers().get(0).getNextMove());
		
		assertEquals(new MutablePair<Integer, Integer>(1,1) , parser.getAdventurers().get(0).getPosition());
		
	}
	
	@Test
	public void testMultipleAdventurers () {
		
		ResourceLoader loader = new ResourceLoader();
		String path = loader.getResourceDefaultFile("testfile2.txt");
		FileParser parser = new FileParser(path);
		
		assertEquals(2 , parser.getAdventurers().size());
		assertEquals("Lauren" , parser.getAdventurers().get(1).getName());
		
	}
	
}
