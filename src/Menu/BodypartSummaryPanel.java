package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Components.BodyPart;
import Components.Upgrade;

public class BodypartSummaryPanel extends IconPanel {
	
	private BodyPart bodypart;

	public BodypartSummaryPanel(BodyPart bodypart) {
		super();
		this.bodypart = bodypart;
	}
	
	public void paint(Graphics g){
		//g.drawImage(this.bodypart.getIcon(), 0, 0, null);
		g.drawString(this.bodypart.getName(), 8, 18);
		g.setColor(Color.ORANGE);
		Upgrade[] upgrades = this.bodypart.getUpgrades();
		for(int i = 0; i < upgrades.length; i++){
			if(upgrades[i] == null) continue;
			g.drawString(upgrades[i].getName(), 8, (i+2)*18);
		}
	}
	
	public BodyPart getBodypart(){
		return bodypart;
	}

}
