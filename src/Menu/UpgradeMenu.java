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

import Components.BodyPart;
import Components.Upgrade;
import Components.UpgradeType;
import Core.ProBotGame;

public class UpgradeMenu extends Menu implements MouseListener{
	
	private JPanel bodypartPanel;
	private JPanel upgradePanel;

	public UpgradeMenu(ProBotGame game) {
		super(game);
		bodypartPanel = new JPanel();
		upgradePanel = new JPanel();
	}
	
	public void prepareSpecificMenu(){
		Dimension panelSize = new Dimension(game.getWindowSize().width/3-20, game.getWindowSize().height-20);
		this.menuWrapper.setLayout(null);
		menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		bodypartPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		upgradePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		menuPanel.setSize(panelSize);
		bodypartPanel.setSize(panelSize);
		upgradePanel.setSize(panelSize);
		
		menuPanel.setLocation(15, 10);
		bodypartPanel.setLocation(panelSize.width+25, 10);
		upgradePanel.setLocation(panelSize.width*2+35, 10);
		
		TitledBorder robotTB = new TitledBorder("Robot");
		menuPanel.setBorder(robotTB);
		TitledBorder bodypartsTB = new TitledBorder("Bodyparts");
		bodypartPanel.setBorder(bodypartsTB);
		TitledBorder upgradesTB = new TitledBorder("Upgrades");
		upgradePanel.setBorder(upgradesTB);
		
		menuWrapper.add(bodypartPanel);
		menuWrapper.add(upgradePanel);
		
		this.reloadBodySummary();	
	}
	
	private void reloadBodySummary(){
		//@TODO: load all slots here
		/*IconPanel slot = new SlotIconPanel(new Slot(UpgradeType.leg, new Point(10, 10)));
		slot.addMouseListener(this);
		slotPanel.add(slot);*/
		menuPanel.removeAll();
		IconPanel headPanel = new BodypartSummaryPanel(this.game.getPlayer().getHead());
		headPanel.addMouseListener(this);
		menuPanel.add(headPanel);
		IconPanel bodyPanel = new BodypartSummaryPanel(this.game.getPlayer().getBody());
		bodyPanel.addMouseListener(this);
		menuPanel.add(bodyPanel);
		IconPanel limbsPanel = new BodypartSummaryPanel(this.game.getPlayer().getLimbs());
		limbsPanel.addMouseListener(this);
		menuPanel.add(limbsPanel);
		
		menuPanel.paintAll(menuPanel.getGraphics());
	}
	
	private void setUpgrade(Upgrade upgrade, BodyPart bodyPart){
		
	}
	
	private void setBodyPart(BodyPart bodypart){
		
	}
	
	private void showMatchingUpgrades(UpgradeType type){
		this.upgradePanel.removeAll();
		
		ArrayList<Upgrade> upgrades = this.game.getUpgradeManager().getUpgradesByType(type);
		for(Upgrade u : upgrades){
			UpgradeIconPanel uip = new UpgradeIconPanel(u);
			uip.addMouseListener(this);
			upgradePanel.add(uip);
		}
		
		this.upgradePanel.paintAll(upgradePanel.getGraphics());
	}
	
	private void showMatchingBodyparts(UpgradeType type){
		this.bodypartPanel.removeAll();
		
		ArrayList<BodyPart> bodyparts = this.game.getUpgradeManager().getBodypartsByType(type);
		for(BodyPart b : bodyparts){
			BodypartIconPanel bpip = new BodypartIconPanel(b);
			bpip.addMouseListener(this);
			bodypartPanel.add(bpip);
		}
		
		this.bodypartPanel.paintAll(bodypartPanel.getGraphics());
	}

	public void mouseClicked(MouseEvent e) {
		Component c = e.getComponent();
		switch(c.getClass().getName()){
		case "Menu.BodypartSummaryPanel":
			showMatchingBodyparts(((BodypartSummaryPanel)c).getBodypart().getType());
			this.upgradePanel.removeAll();
			break;
		case "Menu.BodypartIconPanel":
			showMatchingUpgrades(((BodypartIconPanel)c).getBodypart().getType());
			//@ToDo: set Bodypart
			break;
		case "Menu.UpgradeIconPanel":
			//@ToDo: set upgrade
			break;
		}
		this.reloadBodySummary();
		this.game.requestFocus();
	}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}

	public void mousePressed(MouseEvent arg0) {}

	public void mouseReleased(MouseEvent arg0) {}
}
