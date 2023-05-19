package com.wellmax.entities;

import java.awt.image.BufferedImage;

import com.wellmax.main.Game;

/**
 * Parent class of all collectibles
 * @author joao.gomes
 *
 */
public abstract class Collectible extends Entity {
	//---------------------------- Attributes ----------------------------------//
	/**
	 * Static sprites of Health potion
	 */
	public static BufferedImage []HEALTH_POTION;
	/**
	 * Static sprites of fireballs
	 */
	public static BufferedImage []FIREBALL;

	static {
		HEALTH_POTION = new BufferedImage[4];
		FIREBALL = new BufferedImage[4];
		int width = 32;
		int height = 32;
		
		for(int i = 0; i < FIREBALL.length; i++) {
			FIREBALL[i] = Game.spritesheet.getSprite(576 + i*width, 320, width, height);
			HEALTH_POTION[i] = Game.spritesheet.getSprite(576 + i*width, 288, width, height);
		}
		
	}

	//---------------------------- Methods ----------------------------------//
	/**
	 * Constructor
	 * @param x x position
	 * @param y y position
	 * @param width collectible width
	 * @param height collectible height
	 */
	public Collectible(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	/**
	 * Abstract Method with collectible effect
	 */
	protected abstract void effect();
}
