package com.carbon.apps.model.mapobjects;

/**
 *Specifies map objects that can "hold" an adventurer, which means an adventurer can stay on top
 *of this map object.
 */
public interface AdventurerHolderInterface {
	
	public void holdAdventurer(Adventurer adventurer);
	public void freeHeldAdventurer();
	public Adventurer getAdventurer();
	public boolean isHoldingAdventurer();

}
