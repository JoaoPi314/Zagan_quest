package com.melqjpgames.entities;

import java.awt.image.BufferedImage;

import com.melqjpgames.main.Game;

public class Skeleton extends Enemy{
	
	public Skeleton(int x, int y, int width, int height, int dir) {
		super(x, y, width, height);
		speed = 1;
		frames = 0;
		index = 0;
		nOfSprites = 4;
		this.dir = dir;
		fov = 8;
		
		// Initiates sprites
		upEnemy = new BufferedImage[nOfSprites];
		downEnemy = new BufferedImage[nOfSprites];
		rightEnemy = new BufferedImage[nOfSprites];
		leftEnemy = new BufferedImage[nOfSprites];
		
		enemyDamageUp = Game.spritesheet.getSprite(80, 80, 16, 16);
		enemyDamageDown = Game.spritesheet.getSprite(80, 96, 16, 16);
		enemyDamageRight = Game.spritesheet.getSprite(80, 112, 16, 16);
		enemyDamageLeft = Game.spritesheet.getSprite(80, 128, 16, 16);
		
		for(int i = 0; i < nOfSprites; i++) {
			upEnemy[i] = Game.spritesheet.getSprite(160 + i*16, 0, getWidth(), getHeight());
			downEnemy[i] = Game.spritesheet.getSprite(160 + i*16, 16, getWidth(), getHeight());
			rightEnemy[i] = Game.spritesheet.getSprite(160 + i*16, 32, getWidth(), getHeight());
			leftEnemy[i] = Game.spritesheet.getSprite(160 + i*16, 48, getWidth(), getHeight());
			

		}
		
	}
}
