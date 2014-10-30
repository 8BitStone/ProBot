package Components;

import java.awt.Graphics;
import java.awt.Point;

import static Core.ProBotGame.BLOCK_SIZE;

public abstract class Object {

	public static final int POSITION_MULTIPLYER = BLOCK_SIZE;
	public static final int BLOCKS_PER_SECOND = 1; 
	public static final int MOVEMENT_MULTIPLYER = BLOCK_SIZE;
	
	public static final char DIRECTION_UP = 'u';
	public static final char DIRECTION_RIGHT = 'r';
	public static final char DIRECTION_DOWN = 'd';
	public static final char DIRECTION_LEFT = 'l';
	
	private float exactPostitionX;
	private float exactPostitionY;
	private int speed;
	private int force;
	private World currentWorld;
	private float ySpeed; // percentage of max speed
	private float xSpeed; //percent of max speed
	protected int width = 2; // in Blocks
	protected int height = 2; // in Blocks
	private boolean isFloating;
	private boolean isMovingUp = false;
	private boolean isMovingRight = false;
	private boolean isMovingDown = false;
	private boolean isMovingLeft = false;
	private boolean bouncing = false;
	private boolean bounceNextTime = false;
	
	public Object(Point position, int baseSpeed, int baseForce,
			World currentWorld, boolean bouncing) {
		this.exactPostitionX = position.x;
		this.exactPostitionY = position.y;
		this.speed = baseSpeed;
		this.force = baseForce; // 100%
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
	
	public World getCurrentWorld() {
		return currentWorld;
	}
	
	public void setCurrentWorld(World currentWorld) {
		this.currentWorld = currentWorld;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void addSpeed(int speed){
		this.speed += speed;
	}

	public int getForce() {
		return force;
	}
	
	public void addForce(int force){
		this.force += force;
	}
	
	public void move(long deltaTime){
		float distance;

		if(isMovingUp || isFloating || bounceNextTime){
			ySpeed = (float)(
					ySpeed == 0 && isMovingUp && !isFloating
						? force/MOVEMENT_MULTIPLYER
						: bounceNextTime
							? -ySpeed
							: (isMovingUp || ySpeed < 0)
								? ySpeed - (float)this.currentWorld.getG()*deltaTime/1000 
								: ySpeed - (float)this.currentWorld.getG()*deltaTime/1000*2
					);
			this.bounceNextTime = false;
			if(ySpeed > 0){
				distance = ((float)deltaTime/1000)*BLOCKS_PER_SECOND*ySpeed*MOVEMENT_MULTIPLYER;
				exactPostitionY -= checkForColision(distance, DIRECTION_UP);
			}else{
				setMovingDown(true);
				distance = ((float)deltaTime/1000)*BLOCKS_PER_SECOND*ySpeed*MOVEMENT_MULTIPLYER;
				exactPostitionY += checkForColision(distance*(-1), DIRECTION_DOWN);
			}
		}else{
			//reset if not floating
			ySpeed = 0;
		}
		
		if(isMovingRight || isMovingLeft){
			xSpeed += force/MOVEMENT_MULTIPLYER*(float)deltaTime/1000;
			xSpeed = xSpeed >= 1 ? 1 : xSpeed;
			distance = ((float)deltaTime/1000)*BLOCKS_PER_SECOND*speed*xSpeed;
			
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
						position.x+width*POSITION_MULTIPLYER, 
						position.y, 
						position.x+width*POSITION_MULTIPLYER+((int)Math.ceil(distance*POSITION_MULTIPLYER)), 
						position.y+height*POSITION_MULTIPLYER-1 // -1 prevents from taking a block too much
						);
				break;
			case DIRECTION_DOWN:
				blocks = this.getCurrentWorld().getBlocks(
						position.x, 
						position.y+height*POSITION_MULTIPLYER, 
						position.x+width*POSITION_MULTIPLYER-1, 
						position.y+height*POSITION_MULTIPLYER+((int)Math.ceil(distance*POSITION_MULTIPLYER))
						);
				break;
			case DIRECTION_LEFT:
				blocks = this.getCurrentWorld().getBlocks(
						position.x-((int)Math.ceil(distance*POSITION_MULTIPLYER)), 
						position.y, 
						position.x, 
						position.y+height*POSITION_MULTIPLYER-1 // -1 prevents from taking a block too much
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
				position.y+height*POSITION_MULTIPLYER, 
				position.x+width*POSITION_MULTIPLYER-1, 
				position.y+height*POSITION_MULTIPLYER
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
