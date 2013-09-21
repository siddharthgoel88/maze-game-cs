package org.ds.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.ds.server.PlayerRegistration;

public class RMIClientManager {
	
	private Registry registry;
	private PlayerRegistration playerRegstub;
	
	public RMIClientManager() throws RemoteException, NotBoundException{
		if(registry == null){
			registry = LocateRegistry.getRegistry(ServerProperties.getServerHostname(), ServerProperties.getServerPort());
			playerRegstub = (PlayerRegistration) registry.lookup("registrationManager");
		}
	}
	
	public PlayerRegistration getRegistrationStub(){
		return playerRegstub;
	}
}
