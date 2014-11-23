package Core;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class MenuManager implements ActionListener{
	
	public static final String CMD_START = "start";
	public static final String CMD_CONTINUE = "continue";
	
	private JPanel menuPanel;
	private ProBotGame game;

	public MenuManager(ProBotGame game) {
		this.game = game;
		this.menuPanel = new JPanel();
		this.menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 100));
		this.menuPanel.setFocusable(false);
	}
	
	public void showPauseMenu(){
		game.getContentPane().removeAll();
		menuPanel.removeAll();
		
		JPanel pauseMenu = new JPanel();
		pauseMenu.setLayout(new GridLayout(100, 1, 0, 30));
		
		JButton start = new JButton("Continue");
		start.setActionCommand(CMD_CONTINUE);
		start.addActionListener(this);
		
		pauseMenu.add(start);
			
		menuPanel.add(pauseMenu);
		game.add(this.menuPanel, BorderLayout.CENTER);
		game.setVisible(true);
	}
	
	public void showMainMenu(){
		game.getContentPane().removeAll();
		menuPanel.removeAll();
		
		JPanel mainMenu = new JPanel();
		mainMenu.setLayout(new GridLayout(100, 1, 0, 30));
		
		JButton start = new JButton("Start");
		start.setActionCommand(CMD_START);
		start.addActionListener(this);
		
		mainMenu.add(start);
			
		menuPanel.add(mainMenu);
		game.add(this.menuPanel, BorderLayout.CENTER);
		game.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case CMD_START:
			game.startGame();
			break;
		case CMD_CONTINUE:
			game.continueGame();
			break;
		}	
	}

}
