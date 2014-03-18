package Components;

public abstract class Ability {
	
	private String name;
	private String trigger;
	
	public Ability(String name, String trigger) {
		this.name = name;
		this.trigger = trigger;
	}
	
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getTrigger() {
		return trigger;
	}



	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}



	abstract public void use();
	
}
