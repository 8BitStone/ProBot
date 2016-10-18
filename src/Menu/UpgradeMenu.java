package Menu;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Components.Body;
import Components.BodyPart;
import Components.Head;
import Components.Limbs;
import Components.Robot;
import Components.Upgrade;
import Components.UpgradeType;
import Core.ProBotGame;

public class UpgradeMenu extends Menu implements MouseListener{
	
	private JPanel bodypartPanel;
	private JPanel upgradePanel;
	
	private BodypartIconPanel activeBodypartIconPanel = null;
	private BodypartSummaryPanel activeBodypartSummaryPanel = null;

	public UpgradeMenu(ProBotGame game) {
		super(game);
		bodypartPanel = new JPanel();
		upgradePanel = new JPanel();
	}
	
	public void prepareSpecificMenu(){
		Dimension panelSize = new Dimension(game.getWindowSize().width/3-20, game.getWindowSize().height-20);
		this.menuWrapper.setLayout(null);
		menuPanel.setLayout(new GridLayout(3, 1));
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
		bodyPart.addUpgrade(upgrade);
		upgrade.setUsed(true);
	}
	
	private void removeUpgrade(Upgrade upgrade, BodyPart bodyPart){
		bodyPart.removeUpgrade(upgrade);
		upgrade.setUsed(false);
	}
	
	private void removeAllUpgrades(BodyPart bodyPart){
		for(Upgrade u : bodyPart.getUpgrades()){
			if(u != null){
				this.removeUpgrade(u, bodyPart);
			}
		}
	}
	
	private void setBodyPart(BodyPart bodypart, Robot robot){
		switch (this.activeBodypartSummaryPanel.getBodypart().getType()){
		case Head:
			robot.setHead((Head) bodypart);
			break;
		case Body:
			robot.setBody((Body) bodypart);
			break;
		case Limbs:
			robot.setLimbs((Limbs) bodypart);
			break;
		}
	}
	
	private void showMatchingUpgrades(UpgradeType type){
		this.upgradePanel.removeAll();
		
		ArrayList<Upgrade> upgrades = this.game.getUpgradeManager().getUpgradesByType(type);
		for(Upgrade u : upgrades){
			UpgradeIconPanel uip = new UpgradeIconPanel(u);
			uip.addMouseListener(this);
			upgradePanel.add(uip);
		}
		
		this.upgradePanel.paintAll(this.upgradePanel.getGraphics());
	}
	
	private void showMatchingBodyparts(UpgradeType type){
		this.bodypartPanel.removeAll();
		
		ArrayList<BodyPart> bodyparts = this.game.getUpgradeManager().getBodypartsByType(type);
		for(BodyPart b : bodyparts){
			BodypartIconPanel bpip = new BodypartIconPanel(b);
			bpip.addMouseListener(this);
			if(b.isUsed()){
				this.activeBodypartIconPanel = bpip;
			}
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
			this.activeBodypartSummaryPanel = (BodypartSummaryPanel)c;
			break;
		case "Menu.BodypartIconPanel":
			showMatchingUpgrades(((BodypartIconPanel)c).getBodypart().getType());
			if((BodypartIconPanel)c != this.activeBodypartIconPanel){
				this.removeAllUpgrades(this.activeBodypartIconPanel.getBodypart());
				this.setBodyPart(((BodypartIconPanel)c).getBodypart(), this.game.getPlayer());
				this.activeBodypartIconPanel.repaint();
				c.repaint();
			}
			this.activeBodypartIconPanel = (BodypartIconPanel)c;
			this.activeBodypartSummaryPanel.setBodypart(((BodypartIconPanel)c).getBodypart());
			this.activeBodypartSummaryPanel.repaint();
			break;
		case "Menu.UpgradeIconPanel":
			Upgrade u = ((UpgradeIconPanel)c).getUpgrade();
			if(u.isUsed()){
				this.removeUpgrade(u, this.activeBodypartIconPanel.getBodypart());
			}else{
				this.setUpgrade(u, this.activeBodypartIconPanel.getBodypart());
			}
			this.activeBodypartSummaryPanel.repaint();
			break;
		}
		this.game.requestFocus();
	}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}

	public void mousePressed(MouseEvent arg0) {}

	public void mouseReleased(MouseEvent arg0) {}
}
