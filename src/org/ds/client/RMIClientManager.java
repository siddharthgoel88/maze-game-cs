package org.ds.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.ds.server.ClientHeartBeat;
import org.ds.server.GameEndCheck;
import org.ds.server.MovePlayers;
import org.ds.server.PlayerRegistration;

public class RMIClientManager {
	
	private Registry registryCon;
	private PlayerRegistration playerRegstub;
	private ClientHeartBeat heartBeatStub;
	private MovePlayers movePlayerStub;
	private GameEndCheck gameEndStub;
	
	public RMIClientManager() throws RemoteException, NotBoundException{
		if(registryCon == null){
			registryCon = LocateRegistry.getRegistry(ServerProperties.getServerHostname(), ServerProperties.getServerPort());
			playerRegstub = (PlayerRegistration) registryCon.lookup("registrationManager");
			heartBeatStub = (ClientHeartBeat) registryCon.lookup("heartBeatManager");
			movePlayerStub = (MovePlayers) registryCon.lookup("movePlayers");
			gameEndStub = (GameEndCheck) registryCon.lookup("gameEndCheck");
		}
	}
	
	public PlayerRegistration getRegistrationStub(){
		return playerRegstub;
	}

	public ClientHeartBeat getHeartBeatStub() {
		return heartBeatStub;
	}

	public MovePlayers getMovePlayerStub() {
		return movePlayerStub;
	}

	public GameEndCheck getGameEndStub() {
		return gameEndStub;
	}

//	public void setMovePlayerStub(MovePlayers movePlayerStub) {
//		this.movePlayerStub = movePlayerStub;
//	}
}
