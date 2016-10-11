package Menu;

import java.awt.Color;
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
		if(upgrade.isUsed()){
			g.setColor(Color.red);
		}else{
			g.setColor(Color.black);
		}
		g.drawString(this.upgrade.getName(), 8, 18);
	}
	
	public Upgrade getUpgrade(){
		return upgrade;
	}


}
