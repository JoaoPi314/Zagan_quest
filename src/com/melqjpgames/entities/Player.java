package com.melqjpgames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.melqjpgames.main.Game;
import com.melqjpgames.world.Camera;
import com.melqjpgames.world.World;

public class Player extends Entity{

	// Directions
	private boolean right, up, left, down;
	private double speed;
	private int upDir = 0, downDir = 1, rightDir = 2, leftDir = 3;
	private int dir;
	private boolean moved;
	// Sprites
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private int nOfSprites;
	
	// Animation of sprite
	private int frames;
	private final int maxFrames = 10;
	private int index;
	private final int maxIndex = 3;
	
	
	
	/*
	 * Constructor receives the position and size of player
	 */
	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 0.9;
		frames = 0;
		index = 0;
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
		moved = false;
		if(isRight() && World.isFree((int)(getX() + speed), (int) getY())) {
			setX(getX() + speed);
			dir = rightDir;
			moved = true;
		}else if(isLeft() && World.isFree((int)(getX() - speed), (int) getY())) {
			setX(getX() - speed);
			dir = leftDir;
			moved = true;
		}
		if(isUp() && World.isFree((int)getX(), (int) (getY() - speed))) {
			setY(getY() - speed);
			dir = upDir;
			moved = true;
		}else if(isDown() && World.isFree((int)getX(), (int) (getY() + speed))) {
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
		
		// Makes camera follow player
		Camera.x = Camera.clamp((int)getX() - (Game.WIDTH / 2), 0, World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp((int)getY() - (Game.HEIGHT / 2), 0, World.HEIGHT*16 - Game.HEIGHT);
		
	}

	public void render(Graphics g) {
		if(dir == upDir) {
			g.drawImage(upPlayer[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
		}else if(dir == downDir) {
			g.drawImage(downPlayer[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
		}else if(dir == rightDir) {
			g.drawImage(rightPlayer[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
		}else if(dir == leftDir) {
			g.drawImage(leftPlayer[index],  (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
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
