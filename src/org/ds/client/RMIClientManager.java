package org.ds.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.ds.server.ClientHeartBeat;
import org.ds.server.PlayerRegistration;

public class RMIClientManager {
	
	private Registry registryCon;
	private PlayerRegistration playerRegstub;
	private ClientHeartBeat heartBeatStub;
	
	public RMIClientManager() throws RemoteException, NotBoundException{
		if(registryCon == null){
			registryCon = LocateRegistry.getRegistry(ServerProperties.getServerHostname(), ServerProperties.getServerPort());
			playerRegstub = (PlayerRegistration) registryCon.lookup("registrationManager");
			heartBeatStub = (ClientHeartBeat) registryCon.lookup("heartBeatManager");
		}
	}
	
	public PlayerRegistration getRegistrationStub(){
		return playerRegstub;
	}

	public ClientHeartBeat getHeartBeatStub() {
		return heartBeatStub;
	}
}
