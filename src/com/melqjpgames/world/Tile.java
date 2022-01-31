package com.melqjpgames.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.melqjpgames.main.Game;

public class Tile {

	
	private int width=16, height=16;
	private int x, y;
	private BufferedImage sprite;
	
	public static BufferedImage TILE_ROCK; 

	public static BufferedImage[][] TILE_GRASS;
	public static BufferedImage[][] TILE_WALL;
	public static BufferedImage[][] TILE_STONE_FLOOR;
	public static BufferedImage TILE_RITUAL_STATUE;
	
	
	static {
		TILE_GRASS = new BufferedImage[3][3];
		TILE_WALL = new BufferedImage[3][3];
		TILE_STONE_FLOOR = new BufferedImage[3][3];
		
		TILE_ROCK = Game.spritesheet1.getSprite(48, 48, 16, 16);
		
		// Initiates sprites 
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				// GRASS
				TILE_GRASS[i][j] = Game.spritesheet1.getSprite(j*16, i*16, 16, 16);
				TILE_WALL[i][j] = Game.spritesheet1.getSprite(48 + j*16, i*16, 16, 16);
				TILE_STONE_FLOOR[i][j] = Game.spritesheet1.getSprite(j*16, 48 + i*16, 16, 16);
			}
		}
	}
	
	
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		width = 16;
		height = 16;
		TILE_RITUAL_STATUE = Game.spritesheet.getSprite(224, 64, 64, 64);
	}

	public void render(Graphics g) {
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}
	
}
