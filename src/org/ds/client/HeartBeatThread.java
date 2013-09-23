package org.ds.client;

import java.rmi.RemoteException;
import org.ds.server.ClientHeartBeat;
import org.ds.server.Player;

public class HeartBeatThread implements Runnable{
	
	RMIClientManager clientManager;
	ClientHeartBeat heartBeat;
	Player player;
	
	@Override
	public void run() {
		clientManager = RMIClientManagerFactory.getRMIClientManager();
		heartBeat = clientManager.getHeartBeatStub();
		while(true){
			
			try {
				
				heartBeat.updateHeartBeat(player.getId());
				Thread.sleep(10000);
				
			} catch (InterruptedException e) { 
			} catch (RemoteException re){
			}
		}
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
