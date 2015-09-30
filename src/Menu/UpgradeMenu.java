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
	private JPanel slotPanel;

	public UpgradeMenu(ProBotGame game) {
		super(game);
		slotPanel = new JPanel();
	}
	
	public void prepareSpecificMenu(){
		Dimension panelSize = new Dimension(game.getWindowSize().width/2-20, game.getWindowSize().height-20);
		this.menuWrapper.setLayout(null);
		menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		slotPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));
		menuPanel.setSize(panelSize);
		slotPanel.setSize(panelSize);
		
		menuPanel.setLocation(panelSize.width+25, 10);
		slotPanel.setLocation(15, 10);
		
		TitledBorder upgradeTB = new TitledBorder("Upgrades");
		menuPanel.setBorder(upgradeTB);
		TitledBorder slotTB = new TitledBorder("Slots");
		slotPanel.setBorder(slotTB);
		
		menuWrapper.add(slotPanel);
		
		this.reloadSlots();	
	}
	
	private void reloadSlots(){
		//@TODO: load all slots here
		/*IconPanel slot = new SlotIconPanel(new Slot(UpgradeType.leg, new Point(10, 10)));
		slot.addMouseListener(this);
		slotPanel.add(slot);*/
	}
	
	private void setUpgrade(Upgrade upgrade){
		if(!this.selectedIconPanel.getSlot().setUpgrade(upgrade)){
			return;
		}
		this.selectedIconPanel.setUpgrade(upgrade);
		this.selectedIconPanel.repaint();
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
		this.game.requestFocus();
	}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}

	public void mousePressed(MouseEvent arg0) {}

	public void mouseReleased(MouseEvent arg0) {}
}
