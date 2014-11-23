package Menu;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Components.Upgrade;
import Core.ProBotGame;

public class UpgradeMenu extends Menu{

	public UpgradeMenu(ProBotGame game) {
		super(game);
	}
	
	public void prepareSpecificMenu(){
		Dimension panelSize = new Dimension(game.getWindowSize().width/2-20, game.getWindowSize().height-20);
		this.menuWrapper.setLayout(null);
		menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		menuPanel.setSize(panelSize);
		for(int i = 1; i <= 6; i++){
			menuPanel.add(new UpgradeIconPanel(new Upgrade(null, "bla", null)));
		}
		
		TitledBorder upgradeTB = new TitledBorder("Upgrades");
		menuPanel.setBorder(upgradeTB);
		
		JPanel slotPanel = new JPanel();
		slotPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));
		slotPanel.setSize(panelSize);
		slotPanel.add(new SlotIconPanel(null));
		slotPanel.add(new SlotIconPanel(null));
		
		menuPanel.setLocation(panelSize.width+25, 10);
		slotPanel.setLocation(15, 10);
		
		TitledBorder slotTB = new TitledBorder("Slots");
		slotPanel.setBorder(slotTB);
		
		menuWrapper.add(slotPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		
		}		
	}
}
