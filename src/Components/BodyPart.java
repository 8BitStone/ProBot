package Components;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public abstract class BodyPart {
	
	private Upgrade[] upgrades;
	private UpgradeType type;
	private String name;
	
	public BodyPart(int upgradeCount, UpgradeType type, String name){
		this.upgrades = new Upgrade[upgradeCount];
		this.type = type;
		this.name = name;
	}

	public Upgrade[] getUpgrades() {
		return upgrades;
	}
	
	public void removeUpgrade(Upgrade upgrade) {
		for(int i = 0; i < this.upgrades.length; i++){
			if(this.upgrades[i] == upgrade){
				this.upgrades[i] = null;
				return;
			}
		}
	}
	
	public boolean addUpgrade(Upgrade upgrade, int position){
		if(upgrade.getType() == this.type){
			this.upgrades[position] = upgrade;
			return true;
		}
		return false;
	}

	public UpgradeType getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}

	public BufferedImage getIcon(){
		BufferedImage icon = null;
		try {
			URL url = getClass().getResource("/Icons/"+this.name.replaceAll("\\s","").toLowerCase()+".png");
			icon = ImageIO.read(new File(url.getPath()));
		} catch (IOException e) {}
		return icon;
	}
}
