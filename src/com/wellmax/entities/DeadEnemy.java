package com.wellmax.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;
import com.wellmax.world.Camera;

public class DeadEnemy extends Entity{
	
	
	/**
	 * Flag that indicates if death animation ends
	 */
	private boolean endAnimation;
	
	/**
	 * Constructor of dead enemy
	 * @param x x position
	 * @param y y position
	 * @param width Enemy width
	 * @param height Enemy height
	 * @param dir Direction enemy died
	 */
	public DeadEnemy(int x, int y, int width, int height, Directions dir) {
		super(x, y, width, height);
		
		this.setFaceDir(dir);
		
		enRight = new BufferedImage[this.getnOfSprites()];
		enLeft = new BufferedImage[this.getnOfSprites()];
		enUp = new BufferedImage[this.getnOfSprites()];
		enDown = new BufferedImage[this.getnOfSprites()];

		
		for(int i = 0; i < nOfSprites; i++) {
			enRight[i] = Game.spritesheet.getSprite(224 + i*16, 32, this.getWidth(), this.getHeight());
			enLeft[i] = Game.spritesheet.getSprite(224 + i*16, 48, this.getWidth(), this.getHeight());
			enUp[i] = Game.spritesheet.getSprite(224 + i*16, 0, this.getWidth(), this.getHeight());
			enDown[i] = Game.spritesheet.getSprite(224 + i*16, 16, this.getWidth(), this.getHeight());
		}
	}
	
	/**
	 * @return the endAnimation
	 */
	public boolean isEndAnimation() {
		return endAnimation;
	}

	/**
	 * @param endAnimation the endAnimation to set
	 */
	public void setEndAnimation(boolean endAnimation) {
		this.endAnimation = endAnimation;
	}
	
	
	/**
	 * The countframes of deadEnemy is different, once the animation
	 * has to stop when finishes the sprites
	 */
	public void countFrames(boolean count) {
		if(count) {
			this.frames++;
			if(this.frames == this.maxFrames) {
				this.frames = 0;
				this.index++;
				if(this.index >= this.getnOfSprites()) {
					this.index = this.getnOfSprites() - 1;
					setEndAnimation(true);
				}
			}
		}
	}
	
	
	public void update() {
		this.countFrames(true);
	}
	
	public void render(Graphics g) {
		
		BufferedImage currentSprite;
		
		switch(this.getFaceDir()) {
			case RIGHT:
				currentSprite = this.enRight[index];
				break;
			case LEFT:
				currentSprite = this.enLeft[index];
				break;
			case UP:
				currentSprite = this.enUp[index];
				break;
			case DOWN: // If DOWN, sprite down is rendered	
			default:
				currentSprite = this.enDown[index];
				break;
		}
		
		g.drawImage(currentSprite, (int)(this.getX() - Camera.x), (int)(this.getY() - Camera.y), null);

	}


}
