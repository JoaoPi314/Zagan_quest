package com.wellmax.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;
import com.wellmax.world.Camera;

public class DeadEnemy extends Creature {
	//---------------------------- Attributes ----------------------------------//

	/**
	 * Flag that indicates if death animation ends
	 */
	private boolean endAnimation;

	//---------------------------- Methods ----------------------------------//
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

		enRight = new BufferedImage[this.getNumberOfSprites()];
		enLeft = new BufferedImage[this.getNumberOfSprites()];
		enUp = new BufferedImage[this.getNumberOfSprites()];
		enDown = new BufferedImage[this.getNumberOfSprites()];

		for(int i = 0; i < this.getNumberOfSprites(); i++) {
			enRight[i] = Game.spritesheet.getSprite(224 + i*16, 32, this.getWidth(), this.getHeight());
			enLeft[i] = Game.spritesheet.getSprite(224 + i*16, 48, this.getWidth(), this.getHeight());
			enUp[i] = Game.spritesheet.getSprite(224 + i*16, 0, this.getWidth(), this.getHeight());
			enDown[i] = Game.spritesheet.getSprite(224 + i*16, 16, this.getWidth(), this.getHeight());
		}
	}
	

	public boolean isEndAnimation() {
		return endAnimation;
	}

	public void setEndAnimation(boolean endAnimation) {
		this.endAnimation = endAnimation;
	}
	
	
	/**
	 * The counting frames of deadEnemy is different, once the animation
	 * has to stop when finishes the sprites
	 */
	public void countFrames(boolean count) {
		if(count) {
			this.setFrames(this.getFrames() + 1);
			if(this.getFrames() == this.getMaxFrames()) {
				this.setFrames(0);
				this.setIndex(this.getIndex() + 1);
				if(this.getIndex() >= this.getNumberOfSprites()) {
					this.setIndex(this.getNumberOfSprites() - 1);
					this.setEndAnimation(true);
				}
			}
		}
	}
	
	@Override
	public void update() {
		this.countFrames(true);
	}

	@Override
	public void attack() {

	}

	public void render(Graphics g) {
		
		BufferedImage currentSprite = switch (this.getFaceDir()) {
			case RIGHT -> this.enRight[this.getIndex()];
			case LEFT -> this.enLeft[this.getIndex()];
			case UP -> this.enUp[this.getIndex()];
			case DOWN -> this.enDown[this.getIndex()];
		};

		g.drawImage(currentSprite, (int)(this.getX() - Camera.x), (int)(this.getY() - Camera.y), null);

	}


}
