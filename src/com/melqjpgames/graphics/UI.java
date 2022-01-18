package com.melqjpgames.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.melqjpgames.main.Game;

public class UI {
	
	private BufferedImage fullHeart;
	private int heartSize;
	public UI() {
		heartSize = 8;
		fullHeart = Game.spritesheet.getSprite(0, 144, heartSize, heartSize);
	}
	
	public void render(Graphics g) {
		
		int i;
		
		for(i = 0; i < Game.player.getLife(); i++) {
			g.drawImage(fullHeart, 8 + 9*i, 4, null);
		}
		
	}
	
}
