package Core;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class UpgradeMenu extends Menu{

	public UpgradeMenu(ProBotGame game) {
		super(game);
	}
	
	public void prepareSpecificMenu(){
		menuPanel.setLayout(new GridLayout(100, 1, 0, 30));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		
		}		
	}
}
