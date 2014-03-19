package Core;


public class GameThread extends Thread{
	
	private long tic = 50; //ms
	private long lastUpdateAt = System.currentTimeMillis() / 1000;
	private ProBotGame game;

	public GameThread(ProBotGame game, long tic) {
		this.tic = tic;
		this.game = game;
	}

	public void run(){
		while(true){
			if(!game.running){
				continue;
			}
			long tempDate = System.currentTimeMillis() / 1000;
			if((lastUpdateAt + tic) >= tempDate){
				lastUpdateAt = tempDate;
				game.renew();
			}
		}
	}
}
