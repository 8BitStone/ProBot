package Components;

import java.awt.Color;
import java.awt.Graphics;

import static Core.ProBotGame.BLOCK_SIZE;

public abstract class Block implements Cloneable {
	
	public Color color; //just for debugging public
	protected boolean isSolid;
	
	public Block(Color color, boolean isSolid) {
		this.color = color;
		this.isSolid = isSolid;
	}
	
	public void paint(Graphics g, int x, int y){
		g.setColor(this.color);
		g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
	}
	
	public Block clone(){  
	    try{  
	        return (Block) super.clone();
	    }catch(Exception e){ 
	    	e.printStackTrace();
	    	return null;
	    }
	}

}
