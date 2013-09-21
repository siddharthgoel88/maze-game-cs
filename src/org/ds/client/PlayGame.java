package org.ds.client;

import java.rmi.registry.*;
import java.util.Map;

import org.ds.server.Player;
import org.ds.server.PlayerRegistration;

public class PlayGame {
	
	public static void main(String[] args) {
		
		Player p1 = new Player("name");
		
		try {
			
			RMIClientManager clientManager = new RMIClientManager();
		    Map<String, String> playerData = clientManager.getRegistrationStub().register(p1.getName());
		    System.out.println("id: " + playerData.get("id"));
		    System.out.println("wait time:" + playerData.get("waitTime"));
		    System.out.println("Start Row:" + playerData.get("startRow"));
		    System.out.println("Start Col:" + playerData.get("startCol"));
		    System.out.println("is Successful : " + playerData.get("isSuccessful"));
		    Thread.sleep(Long.parseLong(playerData.get("waitTime")));
		    System.out.println("Finished Waiting");
		    
		} catch (Exception e) {
		    System.err.println("Client exception: " + e.toString());
		    e.printStackTrace();
		}
	}
}
