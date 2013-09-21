package org.ds.server;

public class GameStateFactory {
	private static GameState gameState = new GameState();
	
	public static GameState getGameState(){
		return gameState;
	}
	
}
