package Components;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Living {
	
	public static final int POSITION_MULTIPLYER = 10;
	public static final int BLOCKS_PER_SECOND = 1;
	
	public static final char DIRECTION_UP = 'u';
	public static final char DIRECTION_RIGHT = 'r';
	public static final char DIRECTION_DOWN = 'd';
	public static final char DIRECTION_LEFT = 'l';
	
	//private Point position; //should be 10x bigger (POSITION_MULTIPLYER) than the world
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
		//this.position = position;
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
		this.isMovingDown = isMovingUp ? !isMovingUp : this.isMovingDown;
	}

	public boolean isMovingRight() {
		return isMovingRight;
	}

	public void setMovingRight(boolean isMovingRight) {
		this.isMovingRight = isMovingRight;
		this.isMovingLeft = isMovingRight ? !isMovingRight : this.isMovingLeft;
	}

	public boolean isMovingDown() {
		return isMovingDown;
	}

	public void setMovingDown(boolean isMovingDown) {
		this.isMovingDown = isMovingDown;
		this.isMovingUp = isMovingDown ? !isMovingDown : this.isMovingUp;
	}

	public boolean isMovingLeft() {
		return isMovingLeft;
	}

	public void setMovingLeft(boolean isMovingLeft) {
		this.isMovingLeft = isMovingLeft;
		this.isMovingRight = isMovingLeft ? !isMovingLeft : this.isMovingRight;
	}

	public Point getPosition() {
		return new Point(
				Math.round(exactPostitionX),
				Math.round(exactPostitionY)
				);
	}

	public void setPosition(Point position) {
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
		float distance = (float)BLOCKS_PER_SECOND/deltaTime*POSITION_MULTIPLYER;
		if(isMovingUp && !isMovingDown){
			exactPostitionY -= checkForColision(distance, DIRECTION_UP);
		}else if(isMovingDown && !isMovingUp){
			exactPostitionY += checkForColision(distance, DIRECTION_DOWN);
		}
		
		if(isMovingRight && !isMovingLeft){
			exactPostitionX += checkForColision(distance, DIRECTION_RIGHT);
		}else if(isMovingLeft && !isMovingRight){
			exactPostitionX -= checkForColision(distance, DIRECTION_LEFT);
		}
		//position.x = Math.round(exactPostitionX);
		//position.y = Math.round(exactPostitionY);
	}
	
	private float checkForColision(float distance, char direction){
		Point position = this.getPosition();
		float maxDistanceToColision = 0;
		Block[][] blocks = null;
		switch (direction) {
			case DIRECTION_UP:
				blocks = this.getCurrentWorld().getBlocks(
						position.x, 
						position.y-((int)Math.ceil(distance)), 
						position.x, 
						position.y
						);
				break;
			case DIRECTION_RIGHT:
				blocks = this.getCurrentWorld().getBlocks(
						position.x, 
						position.y, 
						position.x+((int)Math.ceil(distance)), 
						position.y
						);
				break;
			case DIRECTION_DOWN:
				blocks = this.getCurrentWorld().getBlocks(
						position.x, 
						position.y, 
						position.x, 
						position.y+((int)Math.ceil(distance))
						);
				break;
			case DIRECTION_LEFT:
				blocks = this.getCurrentWorld().getBlocks(
						position.x-((int)Math.ceil(distance)), 
						position.y, 
						position.x, 
						position.y
						);
				break;
		}
		
		for(Block[] r : blocks){
			for(Block b : r){
				if(b.isSolid){
					System.out.println("solid");
					return maxDistanceToColision;
				}
				maxDistanceToColision += 1;
			}			
		}
		return distance;
	}
	
	public void die(){
		
	}
	
	public void paint(Graphics g){
		this.paint(g,
				new Point(
						Math.round(exactPostitionX),
						Math.round(exactPostitionY)
						)
		);
	};
	
	public abstract void paint(Graphics g, Point position);

}
