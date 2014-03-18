package Core;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Components.Robot;
import Components.World;


public class ProBotGame extends JFrame implements KeyListener{
	
	public static final int BLOCK_SIZE = 10;
	
	private Robot player;
	private GameThread gameThread;
	private JPanel panel = new JPanel();
	private Dimension windowSize;
	public boolean running = true;

	public static void main(String[] args) {
		new ProBotGame().load();
	}
	
	public ProBotGame() {
		//For JFrame
		super("ProBot");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(100, 100);
		setBackground(Color.red);
		windowSize = new Dimension(800, 800);
		panel.setBackground(Color.white);
		panel.setSize(windowSize);
		getContentPane().add(panel);
		setSize(panel.getSize());
		setVisible(true);
		
		addKeyListener(this);
		
		this.player = new Robot(
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
		
		gameThread = new GameThread(this, 10);
	}


	public void load(){
		System.out.println("loading world");
		this.player.getCurrentWorld().load();
		System.out.println("complete");
		this.player.setPosition
		(
				new Point
				(
						(int)Math.floor(this.player.getCurrentWorld().getWidth()/2), 
						(int)Math.floor(this.player.getCurrentWorld().getHeight()/2)
				)
		);
		System.out.println("repainting");
		this.repaint();
		System.out.println("complete");
		System.out.println("starting thread");
		gameThread.run();
	}
	
	public void renew(){
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
	        case KeyEvent.VK_D:
	            player.move('d');
	            break;
	        case KeyEvent.VK_SPACE:
	        case KeyEvent.VK_W:
	        	player.move('w');
	            break;
	        case KeyEvent.VK_A:
	        	player.move('a');
	            break;
	        case KeyEvent.VK_S:
	        	player.move('s');
	            break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

}
