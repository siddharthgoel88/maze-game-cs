package org.ds.server.impl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.ds.client.ServerProperties;
import org.ds.server.ClientHeartBeat;
import org.ds.server.GameEndCheck;
import org.ds.server.GameState;
import org.ds.server.GameStateFactory;
import org.ds.server.MovePlayers;
import org.ds.server.PlayerRegistration;
 
public class Server {
	
	public static void main(String[] args) {
		Server s = new Server();
		s.init(args);
	}
	
	public void init(String[] args){
		int boardSize,numTreasures;
		PlayerRegistration regStub;
		ClientHeartBeat heartBeatStub;
		MovePlayers movePlayersStub;
		GameEndCheck gameEndStub;
		
		Registry registry;
		HeartBeatChecker heartBeatChecker = new HeartBeatChecker();
		Thread heartBeatWorker = new Thread(heartBeatChecker);
		
		boardSize=Integer.parseInt(args[0]);
		numTreasures = Integer.parseInt(args[1]);
		GameState state = GameStateFactory.getGameState();
		state.setBoardSize(boardSize);
		state.setTotalNumTreasures(numTreasures);
		System.out.println("Command line parameters passed to GameState");
		
		try {
			PlayerRegistrationImpl remRegObj = new PlayerRegistrationImpl();
			ClientHeartBeatImpl remHeartBeatObj = new ClientHeartBeatImpl();
			MovePlayersImpl movePlayersObj = new MovePlayersImpl();
			GameEndCheckImpl gameEndObj = new GameEndCheckImpl();
			
			regStub = (PlayerRegistration) UnicastRemoteObject.exportObject(
					remRegObj, 0);
			heartBeatStub = (ClientHeartBeat) UnicastRemoteObject.exportObject(
					remHeartBeatObj, 0);
			
			movePlayersStub = (MovePlayers) UnicastRemoteObject.exportObject(
					movePlayersObj, 0);
			
			gameEndStub = (GameEndCheck) UnicastRemoteObject.exportObject(
					gameEndObj, 0);
			
			registry = LocateRegistry.createRegistry(ServerProperties.getServerPort());
			registry.bind("registrationManager", regStub);
			registry.bind("heartBeatManager" , heartBeatStub);
			registry.bind("movePlayers",movePlayersStub);
			registry.bind("gameEndCheck",gameEndStub);
			
			System.out.println("Server Started on port 1099");
			System.out.println("Services available Registration , Heartbeat , Move Players!, GameEndCheck ");
			heartBeatWorker.start();
			System.out.println("Heartbeat Checked Started !");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
