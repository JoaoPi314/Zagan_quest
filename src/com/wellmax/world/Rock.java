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
	 */
	public Rock(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
		
		// Width and height
		this.setWidth(16);
		this.setHeight(16);
		
		
		// Collision mask
		this.setMaskX(0);
		this.setMaskY(9);
		this.setmWidth(16);
		this.setmHeight(7);
	}
}
