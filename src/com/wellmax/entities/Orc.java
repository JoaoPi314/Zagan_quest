package com.wellmax.entities;

import java.awt.image.BufferedImage;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;
import com.wellmax.main.Sound;

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

		this.setShadowOffsetX(0);
		this.setShadowOffsetY(15);

		// Initiates sprites
		this.enWalkingRight = new BufferedImage[this.getNumberOfSprites()];
		this.enWalkingLeft = new BufferedImage[this.getNumberOfSprites()];
		this.enWalkingUp = new BufferedImage[this.getNumberOfSprites()];
		this.enWalkingDown = new BufferedImage[this.getNumberOfSprites()];
		this.axeSprite = new BufferedImage[this.getNumberOfSprites()];

		this.shadowVertical = Game.orcSpritesheet.getSprite(128, 0, this.getWidth(), this.getHeight());
		this.shadowHorizontal = Game.orcSpritesheet.getSprite(160, 0, this.getWidth(), this.getHeight());

		this.enDown = Game.orcSpritesheet.getSprite(0, 0, this.getWidth(), this.getHeight());
		this.enLeft = Game.orcSpritesheet.getSprite(32, 0, this.getWidth(), this.getHeight());
		this.enUp = Game.orcSpritesheet.getSprite(64, 0, this.getWidth(), this.getHeight());
		this.enRight = Game.orcSpritesheet.getSprite(96, 0, this.getWidth(), this.getHeight());

		this.enDamageRight = Game.orcSpritesheet.getSprite(64, 112, this.getWidth(), this.getHeight());
		this.enDamageLeft = Game.orcSpritesheet.getSprite(64, 128, this.getWidth(), this.getHeight());
		this.enDamageUp = Game.orcSpritesheet.getSprite(64, 80, this.getWidth(), this.getHeight());
		this.enDamageDown = Game.orcSpritesheet.getSprite(64, 96, this.getWidth(), this.getHeight());

		for(int i = 0; i < this.getNumberOfSprites(); i++) {
			this.enWalkingDown[i] = Game.orcSpritesheet.getSprite(0, 0, this.getWidth(), this.getHeight());
			this.enWalkingLeft[i] = Game.orcSpritesheet.getSprite(32, 0, this.getWidth(), this.getHeight());
			this.enWalkingUp[i] = Game.orcSpritesheet.getSprite(64, 0, this.getWidth(), this.getHeight());
			this.enWalkingRight[i] = Game.orcSpritesheet.getSprite(96, 0, this.getWidth(), this.getHeight());
//			this.axeSprite[i] = Game.orcSpritesheet.getSprite(32 + i*16, 0, this.getWidth(), this.getHeight());

		}
	}

	@Override
	public void attack() {
		Sound.zaganHit.play();
		this.setCoolDown(true);
		this.setFramesCoolDown(40);
		Game.player.setLife(Game.player.getLife() - this.getDamage() / Game.player.getDefense());
		Game.player.setKnockBackDir(this.getFaceDir());
		Game.player.setKnockBackSpeed(3);
		Game.player.setDamaged(true);
	}


}
