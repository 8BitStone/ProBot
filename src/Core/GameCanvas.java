package Core;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.Timer;

@SuppressWarnings("serial")
public class GameCanvas extends Canvas {
	
	private boolean repaintInProgress = false;
	private ProBotGame game;

	public GameCanvas(ProBotGame game) {
		this.game = game;
		setIgnoreRepaint(true);
		GameChrono chrono = new GameChrono(this, game);
		new Timer(16, chrono).start(); // 20 fot 50MHz, 16 for 60MHz
	}
	
	public void inRepaint(){
		if(repaintInProgress){
			return;
		}
		repaintInProgress = true;
		
		BufferStrategy strategy = getBufferStrategy();
		Graphics g = strategy.getDrawGraphics();
		
		game.player.getCurrentWorld().paint(g, game.windowSize, game.player.getPosition());
		game.player.paint(g, new Point(game.windowSize.width/2, game.windowSize.height/2));
		
		if(g != null){
			g.dispose();
		}
		strategy.show();
		Toolkit.getDefaultToolkit().sync();
		repaintInProgress = false;
	}
}
