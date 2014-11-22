package Core;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import Components.Player;
import Components.World;


@SuppressWarnings("serial")
public class ProBotGame extends JFrame implements KeyListener{
	
	public static final int BLOCK_SIZE = 20;
	
	protected Player player;
	protected Dimension windowSize;
	public boolean running = false; 
	private long lastLoopTime = System.currentTimeMillis();
	private GameCanvas canvas;
	protected Gui gui;
	private MenuManager menuManager;
	
	public ProBotGame() {
		//For JFrame
		super("ProBot");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		this.menuManager = new MenuManager(this);
		this.gui = new Gui(this);
		
		this.canvas = new GameCanvas(this);
		add(this.canvas, BorderLayout.CENTER);
		windowSize = new Dimension(1280, 960);
		setSize(windowSize);
		setVisible(true); 
		canvas.createBufferStrategy(2);
		
		GameChrono chrono = new GameChrono(this.canvas, this);
		new Timer(16, chrono).start(); // 20 fot 50MHz, 16 for 60MHz
		
		addKeyListener(this);
		
		this.menuManager.showMainMenu();
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
		System.out.println("running");
	}
	
	public void renew(){
		long deltaTime = System.currentTimeMillis() - lastLoopTime;
		this.lastLoopTime = System.currentTimeMillis();
		player.move(deltaTime);
	}
	
	private void showGame(){
		getContentPane().removeAll();
		this.requestFocus();
		getContentPane().add(this.canvas, BorderLayout.CENTER);
		this.canvas.createBufferStrategy(2);
		this.canvas.setVisible(true);
	}
	
	public void startGame(){
		this.load();
		this.continueGame();
	}
	
	public void continueGame(){
		this.running = true;
		this.lastLoopTime = System.currentTimeMillis();
    	this.showGame();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
	        case KeyEvent.VK_P:
	        case KeyEvent.VK_ESCAPE:
	            this.running = !this.running;
	            if(this.running){
	            	this.continueGame();
	            }else{
	            	menuManager.showPauseMenu();
	            }
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
