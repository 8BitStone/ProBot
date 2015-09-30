package Components;

public abstract class BodyPart {
	
	private Upgrade[] upgrades;
	private UpgradeType type;
	
	public BodyPart(int upgradeCount, UpgradeType type){
		this.upgrades = new Upgrade[upgradeCount];
		this.type = type;
	}

	public Upgrade[] getUpgrades() {
		return upgrades;
	}
	
	public void removeUpgrade(Upgrade upgrade) {
		for(int i = 0; i < this.upgrades.length; i++){
			if(this.upgrades[i] == upgrade){
				this.upgrades[i] = null;
				return;
			}
		}
	}
	
	public boolean addUpgrade(Upgrade upgrade, int position){
		if(upgrade.getType() == this.type){
			this.upgrades[position] = upgrade;
			return true;
		}
		return false;
	}

	public UpgradeType getType() {
		return type;
	}
}
