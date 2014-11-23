package Menu;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Components.Slot;
import Components.Upgrade;
import Components.UpgradeType;
import Core.ProBotGame;

public class UpgradeMenu extends Menu implements MouseListener{
	
	private SlotIconPanel selectedIconPanel = null;

	public UpgradeMenu(ProBotGame game) {
		super(game);
	}
	
	public void prepareSpecificMenu(){
		Dimension panelSize = new Dimension(game.getWindowSize().width/2-20, game.getWindowSize().height-20);
		this.menuWrapper.setLayout(null);
		menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		menuPanel.setSize(panelSize);
		
		TitledBorder upgradeTB = new TitledBorder("Upgrades");
		menuPanel.setBorder(upgradeTB);
		
		JPanel slotPanel = new JPanel();
		slotPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));
		slotPanel.setSize(panelSize);
		IconPanel slot = new SlotIconPanel(new Slot(UpgradeType.leg, new Point(10, 10)));
		slot.addMouseListener(this);
		slotPanel.add(slot);
		
		menuPanel.setLocation(panelSize.width+25, 10);
		slotPanel.setLocation(15, 10);
		
		TitledBorder slotTB = new TitledBorder("Slots");
		slotPanel.setBorder(slotTB);
		
		menuWrapper.add(slotPanel);
	}
	
	private void setUpgrade(Upgrade upgrade){
		if(this.selectedIconPanel.getSlot().setUpgrade(upgrade)){
			System.out.println(upgrade);
			this.selectedIconPanel.setUpgrade(upgrade);
		}
		this.menuWrapper.paintAll(this.menuWrapper.getGraphics());
	}
	
	private void showMatchingUpgrades(SlotIconPanel slotIconPanel){
		this.selectedIconPanel = slotIconPanel;
		
		this.menuPanel.removeAll();
		
		ArrayList<Upgrade> upgrades = this.game.getUpgradeManager().getUpgradesByType(slotIconPanel.getSlot().getType());
		for(Upgrade u : upgrades){
			UpgradeIconPanel uip = new UpgradeIconPanel(u);
			uip.addMouseListener(this);
			menuPanel.add(uip);
		}
		
		this.menuWrapper.paintAll(menuWrapper.getGraphics());
	}

	public void mouseClicked(MouseEvent e) {
		Component c = e.getComponent();
		if(c.getClass() == SlotIconPanel.class){
			this.showMatchingUpgrades(((SlotIconPanel)c));
		}else{
			this.setUpgrade(((UpgradeIconPanel)c).getUpgrade());
		}
	}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}

	public void mousePressed(MouseEvent arg0) {}

	public void mouseReleased(MouseEvent arg0) {}
}
