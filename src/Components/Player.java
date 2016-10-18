package Components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import Core.ProBotGame;

public class Player extends Robot {

	public Player(Point position, int baseSpeed, int baseForce, int healt,
			int maxhealth, World currentWorld, String name, Color color,
			int energy) {
		super(position, baseSpeed, baseForce, healt, maxhealth, currentWorld,
				name, color, energy);
	}
	
	public void load(ProBotGame game){
		this.setPosition
		(
				new Point
				(
						(int)Math.floor(this.getCurrentWorld().getWidth()/2*POSITION_MULTIPLYER), 
						(int)Math.floor(this.getCurrentWorld().getHeight()/2*POSITION_MULTIPLYER-60)
				)
		);
		this.setHead(game.getUpgradeManager().getHeadByName("Head 2"));
		this.setBody(game.getUpgradeManager().getBodyByName("Body 1"));
		this.setLimbs(game.getUpgradeManager().getLimbsByName("Limbs 1"));
	}
	
	@Override
	public void paint(Graphics g, Point position) {
		g.setColor(this.getColor());
		g.fillRect(position.x, position.y, this.width*POSITION_MULTIPLYER, this.height*POSITION_MULTIPLYER);
	}
	
	public void die(){
		
	}
	
}
