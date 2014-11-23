package Components;

import java.awt.Graphics;
import java.awt.Point;

public class Slot{

	private UpgradeType type;
	private Point position; // in percent of the size
	private Upgrade upgrade;
	
	public Slot(UpgradeType type, Point position) {
		this.type = type;
		this.position = position;
	}

	public UpgradeType getType() {
		return type;
	}

	public Point getPosition() {
		return position;
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
	
	public void paintInGame(Graphics g){
		
	}

}
