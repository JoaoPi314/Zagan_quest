package com.melqjpgames.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.melqjpgames.main.Game;

public class Tile {

	
	private int width, height;
	private int x, y;
	private BufferedImage sprite;
	
	public static BufferedImage TILE_FLOOR_1;
	public static BufferedImage TILE_FLOOR_2;
	public static BufferedImage TILE_FLOOR_3;
	public static BufferedImage TILE_WALL;
	public static BufferedImage TILE_RITUAL_FLOOR;
	public static BufferedImage TILE_RITUAL_STATUE;
	
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		width = 16;
		height = 16;
		TILE_FLOOR_1 = Game.spritesheet.getSprite(0, 0, width, height);
		TILE_FLOOR_2 = Game.spritesheet.getSprite(16, 0, width, height);
		TILE_FLOOR_3 = Game.spritesheet.getSprite(32, 0, width, height);
		TILE_WALL = Game.spritesheet.getSprite(48, 0, width, height);
		TILE_RITUAL_FLOOR = Game.spritesheet.getSprite(64, 0, width, height);
		TILE_RITUAL_STATUE = Game.spritesheet.getSprite(224, 64, 64, 64);
	}

	public void render(Graphics g) {
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}
	
}
