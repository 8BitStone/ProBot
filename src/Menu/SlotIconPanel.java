package Menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import Components.Slot;
import Components.Upgrade;

public class SlotIconPanel extends IconPanel {
	
	private Slot slot;
	private Upgrade upgrade = null;

	public SlotIconPanel(Slot slot) {
		super();
		this.slot = slot;
		if(slot.getUpgrade() != null){
			this.setUpgrade(slot.getUpgrade());
		}
	}
	
	public void setUpgrade(Upgrade u){
		this.upgrade = u;
	}
	
	public Slot getSlot(){
		return this.slot;
	}
	
	public void paint(Graphics g){
		g.drawImage(this.slot.getIcon(), 0, 0, null);
	}

}
