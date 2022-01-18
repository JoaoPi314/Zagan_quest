package com.melqjpgames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.melqjpgames.main.Game;

public class Enemy extends Entity{

	// Directions
	private boolean right, up, left, down;
	private double speed;
	private int upDir = 0, downDir = 1, rightDir = 2, leftDir = 3;
	private int dir;
	private boolean moved;
	// Sprites
	private BufferedImage[] upEnemy;
	private BufferedImage[] downEnemy;
	private BufferedImage[] rightEnemy;
	private BufferedImage[] leftEnemy;
	private int nOfSprites;
	
	// Animation of sprite
	private int frames;
	private final int maxFrames = 10;
	private int index;
	private final int maxIndex = 3;
	
	
	
	/*
	 * Constructor receives the position and size of player
	 */
	public Enemy(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 0.9;
		frames = 0;
		index = 0;
		nOfSprites = 4;
		dir = downDir;
		
		// Initiates sprites
		upEnemy = new BufferedImage[nOfSprites];
		downEnemy = new BufferedImage[nOfSprites];
		rightEnemy = new BufferedImage[nOfSprites];
		leftEnemy = new BufferedImage[nOfSprites];
		
		
		for(int i = 0; i < nOfSprites; i++) {
			upEnemy[i] = Game.spritesheet.getSprite(i*16, 80, getWidth(), getHeight());
			downEnemy[i] = Game.spritesheet.getSprite(i*16, 96, getWidth(), getHeight());
			rightEnemy[i] = Game.spritesheet.getSprite(i*16, 112, getWidth(), getHeight());
			leftEnemy[i] = Game.spritesheet.getSprite(i*16, 128, getWidth(), getHeight());
		}
		
	}
	
	// Update and render methods
	
	public void update() {
		moved = false;
		if(isRight()) {
			setX(getX() + speed);
			dir = rightDir;
			moved = true;
		}else if(isLeft()) {
			setX(getX() - speed);
			dir = leftDir;
			moved = true;
		}
		if(isUp()) {
			setY(getY() - speed);
			dir = upDir;
			moved = true;
		}else if(isDown()) {
			setY(getY() + speed);
			dir = downDir;
			moved = true;
		}
		// By now, always moving
		
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		if(dir == upDir) {
			g.drawImage(upEnemy[index], (int)getX(), (int)getY(), null);
		}else if(dir == downDir) {
			g.drawImage(downEnemy[index], (int)getX(), (int)getY(), null);
		}else if(dir == rightDir) {
			g.drawImage(rightEnemy[index], (int)getX(), (int)getY(), null);
		}else if(dir == leftDir) {
			g.drawImage(leftEnemy[index], (int)getX(), (int)getY(), null);
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
