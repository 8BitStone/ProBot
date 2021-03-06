package Core;

import java.util.ArrayList;

import Components.Body;
import Components.BodyPart;
import Components.Head;
import Components.Limbs;
import Components.Upgrade;
import Components.UpgradeType;

public class UpgradeManager {
	
	private Upgrade[] allUpgrades;
	private Head[] allHeads;
	private Body[] allBodys;
	private Limbs[] allLimbs;

	public UpgradeManager() {
		this.allUpgrades = new Upgrade[]{
			new Upgrade(UpgradeType.Body, "Bodyup 1", null),
			new Upgrade(UpgradeType.Head, "Headup 1", null),
			new Upgrade(UpgradeType.Body, "Bodyup 2", null)
		};
		this.allHeads = new Head[]{
			new Head(1, "Head 1"),
			new Head(1, "Head 2")
			
		};
		this.allBodys = new Body[]{
				new Body(1, "Body 1"),
				new Body(2, "Body 2")
			};
		this.allLimbs = new Limbs[]{
				new Limbs(1, "Limbs 1")
			};
	}
	
	public Upgrade[] getAllUpgrades(){
		return this.allUpgrades;
	}
	
	public ArrayList<Upgrade> getUpgradesByType(UpgradeType type){
		ArrayList<Upgrade> matching = new ArrayList<Upgrade>();
		for(Upgrade u : allUpgrades){
			if(u.getType() == type){
				matching.add(u);
			}
		}
		return matching;
	}
	
	public Upgrade getUpgradeByName(String name){
		for(Upgrade u : allUpgrades){
			if(u.getName() == name){
				return u;
			}
		}
		return null;
	}

	public ArrayList<BodyPart> getAllBodyparts() {
		ArrayList<BodyPart> bodyparts = new ArrayList<BodyPart>();
		for(BodyPart u : allHeads){
			bodyparts.add(u);
		}
		for(BodyPart u : allBodys){
			bodyparts.add(u);
		}
		for(BodyPart u : allLimbs){
			bodyparts.add(u);
		}
		return bodyparts;
	}

	public ArrayList<BodyPart> getBodypartsByType(UpgradeType type){
		ArrayList<BodyPart> bodyparts = new ArrayList<BodyPart>();
		switch (type) {
		case Head:
			for(BodyPart u : allHeads){
				bodyparts.add(u);
			}
			return bodyparts;
		case Body:
			for(BodyPart u : allBodys){
				bodyparts.add(u);
			}
			return bodyparts;
		case Limbs:
			for(BodyPart u : allLimbs){
				bodyparts.add(u);
			}
			return bodyparts;
		}
		return new ArrayList<BodyPart>();
	}
	
	public Head getHeadByName(String name){
		for(Head u : allHeads){
			if(u.getName() == name){
				return u;
			}
		}
		return null;	
	}
	
	public Body getBodyByName(String name){
		for(Body u : allBodys){
			if(u.getName() == name){
				return u;
			}
		}
		return null;	
	}
	
	public Limbs getLimbsByName(String name){
		for(Limbs u : allLimbs){
			if(u.getName() == name){
				return u;
			}
		}
		return null;	
	}

}
