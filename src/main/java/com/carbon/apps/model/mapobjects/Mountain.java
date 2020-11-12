package com.carbon.apps.model.mapobjects;

public class Mountain extends MapObject implements MapObjectInterface {

	public Mountain(String[] data) {
		super(data);
	}
	
	@Override
	public boolean isObstacle() {
		return true;
	}
	
	@Override
	public String toString() {
		return "M - " + super.toString();
	}

}
