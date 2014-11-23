package Menu;

import java.awt.Graphics;

import Components.Upgrade;

public class UpgradeIconPanel extends IconPanel {
	
	private Upgrade upgrade;

	public UpgradeIconPanel(Upgrade upgrade) {
		super();
		this.upgrade = upgrade;
	}
	
	public Upgrade getUpgrade(){
		return this.upgrade;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
	}

}
