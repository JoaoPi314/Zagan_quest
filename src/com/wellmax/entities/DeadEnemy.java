package com.wellmax.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;
import com.wellmax.main.Sound;
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

		enWalkingRight = new BufferedImage[this.getNumberOfSprites()];
		enWalkingLeft = new BufferedImage[this.getNumberOfSprites()];
		enWalkingUp = new BufferedImage[this.getNumberOfSprites()];
		enWalkingDown = new BufferedImage[this.getNumberOfSprites()];

		for(int i = 0; i < this.getNumberOfSprites(); i++) {
			enWalkingRight[i] = Game.skeletonSpritesheet.getSprite(i*this.getWidth(), 224, this.getWidth(), this.getHeight());
			enWalkingLeft[i] = Game.skeletonSpritesheet.getSprite(i*this.getWidth(), 224, this.getWidth(), this.getHeight());
			enWalkingUp[i] = Game.skeletonSpritesheet.getSprite(i*this.getWidth(), 224, this.getWidth(), this.getHeight());
			enWalkingDown[i] = Game.skeletonSpritesheet.getSprite(i*this.getWidth(), 224, this.getWidth(), this.getHeight());
		}
		Sound.enemyDead.play();
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
			case RIGHT -> this.enWalkingRight[this.getIndex()];
			case LEFT -> this.enWalkingLeft[this.getIndex()];
			case UP -> this.enWalkingUp[this.getIndex()];
			case DOWN -> this.enWalkingDown[this.getIndex()];
		};

		g.drawImage(currentSprite, (int)(this.getX() - Camera.x), (int)(this.getY() - Camera.y), null);

	}


}
