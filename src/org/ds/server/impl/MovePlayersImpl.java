package org.ds.server.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.ds.server.GameState;
import org.ds.server.GameStateFactory;
import org.ds.server.MoveConstants;
import org.ds.server.MovePlayers;

public class MovePlayersImpl implements MovePlayers{
	GameState state = GameStateFactory.getGameState();
	
	public synchronized Map<String,Object> move(String id, String move) throws RemoteException {
		Map<String,Object> moveMap = new HashMap<String, Object>();
		
		if(move.toLowerCase().equals(MoveConstants.NOMOVE)){
			moveMap.put("currentState", state);
			moveMap.put("isSuccessful", MoveConstants.NOMOVE);
			return moveMap;
		}
		
		return null;
	}
	
}
