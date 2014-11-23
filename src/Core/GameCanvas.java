package Core;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

@SuppressWarnings("serial")
public class GameCanvas extends Canvas{
	
	private boolean repaintInProgress = false;
	private ProBotGame game;

	public GameCanvas(ProBotGame game) {
		this.game = game;
		setIgnoreRepaint(true);
		setFocusable(false);
	}
	
	public void inRepaint(){
		if(repaintInProgress){
			return;
		}
		repaintInProgress = true;
		
		BufferStrategy strategy = getBufferStrategy();
		Graphics g = strategy.getDrawGraphics();
		
		game.getPlayer().getCurrentWorld().paint(g, game.getWindowSize(), game.getPlayer().getPosition());
		game.getPlayer().paint(g, new Point(game.getWindowSize().width/2, game.getWindowSize().height/2));
		game.gui.updateInGame(g);
		
		if(g != null){
			g.dispose();
		}
		strategy.show();
		Toolkit.getDefaultToolkit().sync();
		repaintInProgress = false;
	}
}
