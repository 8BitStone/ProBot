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
		/*int xOnG = 1;
		for(int xRead = (int)Math.floor(playerPoint.getX()-windowDimension.width/2); xRead <= (int)Math.floor(playerPoint.getX()+windowDimension.width/2); xRead++){
			int yOnG = 1;
			for(int yRead = (int)Math.floor(playerPoint.getY()-windowDimension.height/2); yRead <= (int)Math.floor(playerPoint.getY()+windowDimension.height/2); yRead++){
				blocks[xRead][yRead].paint(g, xOnG, yOnG);
System.out.println(xRead+"x"+yRead);
System.out.println(xOnG+"x"+yOnG);
				yOnG++;
			}
			xOnG++;
		}*/
		int worldWindowOffsetX = (int)Math.floor((playerPoint.x-windowDimension.width/BLOCK_SIZE/2));
		int worldWindowOffsetY = (int)Math.floor((playerPoint.y-windowDimension.height/BLOCK_SIZE/2));
		int x = 1;
		while(x*BLOCK_SIZE <= windowDimension.width){
			int y = 1;
			while(y*BLOCK_SIZE <= windowDimension.height){
				blocks[x+worldWindowOffsetX][y+worldWindowOffsetY].paint(g, x*BLOCK_SIZE - BLOCK_SIZE +1, y*BLOCK_SIZE - BLOCK_SIZE +1);
				y++;
			}
			x++;
		}
	}

}
