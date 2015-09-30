package Components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Robot extends Living{
	
	private String name;
	private Color color;
	private int energy;	
	private int legForce;
	private int armForce;
	private Body body;
	private Head head;
	private Limbs limbs;

	public Robot(Point position, int baseSpeed, int baseForce, int healt,
			int maxhealth, World currentWorld, String name, Color color, int energy) {
		super(position, baseSpeed, baseForce, healt, maxhealth, currentWorld, false);
		this.legForce = baseForce;
		this.armForce = baseForce;
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

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}
	
	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}
	
	public Limbs getLimbs() {
		return limbs;
	}

	public void setLimbs(Limbs limbs) {
		this.limbs = limbs;
	}

	public int getLegForce() {
		return legForce;
	}

	public void addLegForce(int legForce) {
		this.legForce += legForce;
	}

	public int getArmForce() {
		return armForce;
	}

	public void addArmForce(int armForce) {
		this.armForce += armForce;
	}

	@Override
	public void paint(Graphics g, Point position) {
		
	}

}
