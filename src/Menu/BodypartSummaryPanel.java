package Menu;

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
		g.drawImage(this.bodypart.getIcon(), 0, 0, null);
	}
	
	public BodyPart getBodypart(){
		return bodypart;
	}

}
