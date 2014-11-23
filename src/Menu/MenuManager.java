package Menu;

import Core.ProBotGame;


public class MenuManager{
	
	private Menu upgradeMenu;
	private Menu mainMenu;
	private Menu pauseMenu;

	public MenuManager(ProBotGame game) {
		this.upgradeMenu = new UpgradeMenu(game);
		this.mainMenu = new MainMenu(game);
		this.pauseMenu = new PauseMenu(game);
		
	}
	
	public void showPauseMenu(){
		pauseMenu.show();
	}
	
	public void showMainMenu(){
		mainMenu.show();
	}
	
	public void showUpgradeMenu(){
		upgradeMenu.show();
	}
}
