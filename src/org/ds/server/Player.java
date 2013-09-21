package org.ds.server;

public class Player {
	
	String name;
	String id;
	int numTreasures;
	int currentRow;
	int currentCol;
	
	public Player(String name) {
		this.setName(name);
	}

	public Player(String name , String id){
		this.setId(id);
		this.setName(name);
	}
	
	public int getCurrentRow() {
		return currentRow;
	}
	
	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}
	
	public int getCurrentCol() {
		return currentCol;
	}
	
	public void setCurrentCol(int currentCol) {
		this.currentCol = currentCol;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}	
