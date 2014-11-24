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
	
	public void paint(Graphics g){
		g.drawImage(this.upgrade.getIcon(), 0, 0, null);
	}

}
