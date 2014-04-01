package Components;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Living {
	
	public static final int POSITION_MULTIPLYER = 10;
	public static final int BLOCKS_PER_SECOND = 15;
	
	private Point position; //should be 10x bigger (POSITION_MULTIPLYER) than the world
	private float exactPostitionX;
	private float exactPostitionY;
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
		this.exactPostitionX = position.x;
		this.exactPostitionY = position.y;
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
		this.exactPostitionX = position.x;
		this.exactPostitionY = position.y;
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
	
	public void move(long deltaTime){
		float distance = (float)BLOCKS_PER_SECOND*POSITION_MULTIPLYER/1000*deltaTime;
		if(isMovingUp && !isMovingDown){
			exactPostitionY-=distance;
		}else if(isMovingDown && !isMovingUp){
			exactPostitionY+=distance;
		}
		if(isMovingRight && !isMovingLeft){
			exactPostitionX+=distance;
		}else if(isMovingLeft && !isMovingRight){
			exactPostitionX-=distance;
		}
		position.x = Math.round(exactPostitionX);
		position.y = Math.round(exactPostitionY);
	}
	
	public void die(){
		
	}
	
	public void paint(Graphics g){
		this.paint(g, position);
	};
	
	public abstract void paint(Graphics g, Point position);

}
