package com.melqjpgames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.melqjpgames.main.Game;

public class Player extends Entity{

	
	private boolean right, up, left, down;
	private double speed;
	private int upDir = 0, downDir = 1, rightDir = 2, leftDir = 3;
	private int dir;
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private int frames;
	private int nOfSprites;
	
	
	
	/*
	 * Constructor receives the position and size of player
	 */
	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 0.9;
		frames = 0;
		nOfSprites = 4;
		dir = downDir;
		// Initiates sprites
		upPlayer = new BufferedImage[nOfSprites];
		downPlayer = new BufferedImage[nOfSprites];
		rightPlayer = new BufferedImage[nOfSprites];
		leftPlayer = new BufferedImage[nOfSprites];
		
		
		for(int i = 0; i < nOfSprites; i++) {
			upPlayer[i] = Game.spritesheet.getSprite(i*16, 16, getWidth(), getHeight());
			downPlayer[i] = Game.spritesheet.getSprite(i*16, 32, getWidth(), getHeight());
			rightPlayer[i] = Game.spritesheet.getSprite(i*16, 48, getWidth(), getHeight());
			leftPlayer[i] = Game.spritesheet.getSprite(i*16, 64, getWidth(), getHeight());
		}
		
	}
	
	// Update and render methods
	
	public void update() {
		if(isRight()) {
			setX(getX() + speed);
			dir = rightDir;
		}else if(isLeft()) {
			setX(getX() - speed);
			dir = leftDir;
		}
		if(isUp()) {
			setY(getY() - speed);
			dir = upDir;
		}else if(isDown()) {
			setY(getY() + speed);
			dir = downDir;
		}
	}
	
	public void render(Graphics g) {
		if(dir == upDir) {
			g.drawImage(upPlayer[0], (int)getX(), (int)getY(), null);
		}else if(dir == downDir) {
			g.drawImage(downPlayer[0], (int)getX(), (int)getY(), null);
		}else if(dir == rightDir) {
			g.drawImage(rightPlayer[0], (int)getX(), (int)getY(), null);
		}else if(dir == leftDir) {
			g.drawImage(leftPlayer[0], (int)getX(), (int)getY(), null);
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
