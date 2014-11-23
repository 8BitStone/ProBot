package Core;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Gui {
	
	private ProBotGame game;

	public Gui(ProBotGame game) {
		this.game = game;
	}
	
	public void updateInGame(Graphics g){
		this.drawHealthBar(g);
	}
	
	private void drawHealthBar(Graphics g){
		g.setColor(new Color(9, 21, 178));
		g.fillPolygon(
				new int[]{50, 300, 320, 70}, 
				new int[]{game.windowSize.height-100, game.windowSize.height-100, game.windowSize.height-70, game.windowSize.height-70}, 
				4
		);
		g.setColor(new Color(145, 44, 0));
		g.fillPolygon(
				new int[]{60, (int) (Math.round((double)235/game.player.getMaxHealth()*game.player.getHealth())+60), (int) (Math.round((double)235/game.player.getMaxHealth()*game.player.getHealth())+75), 75}, //235 / maxHelt * Health
				new int[]{game.windowSize.height-95, game.windowSize.height-95, game.windowSize.height-75, game.windowSize.height-75}, 
				4
		);
	}

}
