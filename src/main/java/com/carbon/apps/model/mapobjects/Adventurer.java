package com.carbon.apps.model.mapobjects;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class Adventurer implements MapObjectInterface {
	
	private final static char[] directions = {'N','E','S','O'};

	private String name;
	
	private Pair <Integer, Integer> position;

	private char direction;
	private int directionIndex;

	private char[] moveSequence;

	private int currentMoveIndex;
	
	private int treasures;

	public Adventurer(String[] data) {

		super();
		name = data[0];
		position = new MutablePair<Integer, Integer>(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
		direction = data[3].charAt(0);
		switch (direction) {
		case 'N':directionIndex=0;break;
		case 'E':directionIndex=1;break;
		case 'S':directionIndex=2;break;
		case 'O':directionIndex=3;break;}
		moveSequence = data[4].toCharArray();
		currentMoveIndex = 0;
		treasures = 0;

	}
	
	public String getName() {
		return name;
	}
	
	public char getDirection() {
		return direction;
	}
	
	public void setDirection(char direction) {
		this.direction = direction;
	}
	
	public void turnRight() {
		directionIndex = directionIndex < directions.length-1 ? ++directionIndex : 0;
		direction = directions[directionIndex];
		currentMoveIndex++;
	}
	
	public void turnLeft() {
		directionIndex = directionIndex > 0 ? --directionIndex : 3;
		direction = directions[directionIndex];
		currentMoveIndex++;
	}
	
	public int getTreasures() {
		return treasures;
	}
	
	@Override
	public boolean isObstacle() {
		return true;
	}
	
	public void pickupTreasure() {
		treasures ++;
	}
	
	public int getMoveSequenceSize() {
		return moveSequence.length;
	}

	public char getNextMove() {
		return currentMoveIndex < moveSequence.length ? moveSequence[currentMoveIndex] : 'F';
	}
	
	public void moveUp() {
		((MutablePair<Integer, Integer>)this.position).right --;
		currentMoveIndex++;
	}
	
	public void moveRight() {
		((MutablePair<Integer, Integer>)this.position).left ++;
		currentMoveIndex++;
	}

	public void moveDown() {
		((MutablePair<Integer, Integer>)this.position).right ++;
		currentMoveIndex++;
	}

	public void moveLeft() {
		((MutablePair<Integer, Integer>)this.position).left --;
		currentMoveIndex++;
	}

	@Override
	public Pair<Integer, Integer> getPosition() {
		return position;
	}
	
	@Override
	public String toString() {
		return "A - " + this.name + " - " + position.getLeft() + " - " + position.getRight() 
			+ " - " + this.direction + " - " + this.treasures;
	}
	
	@Override
	public int hashCode() {
		return Integer.parseInt(this.getPosition().getLeft().toString() + this.getPosition().getRight().toString());
	}

}
