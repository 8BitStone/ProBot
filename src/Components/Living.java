package Components;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Living {
	
	public static final int POSITION_MULTIPLYER = 10;
	
	private Point position; //should be 10x bigger (POSITION_MULTIPLYER) than the world
	private int baseSpeed;
	private int baseForce;
	private int healt;
	private int maxHealth;
	private World currentWorld;
	private double ySpeed;
	private double xSpeed;
	private boolean isFloating;
	private boolean isMovingUp = false;
	private boolean isMovingRight = false;
	private boolean isMovingDown = false;
	private boolean isMovingLeft = false;
	
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
	
	public boolean isMovingUp() {
		return isMovingUp;
	}

	public void setMovingUp(boolean isMovingUp) {
		this.isMovingUp = isMovingUp;
	}

	public boolean isMovingRight() {
		return isMovingRight;
	}

	public void setMovingRight(boolean isMovingRight) {
		this.isMovingRight = isMovingRight;
	}

	public boolean isMovingDown() {
		return isMovingDown;
	}

	public void setMovingDown(boolean isMovingDown) {
		this.isMovingDown = isMovingDown;
	}

	public boolean isMovingLeft() {
		return isMovingLeft;
	}

	public void setMovingLeft(boolean isMovingLeft) {
		this.isMovingLeft = isMovingLeft;
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
	
	public void move(){
		if(isMovingUp && !isMovingDown){
			position.y--;
		}else if(isMovingDown && !isMovingUp){
			position.y++;
		}
		if(isMovingRight && !isMovingLeft){
			position.x++;
		}else if(isMovingLeft && !isMovingRight){
			position.x--;
		}
	}
	
	public void die(){
		
	}
	
	public void paint(Graphics g){
		this.paint(g, position);
	};
	
	public abstract void paint(Graphics g, Point position);

}
