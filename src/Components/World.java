package Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import static Core.ProBotGame.BLOCK_SIZE;
import static Components.Living.POSITION_MULTIPLYER;


public class World {
	
	private String name;
	private int width = 0;
	private int height = 0;
	private double G = 9.81;
	private Block[][] blocks;

	public World(String name) {
		this.name = name;
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
				this.blocks[x][y] = new Block(new Color(worldImg.getRGB(x, y)));
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

}
