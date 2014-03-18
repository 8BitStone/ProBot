package Components;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Living {
	
	private Point position;
	private int baseSpeed;
	private int baseForce;
	private int healt;
	private int maxHealth;
	private World currentWorld;
	private double ySpeed;
	private double xSpeed;
	private boolean isFloating;
	
	public Living(Point position, int baseSpeed, int baseForce, int healt,
			int maxHealt, World currentWorld) {
		this.position = position;
		this.baseSpeed = baseSpeed;
		this.baseForce = baseForce;
		this.healt = healt;
		this.maxHealth = maxHealt;
		this.currentWorld = currentWorld;
		this.ySpeed = 0;
		this.xSpeed = 0;
		this.isFloating = false;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public int getHealt() {
		return healt;
	}

	public void setHealt(int healt) {
		this.healt = healt;
	}

	public World getCurrentWorld() {
		return currentWorld;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setCurrentWorld(World currentWorld) {
		this.currentWorld = currentWorld;
	}

	public int getBaseSpeed() {
		return baseSpeed;
	}

	public int getBaseForce() {
		return baseForce;
	}
	
	public void move(char direction){
		switch(direction){
			case 'w':
				this.position.y--;
				break;
			case 's':
				this.position.y++;
				break;
			case 'd':
				this.position.x++;
				break;
			case 'a':
				this.position.x--;
				break;
		}
	}
	
	public void die(){
		
	}
	
	public void paint(Graphics g){
		this.paint(g, position);
	};
	
	public abstract void paint(Graphics g, Point position);

}
