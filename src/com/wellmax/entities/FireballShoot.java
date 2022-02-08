package com.wellmax.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;
import com.wellmax.world.Camera;
import com.wellmax.world.World;

/**
 * The FireBallShoot is a power that deals
 * 2x the damage of LuteFire, dealing a major
 * knockback when an enemy is hitted
 * @author joao.gomes
 *
 */
public class FireballShoot extends Projectile{

	//---------------------------- Attributes ----------------------------------//	


	/**
	 * Fireball facing right sprite
	 */
	private BufferedImage[] fireRight;
	/**
	 * Fireball facing left
	 */
	private BufferedImage[] fireLeft;
	/**
	 * Fireball facing up sprite
	 */
	private BufferedImage[] fireUp;
	/**
	 * Fireball facing down sprite
	 */
	private BufferedImage[] fireDown;

	//---------------------------- Methods ----------------------------------//	
	
	/**
	 * Constructor
	 * @param x x position
	 * @param y y position
	 * @param width Fireballshoot width
	 * @param height Fireballshoot height
	 * @param dir Direction of shoot
	 * @param dx x axis multiplier
	 * @param dy y axis multiplier
	 */
	public FireballShoot(int x, int y, int width, int height, Directions dir, int dx, int dy) {
		super(x, y, width, height, dx, dy);
		
		this.setFaceDir(dir);
		this.setSpeed(4);
		this.setTotalTime(60);
		this.setTimeRemain(0);
		this.setDamage(5);
		
		this.fireRight = new BufferedImage[this.getnOfSprites()];
		this.fireLeft = new BufferedImage[this.getnOfSprites()];
		this.fireUp = new BufferedImage[this.getnOfSprites()];
		this.fireDown = new BufferedImage[this.getnOfSprites()];

		
		for(int i = 0; i < this.getnOfSprites(); i++) {
			this.fireRight[i] = Game.spritesheet.getSprite(96 + i*16, 32, this.getmWidth(), this.getHeight());
			this.fireLeft[i] = Game.spritesheet.getSprite(96 + i*16, 48, this.getmWidth(), this.getHeight());
			this.fireUp[i] = Game.spritesheet.getSprite(96 + i*16, 0, this.getmWidth(), this.getHeight());
			this.fireDown[i] = Game.spritesheet.getSprite(96 + i*16, 16, this.getmWidth(), this.getHeight());
		}
		
	}
	
	
	public void render(Graphics g) {
		switch(this.getFaceDir()) {
			case UP:
				g.drawImage(fireUp[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
				break;
			case DOWN:
				g.drawImage(fireDown[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
				break;
			case RIGHT:
				g.drawImage(fireRight[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
				break;
			case LEFT:
				g.drawImage(fireLeft[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
				break;
			default:
				g.drawImage(fireRight[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
				break;
		}
	}

	
}
