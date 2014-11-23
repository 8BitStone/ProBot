package Core;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class UpgradeMenu extends Menu{

	public UpgradeMenu(ProBotGame game) {
		super(game);
	}
	
	public void prepareSpecificMenu(){
		Dimension panelSize = new Dimension(game.windowSize.width/2-20, game.windowSize.height-20);
		this.menuWrapper.setLayout(null);
		menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		menuPanel.setLocation(15, 10);
		menuPanel.setSize(panelSize);
		for(int i = 1; i <= 6; i++){
			menuPanel.add(new IconPanel());
		}
		
		TitledBorder upgradeTB = new TitledBorder("Upgrades");
		menuPanel.setBorder(upgradeTB);
		
		JPanel slotPanel = new JPanel();
		slotPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));
		slotPanel.setLocation(panelSize.width+25, 10);
		slotPanel.setSize(panelSize);
		slotPanel.add(new IconPanel());
		slotPanel.add(new IconPanel());
		
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
