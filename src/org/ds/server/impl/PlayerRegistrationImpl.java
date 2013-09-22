package org.ds.server.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.ds.server.GameState;
import org.ds.server.GameStateFactory;
import org.ds.server.Player;
import org.ds.server.PlayerRegistration;
/*
 * Remote interface to manage Player Registration 
 */
public class PlayerRegistrationImpl implements PlayerRegistration{
	
	Long endTime;
	GameState state = GameStateFactory.getGameState();
	char playerChar = 'A';
	
	public Map<String, String> register(String name) throws RemoteException{
		
		HashMap<String, String> registrationProps = new HashMap<String, String>();
		Player newPlayer = new Player(name, UUID.randomUUID().toString());
		Map<String,Player> players = state.getPlayers();
		Long waitTime;
		
		synchronized (players) {
			if(players.isEmpty()){
				endTime = System.currentTimeMillis() + 10000;
				state.initializeGame();
			}
			
			waitTime = endTime - System.currentTimeMillis();
			newPlayer.setPlayerDispId(playerChar);
			registrationProps.put("playerDisplayId", String.valueOf((playerChar++)));
			if(waitTime >= 0){
				players.put(newPlayer.getId() , newPlayer);
				while(!state.initializePlayer(newPlayer.getId()))
					;
			}
		}
		registrationProps.put("id", newPlayer.getId());
		registrationProps.put("waitTime", (waitTime > 0) ? String.valueOf(waitTime): "0" );
		registrationProps.put("startRow" , String.valueOf(newPlayer.getCurrentRow()));
		registrationProps.put("startCol" , String.valueOf(newPlayer.getCurrentCol()));
		registrationProps.put("isSuccessful", String.valueOf((waitTime >= 0)));
		return registrationProps;
	}

	public GameState getInitialGameState() throws RemoteException {
		return state;
	}
}