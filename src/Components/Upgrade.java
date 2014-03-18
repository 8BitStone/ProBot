package Components;

public class Upgrade {
	
	private UpgradeType type;
	private String name;
	private int affectForce;
	private int affectSpeed;
	private Ability additionalAbility;
	
	public Upgrade(UpgradeType type, String name, int affectForce,
			int affectSpeed, Ability additionalAbility) {
		this.type = type;
		this.name = name;
		this.affectForce = affectForce;
		this.affectSpeed = affectSpeed;
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

	public int getAffectForce() {
		return affectForce;
	}

	public void setAffectForce(int affectForce) {
		this.affectForce = affectForce;
	}

	public int getAffectSpeed() {
		return affectSpeed;
	}

	public void setAffectSpeed(int affectSpeed) {
		this.affectSpeed = affectSpeed;
	}

	public Ability getAdditionalAbility() {
		return additionalAbility;
	}

	public void setAdditionalAbility(Ability additionalAbility) {
		this.additionalAbility = additionalAbility;
	}
	
	

}
