package org.ds.client;

public class ServerProperties {
	
	private static String serverHostname = "127.0.0.1";
	private static int serverPort = 1099;
	
	public static String getServerHostname() {
		return serverHostname;
	}
	
	public static void setServerHostname(String serverHostname) {
		ServerProperties.serverHostname = serverHostname;
	}
	
	public static int getServerPort() {
		return serverPort;
	}
	
	public static void setServerPort(int serverPort) {
		ServerProperties.serverPort = serverPort;
	}
}
