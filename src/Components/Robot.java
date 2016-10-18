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
		this.head = null;
		this.limbs = null;
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
		if(this.body != null){
			this.body.setUsed(false);
		}
		this.body = body;
		this.body.setUsed(true);
	}
	
	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		if(this.head != null){
			this.head.setUsed(false);
		}
		this.head = head;
		this.head.setUsed(true);
	}
	
	public Limbs getLimbs() {
		return limbs;
	}

	public void setLimbs(Limbs limbs) {
		if(this.limbs != null){
			this.limbs.setUsed(false);
		}
		this.limbs = limbs;
		this.limbs.setUsed(true);
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
