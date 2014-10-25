package Core;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import Components.Player;
import Components.World;


@SuppressWarnings("serial")
public class ProBotGame extends JFrame implements KeyListener{
	
	public static final int BLOCK_SIZE = 10;
	
	protected Player player;
	protected Dimension windowSize;
	public boolean running = true;
	private long lastLoopTime = System.currentTimeMillis();
	
	public ProBotGame() {
		//For JFrame
		super("ProBot");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		this.load();
		
		GameCanvas canvas = new GameCanvas(this);
		add(canvas, BorderLayout.CENTER);
		windowSize = new Dimension(800, 800);
		setSize(windowSize);
		setVisible(true); 
		canvas.createBufferStrategy(2);
		
		addKeyListener(this);
		
	}


	public void load(){
		
		this.player = new Player(
				new Point(0, 0), 
				30, 
				50, 
				100, 
				100, 
				new World("world3"),
				"Player",
				Color.GREEN,
				100
		);
		
		System.out.println("loading world");
		this.player.getCurrentWorld().load();
		System.out.println("loading player");
		this.player.load();
	}
	
	public void renew(){
		long deltaTime = System.currentTimeMillis() - lastLoopTime;
		this.lastLoopTime = System.currentTimeMillis();
		player.move(deltaTime);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
	        case KeyEvent.VK_P:
	            this.running = !this.running;
	            break;
	        case KeyEvent.VK_SPACE:
	        case KeyEvent.VK_W:
	            player.setMovingUp(true);
	            break;
	        case KeyEvent.VK_D:
	            player.setMovingRight(true);
	            break;
	        case KeyEvent.VK_S:
	            player.setMovingDown(true);
	            break;
	        case KeyEvent.VK_A:
	            player.setMovingLeft(true);
	            break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
	        case KeyEvent.VK_SPACE:
	        case KeyEvent.VK_W:
	            player.setMovingUp(false);
	            break;
	        case KeyEvent.VK_D:
	            player.setMovingRight(false);
	            break;
	        case KeyEvent.VK_S:
	            player.setMovingDown(false);
	            break;
	        case KeyEvent.VK_A:
	            player.setMovingLeft(false);
	            break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

}
