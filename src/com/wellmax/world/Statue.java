package com.wellmax.world;

import java.awt.image.BufferedImage;

/**
 * Tree class
 * @author joao.gomes
 *
 */
public class Statue extends Scenario{
	/**
	 * The tree constructor initializes the collision mask values
	 * @param x x position
	 * @param y y position
	 * @param sprite Rock sprite
	 */
	public Statue(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
		
		// Sprite
		this.sprite = Tile.TILE_STATUE;
		
		// Width and height
		this.setWidth(64);
		this.setHeight(64);
		
		// Collision mask
		this.setMaskX(3);
		this.setMaskY(40);
		this.setmWidth(48);
		this.setmHeight(12);
	}
}
