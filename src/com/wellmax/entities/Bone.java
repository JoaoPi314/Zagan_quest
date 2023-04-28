package com.wellmax.entities;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;
import com.wellmax.world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The Bone projectile is launched by
 * Skeletons and deals 2 hearts damage
 * on player
 * @author joao.gomes
 *
 */
public class Bone extends Projectile{

	//---------------------------- Attributes ----------------------------------//
	/**
	 * Bones sprites
	 */
	private final BufferedImage[] boneSprite;

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
	public Bone(int x, int y, int width, int height, Directions dir, int dx, int dy) {
		super(x, y, width, height, dx, dy);
		
		this.setFaceDir(dir);
		this.setSpeed(2.5);
		this.setTotalTime(60);
		this.setTimeRemain(0);
		this.setDamage(2);
		this.setKnockBackDealt(3);

		// Collision mask
		this.setMask(4, 4, 24, 24);

		this.boneSprite = new BufferedImage[this.getNumberOfSprites()];

		for(int i = 0; i < this.getNumberOfSprites(); i++) {
			this.boneSprite[i] = Game.skeletonSpritesheet.getSprite(224, i*32, this.getWidth(), this.getHeight());
		}
		
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(boneSprite[this.getIndex()], (int) (this.getX() - Camera.x), (int) (this.getY() - Camera.y),
				null);
	}

	
}
