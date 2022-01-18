package com.melqjpgames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.melqjpgames.main.Game;

public class Entity {

	protected double x;
	protected double y;
	protected int width;
	protected int height;

	
	public static BufferedImage HEALTH_POTION_EN;
	public static BufferedImage FIREBALL;
	
	/*
	 * The constructor of Entity receives the position and size of entity
	 */
	public Entity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		HEALTH_POTION_EN = Game.spritesheet.getSprite(64, 0, width, height);
		FIREBALL = Game.spritesheet.getSprite(80, 0, width, height);
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
	
	public void update() {
		
	}
	
	
	public void render(Graphics g) {

	}
	
	
}
