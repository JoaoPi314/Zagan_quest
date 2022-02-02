package com.wellmax.entities;

import java.awt.image.BufferedImage;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;

public class Skeleton extends Enemy{
	
	public Skeleton(int x, int y, int width, int height, Directions dir) {
		super(x, y, width, height);
		this.setSpeed(1);
		this.setFaceDir(dir);
		this.setFov(8);
		
		// Initiates sprites
		enRight = new BufferedImage[this.getnOfSprites()];
		enLeft = new BufferedImage[this.getnOfSprites()];
		enUp = new BufferedImage[this.getnOfSprites()];
		enDown = new BufferedImage[this.getnOfSprites()];

		enDamageRight = Game.spritesheet.getSprite(80, 112, this.getWidth(), this.getHeight());
		enDamageLeft = Game.spritesheet.getSprite(80, 128, this.getWidth(), this.getHeight());
		enDamageUp = Game.spritesheet.getSprite(80, 80, this.getWidth(), this.getHeight());
		enDamageDown = Game.spritesheet.getSprite(80, 96, this.getWidth(), this.getHeight());

		
		for(int i = 0; i < nOfSprites; i++) {
			enRight[i] = Game.spritesheet.getSprite(160 + i*16, 32, this.getWidth(), this.getHeight());
			enLeft[i] = Game.spritesheet.getSprite(160 + i*16, 48, this.getWidth(), this.getHeight());
			enUp[i] = Game.spritesheet.getSprite(160 + i*16, 0, this.getWidth(), this.getHeight());
			enDown[i] = Game.spritesheet.getSprite(160 + i*16, 16, this.getWidth(), this.getHeight());	
		}
		
	}
}
