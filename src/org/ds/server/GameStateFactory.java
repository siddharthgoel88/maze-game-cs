package org.ds.server;

public class GameStateFactory {
	private static GameState gameState;
	
	public static GameState getGameState(){
		if(gameState == null){
			gameState = new GameState();
		}
		return gameState;
	} 
	
}
