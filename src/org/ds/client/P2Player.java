package org.ds.client;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class P2Player {
	
	
	public static void main(String[] args) throws SocketException {
		new P2Player().init(args);
	}
	
	public void init( String [] args ) throws  SocketException{
		String machineIP = null;
		
		if(args.length == 0){
			System.out.println("The base P2P IP address is not specified.");
			System.exit(1);
		}else{
			machineIP = args[0];
		}
		
		Enumeration<NetworkInterface> interfaces =  NetworkInterface.getNetworkInterfaces();
		while(interfaces.hasMoreElements()){
			NetworkInterface n=(NetworkInterface) interfaces.nextElement();
            Enumeration inetAddresses = n.getInetAddresses();
            while(inetAddresses.hasMoreElements())
            {
                InetAddress address = (InetAddress) inetAddresses.nextElement();
                if(address.getHostAddress().equals(machineIP)) {
                	
                }
            }
		}
	}
}
