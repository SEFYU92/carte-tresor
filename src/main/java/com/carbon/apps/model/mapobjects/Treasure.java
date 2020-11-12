package com.carbon.apps.model.mapobjects;

public class Treasure extends AdventurerHolder implements MapObjectInterface, AdventurerHolderInterface {
	
	private int quantity;

	public Treasure(String[] data) {
		super(data);
		quantity = Integer.parseInt(data[2]);
	}
	
	public boolean canTakeTreasure() {
		if (quantity > 0) {
			quantity--;
			return true;
		}else {
			return false;
		}
	}

	public int getQuantity() {
		return quantity;
	}
	
	@Override
	public String toString() {
		return "T - " + super.toString() + " - " + this.quantity;
	}

}
