package org.ds.server.impl;


import java.rmi.RemoteException;

import org.ds.server.GameEndCheck;
import org.ds.server.GameState;
import org.ds.server.GameStateFactory;

public class GameEndCheckImpl implements GameEndCheck{

	GameState state = GameStateFactory.getGameState();
	
	@Override
	public GameState checker() throws RemoteException{
		if(state.getTotalNumTreasures() == 0){
			return state;
		}
		return null;
	}
	

}
