package com.wellmax.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;
import com.wellmax.world.Camera;
import com.wellmax.world.World;

public class FireballShoot extends Entity{

	
	private int dx;
	private int dy;
	private double speed;
	private final int upDir = 0, downDir = 1, rightDir = 2, leftDir = 3;
	private int dir;
	
	private BufferedImage[] fireUp;
	private BufferedImage[] fireDown;
	private BufferedImage[] fireRight;
	private BufferedImage[] fireLeft;
	private int nOfSprites = 4;

	// Animation of sprite
	private int frames;
	private final int maxFrames = 10;
	private int index;
	private final int maxIndex = 3;
	
	private int totalTime = 60; 
	private int timeRemain = 0;
	
	
	public FireballShoot(int x, int y, int width, int height, Directions dir, int dx, int dy) {
		super(x, y, width, height);
		
		this.setFaceDir(dir);
		this.dx = dx;
		this.dy = dy;
		fireUp = new BufferedImage[4];
		fireDown = new BufferedImage[4];
		fireRight = new BufferedImage[4];
		fireLeft = new BufferedImage[4];
		
		speed = 4;
		
		for(int i = 0; i < nOfSprites; i++) {
			fireUp[i] = Game.spritesheet.getSprite(96 + i*16, 0, 16, 16);
			fireDown[i] = Game.spritesheet.getSprite(96 + i*16, 16, 16, 16);
			fireRight[i] = Game.spritesheet.getSprite(96 + i*16, 32, 16, 16);
			fireLeft[i] = Game.spritesheet.getSprite(96 + i*16, 48, 16, 16);
		}
		
	}
	
	public void update() {
		if(World.isFree((int)(x + dx*speed),(int)(y + dy*speed))) {
			x += dx*speed;
			y += dy*speed;
		}else {
			Game.fireballs.remove(this);
		}
		
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) {
				index = 0;
			}
		}
		
		timeRemain++;
		if(timeRemain >= totalTime) {
			Game.fireballs.remove(this);
			return;
		}
		
	}
	
	public void render(Graphics g) {
		switch(this.getFaceDir()) {
			case UP:
				g.drawImage(fireUp[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
				break;
			case DOWN:
				g.drawImage(fireDown[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
				break;
			case RIGHT:
				g.drawImage(fireRight[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
				break;
			case LEFT:
				g.drawImage(fireLeft[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
				break;
			default:
				g.drawImage(fireRight[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
				break;
		}
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}
	
	
}
