package com.wellmax.world.tiles;

import com.wellmax.world.Tile;

import java.awt.image.BufferedImage;

/**
 * FloorTile doesn't have collision with entities
 * @author joao.gomes
 *
 */
public class FloorTile extends Tile {

	/**
	 * FloorTile constructor. It only calls super constructor
	 * @param x x position
	 * @param y y position
	 * @param sprite Tile sprite
	 */
	public FloorTile(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
	}
	
}
