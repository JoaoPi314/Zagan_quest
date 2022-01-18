package com.melqjpgames.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.melqjpgames.entities.*;
import com.melqjpgames.main.Game;

public class World {
	
	
	private Tile[] tiles;
	public static int WIDTH, HEIGHT;
	
	
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			
			
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			int[] pixels = new int[WIDTH * HEIGHT];
			tiles = new Tile[WIDTH * HEIGHT];
			map.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);

			tiles[0] = new WallTile(0, 0, Tile.TILE_WALL);
			// Sweeps map
			for(int xx = 0; xx < WIDTH; xx++) {
				for(int yy = 0; yy < HEIGHT; yy++) {
					int currentPixel =pixels[xx + (yy*WIDTH)]; 
					
					tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR_1);

					
					if(currentPixel == 0xFF000000) {
						// floor
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR_1);
					}else if(currentPixel == 0xFF555555) {
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR_2);
					}else if(currentPixel == 0xFF1D1D1D) {
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR_3);
					}else if(currentPixel == 0xFFFFFFFF) {
						// Wall
						tiles[xx + (yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.TILE_WALL);
					}else if(currentPixel == 0xFF0000FF) {
						// Player
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);
					}else if(currentPixel == 0xFFFF0000) {
						// Enemy
						Game.entities.add(new Enemy(xx*16, yy*16, 16, 16));
					}else if(currentPixel == 0xFFFF8200) {
						// Fireball
						Game.entities.add(new Fireball(xx*16, yy*16, 16, 16));
					}else if(currentPixel == 0xFF14FF00) {
						// Health potion
						Game.entities.add(new HealthPotion(xx*16, yy*16, 16, 16));
					}
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void render(Graphics g) {
		for(int xx = 0; xx < WIDTH; xx++) {
			for(int yy = 0; yy < HEIGHT; yy++) {
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}
	
	
	
}
