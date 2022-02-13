package com.wellmax.world;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.wellmax.entities.Enemy;
import com.wellmax.main.Game;

/**
 * Scenario items have a different behgavior when colliding with 
 * players. They have a configurable collision mask
 * @author joao.gomes
 *
 */
public class Scenario extends Tile{

	//---------------------------- Attributes ----------------------------------//	
	/**
	 * x position of collision mask (offset based on x attribute)
	 */
	protected int maskX;
	/**
	 * y position of collision mask(offset based on y attribute)
	 */
	protected int maskY;
	/**
	 * Width of collision mask. Based on (maskX, maskY) point
	 */
	protected int mWidth;
	/**
	 * Height of collision mask. Based on (maskX, maskY) point
	 */
	protected int mHeight;

	/**
	 * Shadow sprite
	 */
	protected BufferedImage shadowSprite;
	/**
	 * Shadow x position
	 */
	private int shadowX;
	/**
	 * Shadow y position
	 */
	private int shadowY;

	//---------------------------- Methods ----------------------------------//
	
	/**
	 * Scenario constructor. It receives also the width and height
	 * @param x x position
	 * @param y y position
	 * @param sprite Scenario item sprite
	 */
	public Scenario(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
	}
	
	/**
	 * @return the maskX
	 */
	public int getMaskX() {
		return maskX;
	}
	
	/**
	 * @param maskX the maskX to set
	 */
	public void setMaskX(int maskX) {
		this.maskX = maskX;
	}
	
	/**
	 * @return the maskY
	 */
	public int getMaskY() {
		return maskY;
	}
	
	/**
	 * @param maskY the maskY to set
	 */
	public void setMaskY(int maskY) {
		this.maskY = maskY;
	}
	
	/**
	 * @return the mWidth
	 */
	public int getmWidth() {
		return mWidth;
	}
	
	/**
	 * @param mWidth the mWidth to set
	 */
	public void setmWidth(int mWidth) {
		this.mWidth = mWidth;
	}
	
	/**
	 * @return the mHeight
	 */
	public int getmHeight() {
		return mHeight;
	}
	
	/**
	 * @param mHeight the mHeight to set
	 */
	public void setmHeight(int mHeight) {
		this.mHeight = mHeight;
	}

	public int getShadowX() {
		return shadowX;
	}

	public void setShadowX(int shadowX) {
		this.shadowX = shadowX;
	}

	public int getShadowY() {
		return shadowY;
	}

	public void setShadowY(int shadowY) {
		this.shadowY = shadowY;
	}

	public void render(Graphics g){
		g.drawImage(this.shadowSprite,this.getShadowX() - Camera.x, this.getShadowY() - Camera.y, null);
		g.drawImage(this.sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);

	}

}
