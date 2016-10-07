package Menu;

import java.awt.Graphics;

import Components.Upgrade;

public class UpgradeIconPanel extends IconPanel {
	
	private Upgrade upgrade;

	public UpgradeIconPanel(Upgrade upgrade) {
		super();
		this.upgrade = upgrade;
	}
	
	public void paint(Graphics g){
		g.drawImage(this.upgrade.getIcon(), 0, 0, null);
	}
	
	public Upgrade getUpgrade(){
		return upgrade;
	}


}
