package Core;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.Timer;

import Components.Player;
import Components.World;


@SuppressWarnings("serial")
public class ProBotGame extends JFrame{
	
	public static final int BLOCK_SIZE = 20;
	
	protected Player player;
	protected Dimension windowSize;
	public boolean running = false; 
	private long lastLoopTime = System.currentTimeMillis();
	private GameCanvas canvas;
	protected Gui gui;
	protected MenuManager menuManager;
	
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
		this.canvas.requestFocus();
		canvas.createBufferStrategy(2);
		
		GameChrono chrono = new GameChrono(this.canvas, this);
		new Timer(16, chrono).start(); // 20 fot 50MHz, 16 for 60MHz
				
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
	
	public void startGame(){
		this.load();
		this.continueGame();
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
		this.canvas.requestFocus();
	}
}
