package Components;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public abstract class BodyPart{
	
	private Upgrade[] upgrades;
	private UpgradeType type;
	private String name;
	private Boolean used = false;
	
	public BodyPart(int upgradeCount, UpgradeType type, String name){
		this.upgrades = new Upgrade[upgradeCount];
		this.type = type;
		this.name = name;
	}
	

	public Boolean isUsed() {
		return used;
	}


	public void setUsed(Boolean used) {
		this.used = used;
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
	
	public boolean addUpgrade(Upgrade upgrade){
		if(upgrade.getType() == this.type){
			int position = this.nextFreePositon();
			if(position == -1) return false;
			this.upgrades[position] = upgrade;
			return true;
		}
		return false;
	}
	
	private int nextFreePositon(){
		for(int i = 0; i < this.upgrades.length; i++){
			if(this.upgrades[i] == null){
				return i;
			}
		}
		return -1;
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
			//URL url = getClass().getResource("/Icons/"+this.name.replaceAll("\\s","").toLowerCase()+".png");
			URL url = getClass().getResource("/Icons/empty.png");
			icon = ImageIO.read(new File(url.getPath()));
		} catch (IOException e) {}
		return icon;
	}
}
