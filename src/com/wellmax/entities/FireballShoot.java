package com.wellmax.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;
import com.wellmax.world.Camera;

/**
 * The FireBallShoot is a power that deals
 * 2x the damage compared to LuteFire, dealing a major
 * knock-back when an enemy is hit
 * @author joao.gomes
 *
 */
public class FireballShoot extends Projectile{

	//---------------------------- Attributes ----------------------------------//
	/**
	 * Fireball facing right sprite
	 */
	private final BufferedImage[] fireRight;
	/**
	 * Fireball facing left
	 */
	private final BufferedImage[] fireLeft;
	/**
	 * Fireball facing up sprite
	 */
	private final BufferedImage[] fireUp;
	/**
	 * Fireball facing down sprite
	 */
	private final BufferedImage[] fireDown;

	//---------------------------- Methods ----------------------------------//	
	
	/**
	 * Constructor
	 * @param x x position
	 * @param y y position
	 * @param width Fireball shoot width
	 * @param height Fireball shoot height
	 * @param dir Direction of shoot
	 * @param dx x axis multiplier
	 * @param dy y axis multiplier
	 */
	public FireballShoot(int x, int y, int width, int height, Directions dir, int dx, int dy) {
		super(x, y, width, height, dx, dy);
		
		this.setFaceDir(dir);
		this.setSpeed(4);
		this.setTotalTime(60);
		this.setTimeRemain(0);
		this.setDamage(5);
		this.setKnockBackDealt(3);
		
		this.fireRight = new BufferedImage[this.getNumberOfSprites()];
		this.fireLeft = new BufferedImage[this.getNumberOfSprites()];
		this.fireUp = new BufferedImage[this.getNumberOfSprites()];
		this.fireDown = new BufferedImage[this.getNumberOfSprites()];

		
		for(int i = 0; i < this.getNumberOfSprites(); i++) {
			this.fireRight[i] = Game.spritesheet.getSprite(96 + i*16, 32, this.getMaskWidth(), this.getHeight());
			this.fireLeft[i] = Game.spritesheet.getSprite(96 + i*16, 48, this.getMaskWidth(), this.getHeight());
			this.fireUp[i] = Game.spritesheet.getSprite(96 + i*16, 0, this.getMaskWidth(), this.getHeight());
			this.fireDown[i] = Game.spritesheet.getSprite(96 + i*16, 16, this.getMaskWidth(), this.getHeight());
		}
		
	}
	
	@Override
	public void render(Graphics g) {
		switch (this.getFaceDir()) {
			case UP -> g.drawImage(fireUp[this.getIndex()], (int) (getX() - Camera.x), (int) (getY() - Camera.y),
					null);
			case DOWN -> g.drawImage(fireDown[this.getIndex()], (int) (getX() - Camera.x), (int) (getY() - Camera.y),
					null);
			case RIGHT -> g.drawImage(fireRight[this.getIndex()], (int) (getX() - Camera.x), (int) (getY() - Camera.y),
					null);
			case LEFT -> g.drawImage(fireLeft[this.getIndex()], (int) (getX() - Camera.x), (int) (getY() - Camera.y),
					null);
		}
	}

	
}
