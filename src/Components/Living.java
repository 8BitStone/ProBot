package Components;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Living extends Object{
	
	private int healt;
	private int maxHealth;
	
	public Living(Point position, int baseSpeed, int baseForce, int healt,
			int maxHealt, World currentWorld, boolean bouncing) {
		super(position, baseSpeed, baseForce, currentWorld, bouncing);
		this.healt = healt;
		this.maxHealth = maxHealt;
	}

	public int getHealt() {
		return healt;
	}

	public void setHealt(int healt) {
		this.healt = healt;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public void die(){
		
	}

}
