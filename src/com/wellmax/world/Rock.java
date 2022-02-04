package com.wellmax.world;

import java.awt.image.BufferedImage;

/**
 * Rock class
 * @author joao.gomes
 *
 */
public class Rock extends Scenario{
	/**
	 * The rock constructor initializes the collision mask values
	 * @param x x position
	 * @param y y position
	 * @param sprite Rock sprite
	 * @param width Rock width
	 * @param height Rock height
	 */
	public Rock(int x, int y, BufferedImage sprite, int width, int height) {
		super(x, y, sprite, width, height);
		
		// Collision mask
		this.setMaskX(9);
		this.setMaskY(0);
		this.setmWidth(16);
		this.setmHeight(7);
	}
}
