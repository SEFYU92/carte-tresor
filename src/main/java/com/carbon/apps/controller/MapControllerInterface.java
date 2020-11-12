package com.carbon.apps.controller;

import com.carbon.apps.model.mapmodel.MapModel;

/**
 *Controller to process each step of the problem and update the model accordingly to the rules
 *the controller interface can be implemented with an internal logic that matches a specific model
 *@see ArrayMapModel
 *@see ArrayMapController
 */
public interface MapControllerInterface {

	public MapModel getModel();
	void processFirstStep();
	void processNextStep();
	void processAllSteps();
	
}
