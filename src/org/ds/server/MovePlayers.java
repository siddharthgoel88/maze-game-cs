package org.ds.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface MovePlayers extends Remote{
	public Map<String,Object> move( String id, String move ) throws RemoteException;
}
