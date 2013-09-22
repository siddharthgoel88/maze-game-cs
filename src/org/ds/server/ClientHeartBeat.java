package org.ds.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientHeartBeat extends Remote{
	public boolean updateHeartBeat(String id) throws RemoteException;
}
