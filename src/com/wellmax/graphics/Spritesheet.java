package com.wellmax.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The spritesheet is a class that will load a spritesheet 
 * @author joao.gomes
 *
 */
public class Spritesheet {
	
	//---------------------------- Attributes ----------------------------------//	

	/**
	 * Spritesheet to be loaded
	 */
	private BufferedImage spritesheet;
	
	//---------------------------- Methods ----------------------------------//	


	/**
	 * Constructor
	 * @param path Path to spritesheet image
	 */
	public Spritesheet(String path) {	
		try {
			spritesheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	

	/**
	 * Method to return the current sprite from spritesheet
	 * @param x Sprite x position
	 * @param y sprite y position
	 * @param width Sprite width
	 * @param height Sprite height
	 * @return Sprite in BufferedImage format
	 */
	public BufferedImage getSprite(int x, int y, int width, int height) {
		return spritesheet.getSubimage(x, y, width, height);
	}
	
}
