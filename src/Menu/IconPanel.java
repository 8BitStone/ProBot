package Menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Panel;

public abstract class IconPanel extends Panel {

	public IconPanel() {
		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension(50, 50));
		this.setMaximumSize(new Dimension(50, 50));
	}
}
