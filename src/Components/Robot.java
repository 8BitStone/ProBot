package Components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import static Core.ProBotGame.BLOCK_SIZE;

public class Robot extends Living{
	
	private String name;
	private Color color;
	private int energy;
	private ArrayList<Upgrade> upgradeesInstalled;
	
	

	public Robot(Point position, int baseSpeed, int baseForce, int healt,
			int maxhealth, World currentWorld, String name, Color color, int energy) {
		super(position, baseSpeed, baseForce, healt, maxhealth, currentWorld);
		this.name = name;
		this.color = color;
		this.energy = energy;
		this.upgradeesInstalled = null;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public ArrayList<Upgrade> getUpgradeesInstalled() {
		return upgradeesInstalled;
	}

	public void setUpgradeesInstalled(ArrayList<Upgrade> upgradeesInstalled) {
		this.upgradeesInstalled = upgradeesInstalled;
	}
	
	public void addUpgrade(Upgrade upgrade){
		
	}
	
	public void removeUpgrade(Upgrade upgrade){
		
	}

	@Override
	public void paint(Graphics g, Point position) {
		g.setColor(color);
		g.fillRect(position.x, position.y, 10, 10);
	}

}
