package Components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Robot extends Living{
	
	private String name;
	private Color color;
	private int energy;	
	private Upgrade body;

	public Robot(Point position, int baseSpeed, int baseForce, int healt,
			int maxhealth, World currentWorld, String name, Color color, int energy) {
		super(position, baseSpeed, baseForce, healt, maxhealth, currentWorld, false);
		this.name = name;
		this.color = color;
		this.energy = energy;
		this.body = null;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public Upgrade getBody() {
		return body;
	}

	public void setBody(Upgrade body) {
		this.body = body;
	}

	@Override
	public void paint(Graphics g, Point position) {
		
	}

}
