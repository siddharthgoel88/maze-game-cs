package org.ds.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMIClientManagerFactory {
	
	static RMIClientManager clientManager;
	
	public static RMIClientManager initClientManager() throws RemoteException, NotBoundException {
		clientManager = new RMIClientManager();
		return clientManager;
	}
	
	public static RMIClientManager getRMIClientManager() {
		return clientManager;
	}
}
