package com.wellmax.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wellmax.main.Game;

/**
 * The Tile class contains all sprites related to map renderization.
 * @author joao.gomes
 *
 */
public class Tile {
	
	//---------------------------- Attributes ----------------------------------//	

	/**
	 * X position of tile
	 */
	protected int x;
	/**
	 * Y position of tile
	 */
	protected int y;
	/**
	 * Tile width
	 */
	protected int width;
	/**
	 * Tile height
	 */
	protected int height;
	/**
	 * Tile sprite
	 */
	protected BufferedImage sprite;
	
	// Static attributes

	/**
	 * Grass tile (Nine positions in 3x3 array)
	 */
	public static BufferedImage[][] TILE_GRASS;
	/**
	 * Wall tile (Nine positions in 3x3 array)
	 */
	public static BufferedImage[][] TILE_WALL;
	/**
	 * Stone floor tile (Nine positions in 3x3 array)
	 */
	public static BufferedImage[][] TILE_STONE_FLOOR;
	/**
	 * Path tile (Nine positions in 3x3 array)
	 */
	public static BufferedImage[][] TILE_PATH;
	/**
	 * Rock tile (It is a scenario item)
	 */
	public static BufferedImage TILE_ROCK;
	/**
	 * Tree tile (It is a scenario item)
	 */
	public static BufferedImage TILE_TREE;
	/**
	 * Statue tile (It is a scenario item)
	 */
	public static BufferedImage TILE_STATUE;
	
	static {
		// Arrays
		TILE_GRASS = new BufferedImage[3][3];
		TILE_WALL = new BufferedImage[3][3];
		TILE_STONE_FLOOR = new BufferedImage[3][3];
		TILE_PATH = new BufferedImage[3][3];
		// Scenario items
		TILE_ROCK = Game.spritesheet1.getSprite(48, 48, 16, 16);
		TILE_TREE = Game.spritesheet1.getSprite(64, 48, 32, 48);
		TILE_STATUE = Game.spritesheet.getSprite(224, 64, 64, 64);
		
		// Initiates sprites 
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				// GRASS
				TILE_GRASS[i][j] = Game.spritesheet1.getSprite(j*16, i*16, 16, 16);
				TILE_WALL[i][j] = Game.spritesheet1.getSprite(48 + j*16, i*16, 16, 16);
				TILE_STONE_FLOOR[i][j] = Game.spritesheet1.getSprite(j*16, 48 + i*16, 16, 16);
				TILE_PATH[i][j] = Game.spritesheet1.getSprite(96 + j*16, i*16, 16, 16);
			}
		}
	}
	
	//---------------------------- Methods ----------------------------------//	

	/**
	 * Tile Constructor
	 * @param x x position of tile
	 * @param y y position of tile
	 * @param sprite Tile sprite
	 */
	public Tile(int x, int y, BufferedImage sprite) {
		this.setX(x);
		this.setY(y);
		this.sprite = sprite;
		this.setWidth(16);
		this.setHeight(16);
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}


	public void render(Graphics g) {
		g.drawImage(this.sprite, x - Camera.x, y - Camera.y, null);
	}
	
}
