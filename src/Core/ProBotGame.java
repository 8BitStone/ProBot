package Core;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Components.Player;
import Components.Robot;
import Components.World;


public class ProBotGame extends JFrame implements KeyListener{
	
	public static final int BLOCK_SIZE = 10;
	
	private Player player;
	private GameThread gameThread;
	private JPanel panel = new JPanel();
	private Dimension windowSize;
	private Graphics g;
	public boolean running = true;
	private long lastLoopTime = System.currentTimeMillis();
	
	public ProBotGame() {
		//For JFrame
		super("ProBot");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(100, 100);
		setBackground(Color.white);
		windowSize = new Dimension(800, 800);
		panel.setBackground(Color.white);
		panel.setSize(windowSize);
		getContentPane().add(panel);
		setSize(panel.getSize());
		setVisible(true);
		
		addKeyListener(this);
		
		this.player = new Player(
				new Point(0, 0), 
				100, 
				100, 
				100, 
				100, 
				new World("World1"),
				"Player",
				Color.GREEN,
				100
		);
		
		gameThread = new GameThread(this);
	}


	public void load(){
		System.out.println("loading world");
		this.player.getCurrentWorld().load();
		System.out.println("set Player position");
		this.player.setPosition
		(
				new Point
				(
						(int)Math.floor(this.player.getCurrentWorld().getWidth()/2*10), 
						(int)Math.floor(this.player.getCurrentWorld().getHeight()/2*10)
				)
		);
		System.out.println("repainting");
		this.repaint();
		System.out.println("starting thread");
		gameThread.run();
	}
	
	public void renew(){
		long deltaTime = System.currentTimeMillis() - lastLoopTime;
		this.lastLoopTime = System.currentTimeMillis();
		player.move(deltaTime);
		this.repaint();
	}
	
	public void repaint(){
		Graphics g = panel.getGraphics();
		player.getCurrentWorld().paint(g, windowSize, player.getPosition());
		player.paint(g, new Point(windowSize.width/2, windowSize.height/2));
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
