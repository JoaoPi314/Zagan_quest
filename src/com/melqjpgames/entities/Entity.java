package com.melqjpgames.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.melqjpgames.main.Game;

public class Entity {

	protected double x;
	protected double y;
	protected int width;
	protected int height;

	
	public static BufferedImage HEALTH_POTION_EN;
	public static BufferedImage FIREBALL_EN;
	private int maskx, masky, mwidth, mheight;
	
	/*
	 * The constructor of Entity receives the position and size of entity
	 */
	public Entity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.maskx = 0;
		this.masky = 0;
		this.mwidth = width;
		this.mheight = height;
		
		HEALTH_POTION_EN = Game.spritesheet.getSprite(64, 0, width, height);
		FIREBALL_EN = Game.spritesheet.getSprite(80, 0, width, height);
	}
	
	
	// Getter Methods
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	// Setter Methods
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	// Update and render methods
	
	public static boolean isColiding(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle((int)(e1.getX() + e1.maskx), (int)(e1.getY() + e1.masky), e1.mwidth, e1.mheight);
		Rectangle e2Mask = new Rectangle((int)(e2.getX() + e2.maskx), (int)(e2.getY() + e2.masky), e2.mwidth, e2.mheight);
		
		return e1Mask.intersects(e2Mask);
	}
	
	public void setMask(int maskx, int masky, int mwidth, int mheight) {
		this.maskx = maskx;
		this.masky = masky;
		this.mwidth = mwidth;
		this.mheight = mheight;
	}
	
	public void update() {
		
	}
	
	
	public void render(Graphics g) {

	}
	
	
}
