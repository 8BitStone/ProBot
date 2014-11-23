package Components;

import java.awt.Graphics;

public abstract class Upgrade{
	
	private UpgradeType type;
	private String name;
	private Ability additionalAbility;
	
	public Upgrade(UpgradeType type, String name, Ability additionalAbility) {
		this.type = type;
		this.name = name;
		this.additionalAbility = additionalAbility;
	}

	public UpgradeType getType() {
		return type;
	}

	public void setType(UpgradeType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Ability getAdditionalAbility() {
		return additionalAbility;
	}

	public void setAdditionalAbility(Ability additionalAbility) {
		this.additionalAbility = additionalAbility;
	}
	
	public void paintInGame(Graphics g){
		
	}

}
