package Core;

import static java.lang.Thread.MAX_PRIORITY;

public class GameThread implements Runnable{
	
	private long tic = 10; //ms
	private ProBotGame game;
	private Thread t;

	public GameThread(ProBotGame game) {
		this.game = game;
		this.t = new Thread();
		this.t.setName("GameThread");
		this.t.setPriority(MAX_PRIORITY);
	}

	public void run(){
		while(!this.t.isInterrupted()){
			if(!game.running){
				continue;
			}
			try {
				this.t.sleep(tic);
			} catch (InterruptedException e) {}
			game.renew();
		}
	}
}
