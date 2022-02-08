package com.wellmax.world;

import java.awt.image.BufferedImage;

/**
 * WallTile will have collision with entities
 * @author joao.gomes
 *
 */
public class WallTile extends Tile{
	
	/**
	 * WallTile constructor. It only calls super constructor
	 * @param x x position
	 * @param y y position
	 * @param sprite Tile sprite
	 */
	public WallTile(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
	}
	
}
