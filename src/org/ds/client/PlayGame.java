package org.ds.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.registry.*;
import java.util.Map;

import org.ds.server.GameState;
import org.ds.server.Player;
import org.ds.server.PlayerRegistration;
import org.ds.server.Square;

public class PlayGame {
	
	
	
	public static void main(String[] args) {
		
		Player p1 = new Player("name");
		
		try {
			RMIClientManager clientManager = RMIClientManagerFactory.initClientManager();
		    Map<String, String> playerData = clientManager.getRegistrationStub().register(p1.getName());
		    
		    if(!Boolean.valueOf(playerData.get("isSuccessful"))){
		    	System.out.println("Game registration unsuccessful. Please try again later");
		    	System.exit(0);
		    }
		    
		    System.out.println("Welcome "+p1.getName() + " you will have to wait for " + playerData.get("waitTime") + " for the game to begin");
		    Thread.sleep(Long.parseLong(playerData.get("waitTime")));
		    p1.setId(playerData.get("id"));
		    HeartBeatThread heartBeat = new HeartBeatThread();
			Thread heartBeatThread = new Thread(heartBeat);
			heartBeat.setPlayer(p1);
			heartBeatThread.start();
			
		    GameState initState = clientManager.getRegistrationStub().getInitialGameState();
		    printState(initState);
		    
		    while(true){
				BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			    String move;   
				move = bufferRead.readLine().toLowerCase();
				if(move.matches("[asdwqn]")){
					Map<String,Object> moveResult = clientManager.getMovePlayerStub().move(p1.getId(), move);
					if(!Boolean.valueOf((String) moveResult.get("isSuccessful"))){
						System.out.println((String)moveResult.get("errorMessage"));
					}
					System.out.println(moveResult.get("currentState"));
					printState((GameState)moveResult.get("currentState"));
				}else{
					System.out.println("Invalid Entry. Please re-enter your move");
				}
			}
		} catch (Exception e) {
		    System.err.println("Client exception: " + e.toString());
		    e.printStackTrace();
		}
	}

	private static void printState(GameState initState) {
		Square[][] square = initState.getGameBoard();
		Map<String,Player> players = initState.getPlayers();
		int boardsize = initState.getBoardSize();
		
		for (int i=0 ; i < boardsize ; i++){
			System.out.println("\n");
			for(int j=0 ; j < boardsize ; j++){
				if(!square[i][j].isFree()){
					Player curPlayer = players.get(square[i][j].getUserId());
					System.out.print(curPlayer.getPlayerDispId() + "(" + curPlayer.getNumTreasures() + ") |");
				}else{
					System.out.print( square[i][j].getNumTreasures() +" | ");
				}
			}
		}
		
		for(int i=0;i++<5;)
			System.out.println("");
	}
}
