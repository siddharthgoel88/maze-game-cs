package org.ds.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameEndCheck extends Remote{
	public GameState checker() throws RemoteException;
}
