package Menu;

import java.awt.Graphics;

import Components.Slot;
import Components.Upgrade;

public class SlotIconPanel extends IconPanel {
	
	private Slot slot;
	private Upgrade upgrade = null;

	public SlotIconPanel(Slot slot) {
		super();
		this.slot = slot;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
	}

}
