package Components;

import java.awt.Point;

public abstract class Living extends Object{
	
	private int health;
	private int maxHealth;
	
	public Living(Point position, int baseSpeed, int baseForce, int health,
			int maxHealt, World currentWorld, boolean bouncing) {
		super(position, baseSpeed, baseForce, currentWorld, bouncing);
		this.health = health;
		this.maxHealth = maxHealt;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int healt) {
		if(health <= 0){
			this.die();
		}
		this.health = healt;
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
