package Core;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Components.Player;
import Components.World;


@SuppressWarnings("serial")
public class ProBotGame extends JFrame implements KeyListener, ActionListener{
	
	public static final int BLOCK_SIZE = 10;
	
	public static final String CMD_START = "start";
	
	protected Player player;
	protected Dimension windowSize;
	public boolean running = true;
	private long lastLoopTime = System.currentTimeMillis();
	private GameCanvas canvas;
	private JPanel menuPanel;
	
	public ProBotGame() {
		//For JFrame
		super("ProBot");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		this.load();
		
		this.canvas = new GameCanvas(this);
		this.menuPanel = new JPanel();
		this.menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 100));
		add(this.canvas, BorderLayout.CENTER);
		//add(this.menuPanel, BorderLayout.CENTER);
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
		System.out.println("running");
	}
	
	public void renew(){
		long deltaTime = System.currentTimeMillis() - lastLoopTime;
		this.lastLoopTime = System.currentTimeMillis();
		player.move(deltaTime);
	}
	
	private void showPauseMenu(){
		getContentPane().remove(this.canvas);
		
		menuPanel.removeAll();
		
		JPanel mainMenu = new JPanel();
		mainMenu.setLayout(new GridLayout(100, 1, 0, 30));
		
		JButton start = new JButton("Continue");
		start.setActionCommand(CMD_START);
		start.addActionListener(this);
		
		mainMenu.add(start);
			
		menuPanel.add(mainMenu);
		add(this.menuPanel, BorderLayout.CENTER);
		setVisible(true);
	}
	
	private void showGame(){
		add(this.canvas, BorderLayout.CENTER);
		this.canvas.createBufferStrategy(2);
		this.canvas.show();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
	        case KeyEvent.VK_P:
	        case KeyEvent.VK_ESCAPE:
	            this.running = !this.running;
	            if(this.running){
	            	this.lastLoopTime = System.currentTimeMillis();
	            	this.showGame();
	            }else{
	            	this.showPauseMenu();
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


	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case CMD_START:
			this.running = true;
			this.lastLoopTime = System.currentTimeMillis();
        	this.showGame();
			break;
		}	
	}

}
