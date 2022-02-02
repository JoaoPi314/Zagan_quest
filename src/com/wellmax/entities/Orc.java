package com.wellmax.entities;

import java.awt.image.BufferedImage;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;

public class Orc extends Enemy{
	
	public Orc(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.setSpeed(1);
		this.setFaceDir(Directions.DOWN); 
		this.setLife(10);
		this.setFov(5);
		this.setDefense(1);
		this.setDamage(1);
		
		// Initiates sprites
		this.enRight = new BufferedImage[this.getnOfSprites()];
		this.enLeft = new BufferedImage[this.getnOfSprites()];
		this.enUp = new BufferedImage[this.getnOfSprites()];
		this.enDown = new BufferedImage[this.getnOfSprites()];

		this.enDamageRight = Game.spritesheet.getSprite(64, 112, this.getWidth(), this.getHeight());
		this.enDamageLeft = Game.spritesheet.getSprite(64, 128, this.getWidth(), this.getHeight());
		this.enDamageUp = Game.spritesheet.getSprite(64, 80, this.getWidth(), this.getHeight());
		this.enDamageDown = Game.spritesheet.getSprite(64, 96, this.getWidth(), this.getHeight());

		for(int i = 0; i < this.getnOfSprites(); i++) {
			this.enRight[i] = Game.spritesheet.getSprite(i*16, 112, this.getWidth(), this.getHeight());
			this.enLeft[i] = Game.spritesheet.getSprite(i*16, 128, this.getWidth(), this.getHeight());
			this.enUp[i] = Game.spritesheet.getSprite(i*16, 80, this.getWidth(), this.getHeight());
			this.enDown[i] = Game.spritesheet.getSprite(i*16, 96, this.getWidth(), this.getHeight());
		}
	}
}
