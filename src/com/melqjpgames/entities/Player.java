package com.melqjpgames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Entity{

	
	private boolean right, up, left, down;
	private double speed;
	
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private int frames;
	
	
	
	/*
	 * Constructor receives the position and size of player
	 */
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		speed = 0.9;
		frames = 0;
		
		// Initiates sprites
		
	}
	
	// Update and render methods
	
	public void update() {
		if(isRight()) {
			setX(getX() + speed);
		}else if(isLeft()) {
			setX(getX() - speed);
		}
		if(isUp()) {
			setY(getY() - speed);
		}else if(isDown()) {
			setY(getY() + speed);
		}
	}
	
	public void render(Graphics g) {
		if(isUp()) {
			
		}else if(isDown()) {
			
		}else if(isLeft()) {
			
		}else if(isRight()) {
			
		}
	}
	

	// Getter and Setters methods
	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

}
