package Components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Player extends Robot {

	public Player(Point position, int baseSpeed, int baseForce, int healt,
			int maxhealth, World currentWorld, String name, Color color,
			int energy) {
		super(position, baseSpeed, baseForce, healt, maxhealth, currentWorld,
				name, color, energy);
		// TODO Auto-generated constructor stub
	}
	
	public void load(){
		this.setPosition
		(
				new Point
				(
						(int)Math.floor(this.getCurrentWorld().getWidth()/2*POSITION_MULTIPLYER), 
						(int)Math.floor(this.getCurrentWorld().getHeight()/2*POSITION_MULTIPLYER-60)
				)
		);
	}
	
	@Override
	public void paint(Graphics g, Point position) {
		g.setColor(this.getColor());
		g.fillRect(position.x, position.y, this.height*POSITION_MULTIPLYER, this.width*POSITION_MULTIPLYER);
	}
	
}
