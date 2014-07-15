package Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Window.Type;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import static Core.ProBotGame.BLOCK_SIZE;
import static Components.Living.POSITION_MULTIPLYER;


public class World {
	
	private String name;
	private int width = 0;
	private int height = 0;
	private double G = 9.81;
	private Block[][] blocks;
	
	private static Map<Color, Block> blockTypes = new HashMap<Color, Block>();

	public World(String name) {
		this.name = name;
		blockTypes.put(new Color(255, 255, 255), new Blocks.Air());
		blockTypes.put(new Color(94, 75, 9), new Blocks.Dirt());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getG() {
		return G;
	}

	public void generate(){
		
	}
	
	public void delete(){
		
	}
	
	public void load(){
		BufferedImage worldImg = null;
		try {
			URL ulr = getClass().getResource("/"+name+".jpg");
			worldImg = ImageIO.read(new File(ulr.getPath()));
		} catch (IOException e) {}
		
		this.width = worldImg.getWidth();
		this.height = worldImg.getHeight();
		this.blocks = new Block[width][height];
		for(int x = 0; x < this.width; x++){
			for(int y = 0; y < this.height; y++){
				Block b = blockTypes.get(new Color(worldImg.getRGB(x, y)));
				if(null != b){
					this.blocks[x][y] = b.clone();
				}else{
					this.blocks[x][y] = new Blocks.Air();
					System.out.print(new Color(worldImg.getRGB(x, y)).getRed());
					System.out.print(", ");
					System.out.print(new Color(worldImg.getRGB(x, y)).getGreen());
					System.out.print(", ");
					System.out.println(new Color(worldImg.getRGB(x, y)).getBlue());
				}
			}
		}
	}
	
	public void update(){
		
	}
	
	public void paint(Graphics g, Dimension windowDimension, Point playerPoint){
		int worldWindowOffsetX = (int)Math.floor((playerPoint.x/POSITION_MULTIPLYER-windowDimension.width/BLOCK_SIZE/2));
		int worldWindowOffsetY = (int)Math.floor((playerPoint.y/POSITION_MULTIPLYER-windowDimension.height/BLOCK_SIZE/2));
		int x = 1;
		while(x*BLOCK_SIZE <= windowDimension.width){
			int y = 1;
			while(y*BLOCK_SIZE <= windowDimension.height){
				blocks[x+worldWindowOffsetX][y+worldWindowOffsetY].paint(
						g, 
						x*BLOCK_SIZE - BLOCK_SIZE - playerPoint.x%POSITION_MULTIPLYER, 
						y*BLOCK_SIZE - BLOCK_SIZE - playerPoint.y%POSITION_MULTIPLYER
				);
				y++;
			}
			x++;
		}
	}
	
	public Block[][] getBlocks(int x1, int y1, int x2, int y2){
		Block[][] blocks = new Block[x2-x1+1][y2-y1+1];
		for(int x = x1; x <= x2; x++){
			for(int y = y1; y <= y2; y++){
				blocks[x-x1][y-y1] = this.blocks[x/POSITION_MULTIPLYER+1][y/POSITION_MULTIPLYER+1]; //fucking no idea why there must be a +1 but otherwise it takes the wrong block
			}
		}
		return blocks;
	}

}
