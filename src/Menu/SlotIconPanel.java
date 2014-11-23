package Menu;

import java.awt.Color;
import java.awt.Graphics;

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
		this.setBackground(Color.red);
	}
	
	public Slot getSlot(){
		return this.slot;
	}
	
	public void paintComponent(Graphics g){
		if(this.upgrade == null){
			//draw slot icon
			super.paintComponents(g);
		}else{
			//draw upgrade icon
			super.paintComponents(g);
		}
	}

}
