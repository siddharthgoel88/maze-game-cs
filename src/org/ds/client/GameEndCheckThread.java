package org.ds.client;

import org.ds.server.GameEndCheck;
import org.ds.server.GameState;
import org.ds.server.Player;

public class GameEndCheckThread implements Runnable{

	GameState state;
	GameEndCheck gameEndObj;
	
	@Override
	public void run()
	{
		gameEndObj = RMIClientManagerFactory.getRMIClientManager().getGameEndStub();
		while(true){
			try{
			state = gameEndObj.checker();
			if(state == null){
				Thread.sleep(1000);
			}else{
				System.out.println("\n\n\nGame is over!\n\n\nFinal state is:");
				PlayGame.printState(state);
				System.out.println("\n\n\nFollowing is/are the winner(s) :\n");
				
				for(Player p: state.getWinner()){
					System.out.println("Name: "+ p.getName() + "\t# of Treasures: " + p.getNumTreasures());
				}
				
				break;
			}
						
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
