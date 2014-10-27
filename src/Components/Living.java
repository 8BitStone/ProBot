package Components;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Living {
	
	public static final int POSITION_MULTIPLYER = 10;
	public static final int BLOCKS_PER_SECOND = 1; 
	public static final int MOVEMENT_MULTIPLYER = 10;
	
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
	private float ySpeed; // percentage of max speed
	private float xSpeed; //percent of max speed
	protected int charWidth = 2; // in Blocks
	protected int charHeight = 2; // in Blocks
	private boolean isFloating;
	private boolean isMovingUp = false;
	private boolean isMovingRight = false;
	private boolean isMovingDown = false;
	private boolean isMovingLeft = false;
	private boolean bouncing = false;
	private boolean bounceNextTime = false;
	
	public Living(Point position, int baseSpeed, int baseForce, int healt,
			int maxHealt, World currentWorld, boolean bouncing) {
		//this.position = position;
		this.exactPostitionX = position.x;
		this.exactPostitionY = position.y;
		this.baseSpeed = baseSpeed;
		this.baseForce = baseForce; // 100%
		this.healt = healt;
		this.maxHealth = maxHealt;
		this.currentWorld = currentWorld;
		this.ySpeed = 0;
		this.xSpeed = 0;
		this.isFloating = false;
		this.bouncing = bouncing;
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
		this.xSpeed = !isMovingRight ? 0 : this.xSpeed;
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
		this.xSpeed = !isMovingLeft ? 0 : this.xSpeed;
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
		float distance;
		System.out.println(bounceNextTime);
		if(isMovingUp || isMovingDown || isFloating || bounceNextTime){
			ySpeed = (float)(
					ySpeed == 0 && isMovingUp && !isFloating
						? baseForce/MOVEMENT_MULTIPLYER 
						: bounceNextTime
							? -ySpeed
							: (isMovingUp || ySpeed < 0)
								? ySpeed - (float)this.currentWorld.getG()*(deltaTime)/1000 
								: ySpeed - (float)this.currentWorld.getG()*(deltaTime)/1000*2
					);
			this.bounceNextTime = false;
			if(ySpeed > 0){
				distance = ((float)deltaTime/1000)*BLOCKS_PER_SECOND*ySpeed*(float)this.currentWorld.getG();
				exactPostitionY -= checkForColision(distance, DIRECTION_UP);
			}else{
				setMovingDown(true);
				distance = ((float)deltaTime/1000)*BLOCKS_PER_SECOND*ySpeed*(float)this.currentWorld.getG();
				exactPostitionY += checkForColision(distance*(-1), DIRECTION_DOWN);
			}
		}else{
			//reset if not floating
			ySpeed = 0;
		}
		
		if(isMovingRight || isMovingLeft){
			xSpeed += baseForce/MOVEMENT_MULTIPLYER*(float)deltaTime/1000;
			xSpeed = xSpeed >= 1 ? 1 : xSpeed;
			distance = ((float)deltaTime/1000)*BLOCKS_PER_SECOND*baseSpeed*xSpeed;
			
			if(isMovingRight && !isMovingLeft){
				exactPostitionX += checkForColision(distance, DIRECTION_RIGHT);
			}else if(isMovingLeft && !isMovingRight){
				exactPostitionX -= checkForColision(distance, DIRECTION_LEFT);
			}
		}
		this.checkForFloating();
	}
	
	private float checkForColision(float distance, char direction){
		Point position = this.getPosition();
		Block[][] blocks = null;
		switch (direction) {
			case DIRECTION_UP:
				blocks = this.getCurrentWorld().getBlocks(
						position.x, 
						position.y-((int)Math.ceil(distance*POSITION_MULTIPLYER)), 
						position.x, 
						position.y
						);
				break;
			case DIRECTION_RIGHT:
				blocks = this.getCurrentWorld().getBlocks(
						position.x+charWidth*POSITION_MULTIPLYER, 
						position.y, 
						position.x+charWidth*POSITION_MULTIPLYER+((int)Math.ceil(distance*POSITION_MULTIPLYER)), 
						position.y+charHeight*POSITION_MULTIPLYER-1 // -1 prevents from taking a block too much
						);
				break;
			case DIRECTION_DOWN:
				blocks = this.getCurrentWorld().getBlocks(
						position.x, 
						position.y+charHeight*POSITION_MULTIPLYER, 
						position.x+charWidth*POSITION_MULTIPLYER-1, 
						position.y+charHeight*POSITION_MULTIPLYER+((int)Math.ceil(distance*POSITION_MULTIPLYER))
						);
				break;
			case DIRECTION_LEFT:
				blocks = this.getCurrentWorld().getBlocks(
						position.x-((int)Math.ceil(distance*POSITION_MULTIPLYER)), 
						position.y, 
						position.x, 
						position.y+charHeight*POSITION_MULTIPLYER-1 // -1 prevents from taking a block too much
						);
				break;
		}
		
		float tempMaxDistance = 0;
		float maxDistanceToColision = 0;
		
		switch (direction) {
			case DIRECTION_UP:
				for(int x = 0; x < blocks.length ; x++){
					for(int y = blocks[0].length-1; y >= 0; y--){
						if(blocks[x][y].isSolid){
							if(ySpeed > 0){
								ySpeed = 0;
								this.setMovingDown(true);
							}
							return maxDistanceToColision;
						}
						maxDistanceToColision += 1;
					}
					maxDistanceToColision = 0;
				}
				break;
			case DIRECTION_LEFT:
				for(int x = blocks.length-1; x >= 0; x--){
					for(int y = 0; y < blocks[x].length ; y++){
						if(blocks[x][y].isSolid){
							return maxDistanceToColision-1;
						}
					}
					maxDistanceToColision += 1;
				}
				break;
			case DIRECTION_RIGHT:
				for(Block[] r : blocks){
					for(Block b : r){
						if(b.isSolid){
							return maxDistanceToColision;
						}
					}	
					maxDistanceToColision += 1;
				}
				break;
			case DIRECTION_DOWN:
				for(Block[] r : blocks){
					for(Block b : r){
						if(b.isSolid){
							if(ySpeed != 0 && this.bouncing){
								this.bounceNextTime = true;
							}else{
								ySpeed = 0;
							}
							this.setMovingDown(false);
							//this is so awesome because if the chat is bigger than 1 it has to check every row bevore it can set the maxDistance
							if(maxDistanceToColision < tempMaxDistance || tempMaxDistance == 0){
								tempMaxDistance = maxDistanceToColision;
							}
						}
						maxDistanceToColision += 1;
					}
					maxDistanceToColision = 0;
				}
		}
		return tempMaxDistance != 0 ? tempMaxDistance : distance*POSITION_MULTIPLYER;
	}
	
	private void checkForFloating(){
		Point position = this.getPosition();
		Block[][] blocks = this.currentWorld.getBlocks(
				position.x, 
				position.y+charHeight*POSITION_MULTIPLYER, 
				position.x+charWidth*POSITION_MULTIPLYER-1, 
				position.y+charHeight*POSITION_MULTIPLYER
				);
		for(Block[] r : blocks){
			for(Block b : r){
				if(b.isSolid){
					this.isFloating = false;
					return;
				}
			}
		}
		this.isFloating = true;
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
