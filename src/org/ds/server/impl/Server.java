package org.ds.server.impl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.ds.server.PlayerRegistration;
 
public class Server {
	public static void main(String[] args) {
		PlayerRegistration regStub;
		Registry registry;

		try {
			PlayerRegistrationImpl remRegObj = new PlayerRegistrationImpl();
			regStub = (PlayerRegistration) UnicastRemoteObject.exportObject(
					remRegObj, 0);
			registry = LocateRegistry.createRegistry(1099);
			registry.bind("registrationManager", regStub);
			System.out.println("Server Started on port 1099");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
