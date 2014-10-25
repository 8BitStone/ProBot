package Core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameChrono implements ActionListener{

	private GameCanvas gc;
	private ProBotGame game;
	
	public GameChrono(GameCanvas gc, ProBotGame game) {
		this.gc = gc;
		this.game = game;
	}	

	public void actionPerformed(ActionEvent arg0) {
		if(game.running){
			game.renew();
		}
		gc.gameRepaint();
	}
}
