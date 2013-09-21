package org.ds.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameState {

	private int BOARD_SIZE = 5;
	private int numTreasures = 10;
	private Square[][] gameBoard = new Square[5][5];
	private HashMap<String,Player> players = new HashMap<String,Player>();

	public void initializeGame() {
		int randRow, randCol;
		Random random = new Random();
		
		for(int i = 0 ; i < BOARD_SIZE ; i++)
			for(int j = 0 ; j < BOARD_SIZE ; j++)
				gameBoard[i][j] = new Square();
		
		
		for (int i = 0; i < numTreasures; i++) {
			randCol = random.nextInt(BOARD_SIZE);
			randRow = random.nextInt(BOARD_SIZE);
			gameBoard[randCol][randRow].numTreasures += 1;
		}
		
		for(int i = 0 ; i < BOARD_SIZE ; i++){
			System.out.println("\n");
			for(int j = 0 ; j < BOARD_SIZE ; j++){
				System.out.print(gameBoard[i][j].numTreasures + " | ");
			}
		}
	}
	
	public void setBoardSize(int boardSize){
		this.BOARD_SIZE = boardSize;
	}
	
	public void setNumTreasures(int numTreasures){
		this.numTreasures = numTreasures;
	}
	
	public Map<String,Player> getPlayers(){
		return players;
	}
	
	public boolean initializePlayer(String id){
		Random random = new Random();
		int randRow = random.nextInt(BOARD_SIZE);
		int randCol = random.nextInt(BOARD_SIZE);
		
		if(gameBoard[randRow][randCol].numTreasures == 0 ){
			gameBoard[randRow][randCol].userId = id;
			players.get(id).currentCol = randCol;
			players.get(id).currentRow = randRow;
			return true;
		}
		
		return false;
	}
}
