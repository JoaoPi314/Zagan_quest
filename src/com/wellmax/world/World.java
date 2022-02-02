package com.wellmax.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.wellmax.entities.*;
import com.wellmax.main.Game;

public class World {
	
	
	public static Tile[] tiles;
	public static Tile statue;
	
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 16;
	
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			
			
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			int[] pixels = new int[WIDTH * HEIGHT];
			tiles = new Tile[WIDTH * HEIGHT];
			map.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
			 			
			// Sweeps map
			for(int xx = 0; xx < WIDTH; xx++) {
				for(int yy = 0; yy < HEIGHT; yy++) {
					int currentPixel =pixels[xx + (yy*WIDTH)]; 
					
					tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_GRASS[1][1]);

					switch(currentPixel) {
					
						case 0xFF9D8686: // Wall facing down
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_WALL[2][1]);
							break;
						case 0xFF786565: // Wall facing right
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_WALL[1][2]);
							break;
						case 0xFF665252: // Wall middle
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_WALL[1][1]);
							break;
						case 0xFF5D4848: // Wall facing left
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_WALL[1][0]);
							break;
						case 0xFF3A3131: // Wall facing up
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_WALL[0][1]);
							break;
						case 0xFF8A7575: // Wall corner down-left
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_WALL[2][0]);
							break;
						case 0xFFB19898: // Wall corner down-right
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_WALL[2][2]);
							break;
						case 0xFF4A3B3B: // Wall corner top-right
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_WALL[0][2]);
							break;
						case 0xFF2D2C2C: // Wall corner top-left
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_WALL[0][0]);
							break;
						case 0xFF999999: // Stone Floor Middle
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_STONE_FLOOR[1][1]);
							break;
						case 0xFF333333: // Stone Floor facing up
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_STONE_FLOOR[0][1]);
							break;
						case 0xFFB8B8B8: // Stone Floor facing right
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_STONE_FLOOR[1][2]);
							break;
						case 0xFFDEDCDC: // Stone Floor facing down
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_STONE_FLOOR[2][1]);
							break;
						case 0xFF777777: // Stone Floor facing left
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_STONE_FLOOR[1][0]);
							break;
						case 0xFF181717: // Stone Floor top-left
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_STONE_FLOOR[0][0]);
							break;
						case 0xFF555555: // Stone Floor top-right
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_STONE_FLOOR[0][2]);
							break;
						case 0xFFCECECE: // Stone Floor down-left
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_STONE_FLOOR[2][0]);
							break;
						case 0xFFF7F5F5: // Stone Floor down-right
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_STONE_FLOOR[2][2]);
							break;
						case 0xFFEE03FF: // Tile that statue is placed
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_STONE_FLOOR[1][1]);
							statue = new WallTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_RITUAL_STATUE);
							break;
						case 0xFF6C0073: // Stone floor but is statue
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_STONE_FLOOR[1][1]);
							break;
						case 0xFFCDA664: // Path middle
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_PATH[1][1]);
							break;
						case 0xFFA58259: // Path up
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_PATH[0][1]);
							break;
						case 0xFFD1B57E: // Path down
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_PATH[2][1]);
							break;
						case 0xFFDCA548: // Path left
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_PATH[1][0]);
							break;
						case 0xFFF1BA5C: // Path right
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_PATH[1][2]);
							break;
						case 0xFFB39471: // Path top-right
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_PATH[0][2]);
							break;
						case 0xFFAF7B40: // Path top-left
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_PATH[0][0]);
							break;
						case 0xFFDEC47E: // Path down-right
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_PATH[2][2]);
							break;
						case 0xFFC4A97A: // Path down-left
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_PATH[2][0]);
							break;
						case 0xFF0E0404: // Rock
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_ROCK);
							break;
						case 0xFF0D260E: // Trees
							Ent ent = new Ent(xx*TILE_SIZE, yy*TILE_SIZE, 32, 48);
							ent.setMask(3, 38, 26, 10);
							Game.ents.add(ent);
							break;
						case 0xFF0A4A0C: // Floor with collision (Tree)
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_GRASS[1][1]);
							break;
						case 0xFFF4FF00: // Health Potion
							HealthPotion healthPotion = new HealthPotion(xx*TILE_SIZE, yy*TILE_SIZE, TILE_SIZE, TILE_SIZE);
							healthPotion.setMask(4, 0, 8, 16);
							Game.entities.add(healthPotion);
							break;
						case 0xFFFF8200: // FireBall
							Fireball fireball = new Fireball(xx*TILE_SIZE, yy*TILE_SIZE, TILE_SIZE, TILE_SIZE);
							fireball.setMask(4, 0, 8, 16);
							Game.entities.add(fireball);
							break;
						case 0xFFFF0000: // Enemy
							Enemy en = new Enemy(xx*TILE_SIZE, yy*TILE_SIZE, TILE_SIZE, TILE_SIZE);
							Game.enemies.add(en);
							break;
						case 0xFF0000FF: // Player
							Game.player.setX(xx*TILE_SIZE);
							Game.player.setY(yy*TILE_SIZE);
							break;

						default:
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_GRASS[1][1]);
							break;
					}
				}
			}
									
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isFree(int x, int y) {
		// Converts position to tiles
		int x1 = x / TILE_SIZE;
		int y1 = y / TILE_SIZE;
		
		int x2 = (x + TILE_SIZE - 1) / TILE_SIZE;
		int y2 = y / TILE_SIZE;
		
		int x3 = x  / TILE_SIZE;
		int y3 = (y + TILE_SIZE - 1) / TILE_SIZE;
		
		int x4 = (x + TILE_SIZE - 1) / TILE_SIZE;
		int y4 = (y + TILE_SIZE - 1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				 (tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				 (tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				 (tiles[x4 + (y4*World.WIDTH)] instanceof WallTile));
		
	}
	
	public void render(Graphics g) {
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile underFloor = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_GRASS[1][1]);
				Tile tile = tiles[xx + (yy*WIDTH)];
				underFloor.render(g);
				tile.render(g);
			}
		}		
		statue.render(g);
	}
	
	
	
}
