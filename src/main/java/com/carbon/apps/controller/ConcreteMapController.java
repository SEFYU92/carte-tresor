package com.carbon.apps.controller;

import com.carbon.apps.model.mapmodel.MapModel;

public abstract class ConcreteMapController implements MapControllerInterface {
	
	protected MapModel model;
	protected int currentActionIndex = 0;
		
	@Override
	public MapModel getModel() {
		
		return model;
		
	}
	
}
