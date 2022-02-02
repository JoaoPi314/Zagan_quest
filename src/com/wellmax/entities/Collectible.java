package com.wellmax.entities;

import java.awt.image.BufferedImage;

import com.wellmax.main.Game;

/**
 * Parent class of all collectibles
 * @author joao.gomes
 *
 */
public class Collectible extends Entity{
	
	
	//---------------------------- Attributes ----------------------------------//	
	
	/**
	 * Static sprites of Health potion
	 */
	public static BufferedImage []HEALTH_POTION;
	/**
	 * Static sprites of fireballs
	 */
	public static BufferedImage []FIREBALL;
	
	/**
	 * Initialization of static sprites
	 */
	static {
		HEALTH_POTION = new BufferedImage[4];
		FIREBALL = new BufferedImage[4];
		
		for(int i = 0; i < FIREBALL.length; i++) {
			HEALTH_POTION[i] = Game.spritesheet.getSprite(96 + i*16, 112, 16, 16);
			FIREBALL[i] = Game.spritesheet.getSprite(96 + i*16, 128, 16, 16);
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
	 * Method with collectible effect
	 */
	protected void effect() {
		
	}
	
}
