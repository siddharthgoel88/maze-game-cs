package org.ds.server.impl;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.ds.server.GameState;
import org.ds.server.GameStateFactory;
import org.ds.server.Player;

public class HeartBeatChecker implements Runnable {
	GameState state;

	@Override
	public void run() {
		while (true) {
			state = GameStateFactory.getGameState();
			Collection<Player> players = state.getPlayers().values();
			long curTime = System.currentTimeMillis();
			
			for (Player p : players) {
				if (p.getLastActiveTime() != 0) {
					if ((curTime - p.getLastActiveTime()) > 10000) {
						System.out.println("Player " + p.getName()
								+ " has quit the game abruptly");
						state.cleanUpPlayer(p);
					}
				}
			}
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException ie) {

			}
		}
	}
}
