package org.ds.server.impl;

import java.rmi.RemoteException;

import org.ds.server.ClientHeartBeat;	
import org.ds.server.GameState;
import org.ds.server.GameStateFactory;

public class ClientHeartBeatImpl implements ClientHeartBeat{
	
	GameState state = GameStateFactory.getGameState();
	
	public boolean updateHeartBeat(String id) throws RemoteException{
		synchronized (state.getPlayers().get(id)) {
			state.getPlayers().get(id).setLastActiveTime(System.currentTimeMillis());
		}
		return true;
	}
}
