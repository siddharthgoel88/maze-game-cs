package org.ds.client;

import org.ds.server.GameEndCheck;
import org.ds.server.GameState;

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
				System.out.println("\n\n Winner is " + state.getWinner().get("name") + " with " + state.getWinner().get("maxTreasure") + " number of treasures.\n\n");
				break;
			}
						
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
