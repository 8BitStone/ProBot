package Core;

import java.util.ArrayList;

import Components.Upgrade;
import Components.UpgradeType;

public class UpgradeManager {
	
	private Upgrade[] allUpgrades;

	public UpgradeManager() {
		this.allUpgrades = new Upgrade[]{
			/*new Upgrade(UpgradeType.leg, "Leg Mk1", null),
			new Upgrade(UpgradeType.foot, "Foot Mk1", null),
			new Upgrade(UpgradeType.leg, "Leg Mk2", null)*/
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
	
	public Upgrade getUpgradeByname(String name){
		for(Upgrade u : allUpgrades){
			if(u.getName() == name){
				return u;
			}
		}
		return null;
	}

}
