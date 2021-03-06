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
import Menu.MenuManager;


@SuppressWarnings("serial")
public class ProBotGame extends JFrame implements KeyListener{
	
	public static final int BLOCK_SIZE = 20;
	
	private Player player;
	private Dimension windowSize;
	private boolean running = false; 
	private boolean started = false;
	private long lastLoopTime = System.currentTimeMillis();
	private GameCanvas canvas;
	protected Gui gui;
	protected MenuManager menuManager;
	private UpgradeManager upgradeManager;
	
	public ProBotGame() {
		//For JFrame
		super("ProBot");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		this.upgradeManager = new UpgradeManager();
		
		this.menuManager = new MenuManager(this);
		this.gui = new Gui(this);
		
		this.canvas = new GameCanvas(this);
		add(this.canvas, BorderLayout.CENTER);
		windowSize = new Dimension(1280, 960);
		setSize(new Dimension(getWindowSize().width+6, getWindowSize().height+29)); //plus the border size so windowSize is the Resolution of the Game
		setVisible(true); 
		canvas.createBufferStrategy(2);
		
		GameChrono chrono = new GameChrono(this.canvas, this);
		new Timer(16, chrono).start(); // 20 fot 50MHz, 16 for 60MHz
		
		addKeyListener(this);
				
		this.menuManager.showMainMenu();
		
		this.setFocusable(true);
		this.requestFocus();
	}

	public Dimension getWindowSize() {
		return windowSize;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public boolean isStartet(){
		return started;
	}
	
	public UpgradeManager getUpgradeManager(){
		return this.upgradeManager;
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
		this.player.load(this);
		System.out.println("running");
	}
	
	public void renew(){
		long deltaTime = System.currentTimeMillis() - lastLoopTime;
		this.lastLoopTime = System.currentTimeMillis();
		player.move(deltaTime);
	}
	
	public void startGame(){
		this.load();
		this.continueGame();
		this.started = true;
	}
	
	public void continueGame(){
		this.running = true;
		this.lastLoopTime = System.currentTimeMillis();
    	this.showGame();
	}
	
	private void showGame(){
		getContentPane().removeAll();
		getContentPane().add(this.canvas, BorderLayout.CENTER);
		this.canvas.createBufferStrategy(2);
		this.canvas.setVisible(true);
	}
	
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
	        case KeyEvent.VK_U:
	        	if(!this.started){
	        		return;
	        	}
	        	this.running = !this.running;
	        	if(this.running){
	        		this.continueGame();
	        	}else{
	        		this.menuManager.showUpgradeMenu();
	        	}
	        	break;
	        case KeyEvent.VK_SPACE:
	        case KeyEvent.VK_W:
	        	this.player.setMovingUp(true);
	            break;
	        case KeyEvent.VK_D:
	        	this.player.setMovingRight(true);
	            break;
	        case KeyEvent.VK_S:
	        	this.player.setMovingDown(true);
	            break;
	        case KeyEvent.VK_A:
	        	this.player.setMovingLeft(true);
	            break;
		}
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
	        case KeyEvent.VK_SPACE:
	        case KeyEvent.VK_W:
	        	this.player.setMovingUp(false);
	            break;
	        case KeyEvent.VK_D:
	        	this.player.setMovingRight(false);
	            break;
	        case KeyEvent.VK_S:
	        	this.player.setMovingDown(false);
	            break;
	        case KeyEvent.VK_A:
	        	this.player.setMovingLeft(false);
	            break;
		}
	}

	public void keyTyped(KeyEvent e) {}

}
