package com.wellmax.entities;

import java.awt.image.BufferedImage;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;

/**
 * The orc is the base enemy. All logic is already implemented at Enemy
 * @author joao.gomes
 *
 */
public class Orc extends Enemy{

	//---------------------------- Attributes ----------------------------------//
	private BufferedImage[] axeSprite;
	//---------------------------- Methods ----------------------------------//

	/**
	 * Constructor
	 * @param x position
	 * @param y position
	 * @param width Orc width
	 * @param height Orc height
	 */
	public Orc(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.setSpeed(1);
		this.setFaceDir(Directions.DOWN); 
		this.setLife(10);
		this.setFov(5);
		this.setDefense(1);
		this.setDamage(1);
		
		// Initiates sprites
		this.enWalkingRight = new BufferedImage[this.getNumberOfSprites()];
		this.enWalkingLeft = new BufferedImage[this.getNumberOfSprites()];
		this.enWalkingUp = new BufferedImage[this.getNumberOfSprites()];
		this.enWalkingDown = new BufferedImage[this.getNumberOfSprites()];
		this.axeSprite = new BufferedImage[this.getNumberOfSprites()];

		this.enDamageRight = Game.spritesheet.getSprite(64, 112, this.getWidth(), this.getHeight());
		this.enDamageLeft = Game.spritesheet.getSprite(64, 128, this.getWidth(), this.getHeight());
		this.enDamageUp = Game.spritesheet.getSprite(64, 80, this.getWidth(), this.getHeight());
		this.enDamageDown = Game.spritesheet.getSprite(64, 96, this.getWidth(), this.getHeight());

		for(int i = 0; i < this.getNumberOfSprites(); i++) {
			this.enWalkingRight[i] = Game.spritesheet.getSprite(i*16, 112, this.getWidth(), this.getHeight());
			this.enWalkingLeft[i] = Game.spritesheet.getSprite(i*16, 128, this.getWidth(), this.getHeight());
			this.enWalkingUp[i] = Game.spritesheet.getSprite(i*16, 80, this.getWidth(), this.getHeight());
			this.enWalkingDown[i] = Game.spritesheet.getSprite(i*16, 96, this.getWidth(), this.getHeight());
			this.axeSprite[i] = Game.spritesheet.getSprite(32 + i*16, 144, this.getWidth(), this.getHeight());

		}
	}

	@Override
	public void attack() {
		this.setCoolDown(true);
		this.setFramesCoolDown(40);
		Game.player.setLife(Game.player.getLife() - this.getDamage() / Game.player.getDefense());
		Game.player.setKnockBackDir(this.getFaceDir());
		Game.player.setKnockBackSpeed(3);
		Game.player.setDamaged(true);
	}


}
