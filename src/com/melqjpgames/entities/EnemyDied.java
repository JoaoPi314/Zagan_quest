package com.melqjpgames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.melqjpgames.main.Game;
import com.melqjpgames.world.Camera;

public class EnemyDied extends Entity{
	
	private BufferedImage[] dyingUp;
	private BufferedImage[] dyingDown;
	private BufferedImage[] dyingRight;
	private BufferedImage[] dyingLeft;
	private final int nOfSprites = 4;
	
	private int dir;
	private final int upDir = 0, downDir = 1, rightDir = 2, leftDir = 3;

	
	// Animation of sprite
	private int frames;
	private final int maxFrames = 10;
	private int index;
	private final int maxIndex = 3;
	private boolean endAnimation;
	public EnemyDied(int x, int y, int width, int height, int dir) {
		super(x, y, width, height);
		
		this.setDir(dir);
		dyingUp = new BufferedImage[nOfSprites];
		dyingDown = new BufferedImage[nOfSprites];
		dyingRight = new BufferedImage[nOfSprites];
		dyingLeft = new BufferedImage[nOfSprites];
		
		for(int i = 0; i < nOfSprites; i++) {
			dyingUp[i] = Game.spritesheet.getSprite(224 + i*16, 0, getWidth(), getHeight());
			dyingDown[i] = Game.spritesheet.getSprite(224 + i*16, 16, getWidth(), getHeight());
			dyingRight[i] = Game.spritesheet.getSprite(224 + i*16, 32, getWidth(), getHeight());
			dyingLeft[i] = Game.spritesheet.getSprite(224 + i*16, 48, getWidth(), getHeight());
		}
	}
	
	
	public void update() {
		if(!endAnimation) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = maxIndex;
					endAnimation = true;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		if(getDir() == upDir) {
			g.drawImage(dyingUp[index],  (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
		}else if(getDir() == downDir) {
			g.drawImage(dyingDown[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
		}else if(getDir() == rightDir) {
			g.drawImage(dyingRight[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
		}else if(getDir() == leftDir) {
			g.drawImage(dyingLeft[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
		}
	}


	public int getDir() {
		return dir;
	}


	public void setDir(int dir) {
		this.dir = dir;
	}
	
}
