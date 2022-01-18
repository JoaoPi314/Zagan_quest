package com.melqjpgames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {

	private int x;
	private int y;
	private int width;
	private int height;
	
	private BufferedImage sprite;
	
	/*
	 * The constructor of Entity receives the position and size of entity
	 */
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}
	
	
	// Getter Methods
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	// Setter Methods
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
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
		g.drawImage(sprite, x, y, null);
	}
	
	
}
