package Components;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Slot{

	private UpgradeType type;
	private Upgrade upgrade;
	
	public Slot(UpgradeType type, Point position) {
		this.type = type;
	}

	public UpgradeType getType() {
		return type;
	}

	public Upgrade getUpgrade() {
		return upgrade;
	}

	public boolean setUpgrade(Upgrade upgrade) {
		if(upgrade.getType() != this.type){
			return false;
		}
		this.upgrade = upgrade;
		return true;
	}
	
	public BufferedImage getIcon(){
		if(this.upgrade != null){
			return this.upgrade.getIcon();
		}
		BufferedImage icon = null;
		try {
			URL ulr = getClass().getResource("/Icons/empty.png");
			icon = ImageIO.read(new File(ulr.getPath()));
		} catch (IOException e) {}
		return icon;
	}

}
