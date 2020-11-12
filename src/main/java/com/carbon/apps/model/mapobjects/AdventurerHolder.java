package com.carbon.apps.model.mapobjects;

public abstract class AdventurerHolder extends MapObject implements AdventurerHolderInterface {
	
	private Adventurer adventurer = null;
	
	private boolean isObstacle = false;
	
	public AdventurerHolder(String[] data) {
		
		super(data);
		
	}
	
	public AdventurerHolder() {
		super();
	}
	
	@Override
	public boolean isObstacle() {
		return isObstacle;
	}
	
	public void setObstacle(boolean value) {
		isObstacle = value;
	}

	@Override
	public void holdAdventurer(Adventurer adventurer) {
		
		this.adventurer = adventurer;
		this.isObstacle = true;

	}

	@Override
	public void freeHeldAdventurer() {
		
		this.adventurer = null;
		this.isObstacle = false;
		
	}

	@Override
	public Adventurer getAdventurer() {
		
		return adventurer;
		
	}

	@Override
	public boolean isHoldingAdventurer() {
		
		return adventurer == null ? false : true;
		
	}

}
