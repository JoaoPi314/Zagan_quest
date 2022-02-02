package com.melqjpgames.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Generic Entity class. The Entity, projectile and Collectible classes
 * will inheritate this class
 * @author joao.gomes
 *
 */
public abstract class GenericEntity {

	//---------------------------- Attributes ----------------------------------//
	
	/**
	 * x position of Entity
	 */
	protected double x;
	/**
	 * y position of Entity
	 */
	protected double y;
	/**
	 * Width of Entity
	 */
	protected int width;
	/**
	 * Height of entity
	 */
	protected int height;
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
	 * Number of sprites of Entity (Used when entity has animation)
	 */
	protected int nOfSprites;
	/**
	 * Frames of actual sprite (Animation calculus)
	 */
	private int frames;
	/**
	 * Max number of frames to change sprite (Animation calculus)
	 */
	private int maxFrames;
	/**
	 * Index of sprite array (Animation calculus)
	 */
	private int index;
	
	//---------------------------- Methods ----------------------------------//	
	
	/**
	 * The constructor takes the fundamental parameters of an Entity
	 * @param x: x position
	 * @param y: y position
	 * @param width: Entity width
	 * @param height: Entity heighy
	 */
	public GenericEntity(int x, int y, int width, int height) {
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
	}
	/**
	 * Getter method to x attribute
	 * @return x position
	 */
	public double getX() {
		return this.x;
	}
	/**
	 * Getter method to y attribute
	 * @return y position
	 */
	public double getY() {
		return this.y;
	}
	/**
	 * Getter method to width attribute
	 * @return entity width
	 */
	public int getWidth() {
		return this.width;
	}
	/**
	 * Getter method to height attribute
	 * @return entity height
	 */
	public int getHeight() {
		return this.height;
	}
	/**
	 * Getter method to maskX attribute
	 * @return maskX position
	 */
	public int getMaskX() {
		return this.maskX;
	}
	/**
	 * Getter method to maskY attribute
	 * @return maskY position
	 */
	public int getMaskY() {
		return this.maskY;
	}
	/**
	 * Getter method to mWidth attribute
	 * @return Mask width
	 */
	public int getMWidth() {
		return this.mWidth;
	}
	/**
	 * Getter method to mHeight attribute
	 * @return Mask height
	 */
	public int getMHeight() {
		return this.mHeight;
	}
	
	/**
	 * Setter method to x attribute
	 * @param x x position
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * Setter method to y attribute
	 * @param y y position
	 */
	public void setY(double y) {
		this.y = y;
	}
	/**
	 * Setter method to width attribute
	 * @param width Entity width
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * Setter method to height attribute
	 * @param height Entity height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * Setter method to maskX attribute
	 * @param maskX x mask position
	 */
	public void setMaskX(int maskX) {
		this.maskX = maskX;
	}
	/**
	 * Setter method to maskY attribute
	 * @param maskY y mask position
	 */
	public void setMaskY(int maskY) {
		this.maskY = maskY;
	}
	/**
	 * Setter method to mWidth attribute
	 * @param mWidth mask width
	 */
	public void setMWidth(int mWidth) {
		this.mWidth = mWidth;
	}
	/**
	 * Setter method to mHeight attribute
	 * @param mHeight mask height
	 */
	public void setMHeight(int mHeight) {
		this.mHeight = mHeight;
	}
	
	/**
	 * Static method to check collision between two entities
	 * @param e1 Generic entity 1
	 * @param e2 Generic entity 2
	 * @return true if e1 and e2 are colliding
	 */
	public static boolean isColliding(GenericEntity e1, GenericEntity e2) {
		Rectangle e1Mask = new Rectangle((int)(e1.getX() + e1.getMaskX()), (int)(e1.getY() + e1.getMaskY()), e1.getMWidth(), e1.getMHeight());
		Rectangle e2Mask = new Rectangle((int)(e2.getX() + e2.getMaskX()), (int)(e2.getY() + e2.getMaskY()), e2.getMWidth(), e2.getMHeight());
		
		return e1Mask.intersects(e2Mask);
	}	
	
	/**
	 * Method to set all collision mask attributes at once
	 * @param maskX x mask position
	 * @param maskY y mask position
	 * @param mWidth mask width
	 * @param mHeight mask height
	 */
	public void setMask(int maskX, int maskY, int mWidth, int mHeight) {
		this.setMaskX(maskX);
		this.setMaskY(maskY);
		this.setMWidth(mWidth);
		this.setMHeight(mHeight);
	}
	
	/**
	 * Abstract method to evaluate entity logic at each frame
	 */
	abstract void update();
	/**
	 * Abstract method to render entity at each frame
	 * @param g Graphic object to render entity
	 */
	abstract void render(Graphics g);
	

}
