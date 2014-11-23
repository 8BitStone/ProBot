package Core;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;

public class MainMenu extends Menu {

	public MainMenu(ProBotGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void prepareSpecificMenu() {
		menuPanel.setLayout(new GridLayout(100, 1, 0, 30));
		
		JButton start = new JButton("Start");
		start.setActionCommand(CMD_START);
		start.addActionListener(this);
		
		menuPanel.add(start);
			
		game.add(this.menuWrapper, BorderLayout.CENTER);
		game.setVisible(true);		
	}

}
