package org.ds.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

/*
 * Remote interface to manage Player Registration 
 */
public interface PlayerRegistration extends Remote{
	public Map<String, String> register(String name) throws RemoteException;
}
