package com.melqjpgames.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.melqjpgames.main.Game;
import com.melqjpgames.world.World;

public class UI {
	
	private BufferedImage fullHeart;
	private BufferedImage fireball;
	private BufferedImage waveBarCornerRight;
	private BufferedImage waveBarCornerLeft;
	private BufferedImage waveBarMiddle;
	private BufferedImage waveBarBoss;
	private BufferedImage note;
	
	private int heartSize;
	private int fireballSize;
	private int noteSize = 8;
	private final int waveWidth = 16;
	private final int waveHeight = 16;
	
	private double maxEnemies;
	private double currentEnemies;
	private double maxWaveSize = 13*16;
	
	
	
	public UI() {
		heartSize = 8;
		fireballSize = 8;
		noteSize = 8;
		fullHeart = Game.spritesheet.getSprite(0, 144, heartSize, heartSize);
		fireball = Game.spritesheet.getSprite(0, 152, fireballSize, fireballSize);
		note = Game.spritesheet.getSprite(8,152, noteSize, noteSize);
		
		waveBarCornerRight = Game.spritesheet.getSprite(80, 48, waveWidth, waveHeight);
		waveBarCornerLeft = Game.spritesheet.getSprite(80, 16, waveWidth, waveHeight);
		waveBarMiddle = Game.spritesheet.getSprite(80, 32, waveWidth, waveHeight);
		waveBarBoss= Game.spritesheet.getSprite(80, 64, waveWidth, waveHeight);
		
		maxEnemies = Game.enemies.size();
		currentEnemies = maxEnemies;
		
	}
	
	
	public void update() {
		currentEnemies = Game.enemies.size();
	}
	
	
	public void render(Graphics g) {
		
		// Draw life		
		for(int i = 0; i < Game.player.getLife(); i++) {
			g.drawImage(fullHeart, 8 + 9*i, 4, null);
		}
		
		if(Game.player.isHasFireball()) {
			// Draw Fireballs
			g.drawImage(fireball, 8, 14, null);
			g.setFont(new Font("arial", Font.BOLD, 8));
			g.setColor(Color.white);
			g.drawString("x"+Game.player.getFireballs(), 17, 21);
			
		}else {
			// Draw note
			g.drawImage(note, 8, 14, null);
			g.setFont(new Font("arial", Font.BOLD, 8));
			g.setColor(Color.white);
			g.drawString("- -", 17, 21);
		}
		
		g.setColor(new Color(0xFF, 0xFF, 0xFF, 70));
		g.fillRect(8, 13, (int)((Game.player.getFramesCoolDown()/Game.player.getMaxFramesCoolDown())*18), 10);
		
		// Draw waveBar
		g.setColor(Color.red);
		g.fillRect(23, 233, (int)((currentEnemies/maxEnemies)*maxWaveSize), 3);
		
		
		g.drawImage(waveBarCornerLeft, 16, 224, null);
		g.drawImage(waveBarCornerRight,224, 224, null);
	
		for(int i = 0; i < (Game.WIDTH/World.TILE_SIZE) - 4; i++) {
			g.drawImage(waveBarMiddle, 32 +i*16, 224, null);
		}

		
	}
	
}
