package com.wellmax.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	
	
	private BufferedImage spritesheet;
	
	
	/*
	* The Constructor of Spritesheet receives a path to spritesheet file
	*/
	public Spritesheet(String path) {	
		try {
			spritesheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/*
	 * The getSprite returns the current sprite from spritesheet
	 */
	public BufferedImage getSprite(int x, int y, int width, int height) {
		return spritesheet.getSubimage(x, y, width, height);
	}
	
}
