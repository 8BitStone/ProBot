package Menu;

import java.awt.Color;
import java.awt.Graphics;

import Components.BodyPart;
import Components.Upgrade;

public class BodypartIconPanel extends IconPanel {
	
	private BodyPart bodypart;

	public BodypartIconPanel(BodyPart bodypart) {
		super();
		this.bodypart = bodypart;
	}
	
	public void paint(Graphics g){
		g.drawImage(this.bodypart.getIcon(), 0, 0, null);
		if(bodypart.isUsed()){
			g.setColor(Color.red);
		}else{
			g.setColor(Color.black);
		}
		g.drawString(this.bodypart.getName(), 8, 18);
	}
	
	public BodyPart getBodypart(){
		return bodypart;
	}

}
