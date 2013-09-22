package org.ds.server.impl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.ds.server.ClientHeartBeat;
import org.ds.server.PlayerRegistration;
 
public class Server {
	
	public static void main(String[] args) {
		Server s = new Server();
		s.init();
	}
	
	public void init(){
		PlayerRegistration regStub;
		ClientHeartBeat heartBeatStub;
		Registry registry;
		HeartBeatChecker heartBeatChecker = new HeartBeatChecker();
		Thread heartBeatWorker = new Thread(heartBeatChecker);
		
		try {
			PlayerRegistrationImpl remRegObj = new PlayerRegistrationImpl();
			ClientHeartBeatImpl remHeartBeatObj = new ClientHeartBeatImpl();
			regStub = (PlayerRegistration) UnicastRemoteObject.exportObject(
					remRegObj, 0);
			heartBeatStub = (ClientHeartBeat) UnicastRemoteObject.exportObject(
					remHeartBeatObj, 0);
			registry = LocateRegistry.createRegistry(1099);
			registry.bind("registrationManager", regStub);
			registry.bind("heartBeatManager" , heartBeatStub);
			System.out.println("Server Started on port 1099");
			System.out.println("Services available Registration , Heartbeat ! ");
			heartBeatWorker.start();
			System.out.println("Heartbeat Checked Started !");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
