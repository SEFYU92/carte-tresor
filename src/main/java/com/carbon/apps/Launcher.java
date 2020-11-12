package com.carbon.apps;

import com.carbon.apps.controller.ArrayMapController;
import com.carbon.apps.controller.MapControllerInterface;
import com.carbon.apps.model.mapmodel.ArrayMapModel;
import com.carbon.apps.model.mapmodel.MapModel;
import com.carbon.apps.util.FileParser;

/**
 * App launcher, takes path of input file as argument
 * Argument example : C:\Users\John\eclipse-workspace\testfile.txt or testfile.txt in relative
 */
public class Launcher {
	
	public static void main ( String[] args) {
		
		String path = null;
		if(args.length == 0) {
			System.err.println("no arguments");
			System.exit(1);
		} else {
			path = args[0];
		}
		
		FileParser parser = new FileParser(path);
		MapModel model = new ArrayMapModel(parser.getDimensions(), parser.getMapObjects(), parser.getAdventurers());
		MapControllerInterface controller = new ArrayMapController((ArrayMapModel) model);
		
		System.out.println("Start situation :");
		System.out.println(controller.getModel().toString());
		
		controller.processAllSteps();
		
		System.out.println("Resolved situation :");
		System.out.println(controller.getModel().toString());
		
	}
	
}
