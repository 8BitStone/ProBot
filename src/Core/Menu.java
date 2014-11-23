package Core;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public abstract class Menu implements ActionListener{
	
	public static final String CMD_START = "start";
	public static final String CMD_CONTINUE = "continue";
	
	/**
	 * Wrapper for the menu
	 */
	protected JPanel menuWrapper;
	
	/**
	 * the specific Menu. Is added to the Wrapper
	 */
	protected JPanel menuPanel;
	protected ProBotGame game;

	public Menu(ProBotGame game) {
		this.game = game;
		this.menuWrapper = new JPanel();
		this.menuWrapper.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 100));
		this.menuWrapper.setFocusable(false);
		this.menuPanel = new JPanel();
		this.menuPanel.setFocusable(false);
	}
	
	public void show(){
		game.getContentPane().removeAll();
		menuWrapper.removeAll();
		menuPanel.removeAll();
		
		this.prepareSpecificMenu();
		
		this.menuWrapper.add(this.menuPanel);
		game.add(this.menuWrapper, BorderLayout.CENTER);
		game.setVisible(true);
	}
	
	public abstract void prepareSpecificMenu();
	
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
