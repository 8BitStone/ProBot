package Components;

import java.awt.Color;
import java.awt.Graphics;

import static Core.ProBotGame.BLOCK_SIZE;

public class Block {
	
	private Color color;
	
	public Block(Color color) {
		this.color = color;
	}
	
	public void paint(Graphics g, int x, int y){
		g.setColor(this.color);
		g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
	}

}
