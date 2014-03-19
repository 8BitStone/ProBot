package Blocks;

import java.awt.Color;
import java.awt.Graphics;
import static Core.ProBotGame.BLOCK_SIZE;
import Components.Block;

public class Air extends Block {

	public Air() {
		super(Color.white, false);
	}
	
	public void paint(Graphics g, int x, int y){
		g.clearRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
	}

}
