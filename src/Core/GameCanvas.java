package Core;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.Timer;

@SuppressWarnings("serial")
public class GameCanvas extends Canvas implements KeyListener{
	
	private boolean repaintInProgress = false;
	private ProBotGame game;

	public GameCanvas(ProBotGame game) {
		this.game = game;
		setIgnoreRepaint(true);
		addKeyListener(this);
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
		game.gui.updateInGame(g);
		
		if(g != null){
			g.dispose();
		}
		strategy.show();
		Toolkit.getDefaultToolkit().sync();
		repaintInProgress = false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
	        case KeyEvent.VK_P:
	        case KeyEvent.VK_ESCAPE:
	            game.running = !game.running;
	            if(game.running){
	            	game.continueGame();
	            }else{
	            	game.menuManager.showPauseMenu();
	            }
	            break;
	        case KeyEvent.VK_SPACE:
	        case KeyEvent.VK_W:
	        	game.player.setMovingUp(true);
	            break;
	        case KeyEvent.VK_D:
	        	game.player.setMovingRight(true);
	            break;
	        case KeyEvent.VK_S:
	        	game.player.setMovingDown(true);
	            break;
	        case KeyEvent.VK_A:
	        	game.player.setMovingLeft(true);
	            break;
		}
	}

	@Override 
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
	        case KeyEvent.VK_SPACE:
	        case KeyEvent.VK_W:
	        	game.player.setMovingUp(false);
	            break;
	        case KeyEvent.VK_D:
	        	game.player.setMovingRight(false);
	            break;
	        case KeyEvent.VK_S:
	        	game.player.setMovingDown(false);
	            break;
	        case KeyEvent.VK_A:
	        	game.player.setMovingLeft(false);
	            break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}
