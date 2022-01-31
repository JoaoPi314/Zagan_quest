package com.melqjpgames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.melqjpgames.main.Game;
import com.melqjpgames.world.Camera;

public class Ent extends Entity{
	
	
	private BufferedImage entIdle;
	
	
	public Ent(int x, int y, int width, int height) {
		super(x, y, width, height);	
		entIdle = Game.spritesheet1.getSprite(64, 48, width, height);
		
	}
	
	public void render(Graphics g) {
		g.drawImage(entIdle, (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
	}
	
	
	
}
