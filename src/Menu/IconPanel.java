package Menu;

import java.awt.Dimension;
import java.awt.Panel;

public abstract class IconPanel extends Panel {

	public IconPanel() {
		this.setPreferredSize(new Dimension(50, 50));
		this.setMaximumSize(new Dimension(50, 50));
	}
}
