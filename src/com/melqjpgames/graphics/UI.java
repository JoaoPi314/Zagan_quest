package com.melqjpgames.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.melqjpgames.main.Game;

public class UI {
	
	private BufferedImage fullHeart;
	private BufferedImage fireball;
	private int heartSize;
	private int fireballSize;
	public UI() {
		heartSize = 8;
		fireballSize = 8;
		fullHeart = Game.spritesheet.getSprite(0, 144, heartSize, heartSize);
		fireball = Game.spritesheet.getSprite(0, 152, fireballSize, fireballSize);
	}
	
	public void render(Graphics g) {
		
		// Draw life		
		for(int i = 0; i < Game.player.getLife(); i++) {
			g.drawImage(fullHeart, 8 + 9*i, 4, null);
		}
		
		// Draw Fireballs
		if(Game.player.isHasFireball()) {
			g.drawImage(fireball, 12, Game.HEIGHT - 16, null);
			g.setFont(new Font("arial", Font.BOLD, 8));
			g.setColor(Color.white);
			g.drawString("x"+Game.player.getFireballs(), 21, Game.HEIGHT - 8);
		}
	}
	
}
