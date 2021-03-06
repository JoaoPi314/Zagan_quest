package com.wellmax.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wellmax.main.Game;
import com.wellmax.world.World;

/**
 * Ui class contains all user interface elements
 * @author joao.gomes
 *
 */
public class UI {
	
	//---------------------------- Attributes ----------------------------------//	

	/**
	 * Sprite of heart
	 */
	private final BufferedImage heart;
	/**
	 * Sprite of fireball
	 */
	private final BufferedImage fireball;
	/**
	 * Sprite of right corner of wave bar
	 */
	private final BufferedImage waveBarCornerRight;
	/**
	 * Sprite of left corner of wave bar
	 */
	private final BufferedImage waveBarCornerLeft;
	/**
	 * Sprite of wave bar
	 */
	private final BufferedImage waveBarMiddle;
	/**
	 * Sprite of boss wave bar
	 */
	private final BufferedImage waveBarBoss;
	/**
	 * Sprite of musical note
	 */
	private final BufferedImage note;
	
	/**
	 * Size of heart displayed
	 */
	private int heartSize;
	/**
	 * Size of note displayed
	 */
	private int fireballSize;
	/**
	 * Size of notes
	 */
	private int noteSize;
	/**
	 * Width of wave bar
	 */
	private int waveSize;

	/**
	 * Number of enemies at world creation
	 */
	private double maxEnemies;
	/**
	 * Number of enemies remaining
	 */
	private double currentEnemies;
	/**
	 * Total size of wave bar
	 */
	private double maxWaveSize;
	
	
	//---------------------------- Methods ----------------------------------//	
	/**
	 * UI constructor
	 */
	public UI() {
		
		// Variables
		this.setHeartSize(8);
		this.setFireballSize(8);
		this.setNoteSize(8);
		this.setWaveSize(16);
		this.setMaxWaveSize(13*16);
		this.setMaxEnemies(Game.enemies.size());
		this.setCurrentEnemies(this.getMaxEnemies());
		
		// Sprites
		this.heart = Game.spritesheet.getSprite(0, 144, this.getHeartSize(), this.getHeartSize());
		this.fireball = Game.spritesheet.getSprite(0, 152, this.getFireballSize(), this.getFireballSize());
		this.note = Game.spritesheet.getSprite(8,152, this.getNoteSize(), this.getNoteSize());
		
		this.waveBarCornerRight = Game.spritesheet.getSprite(80, 48, this.getWaveSize(), this.getWaveSize());
		this.waveBarCornerLeft = Game.spritesheet.getSprite(80, 16, this.getWaveSize(), this.getWaveSize());
		this.waveBarMiddle = Game.spritesheet.getSprite(80, 32, this.getWaveSize(), this.getWaveSize());
		this.waveBarBoss= Game.spritesheet.getSprite(80, 64, this.getWaveSize(), this.getWaveSize());

	}
	

	public int getHeartSize() {
		return heartSize;
	}

	public void setHeartSize(int heartSize) {
		this.heartSize = heartSize;
	}

	public int getFireballSize() {
		return fireballSize;
	}

	public void setFireballSize(int fireballSize) {
		this.fireballSize = fireballSize;
	}

	public int getNoteSize() {
		return noteSize;
	}

	public void setNoteSize(int noteSize) {
		this.noteSize = noteSize;
	}

	public double getMaxEnemies() {
		return maxEnemies;
	}

	public void setMaxEnemies(double maxEnemies) {
		this.maxEnemies = maxEnemies;
	}

	public double getCurrentEnemies() {
		return currentEnemies;
	}

	public void setCurrentEnemies(double currentEnemies) {
		this.currentEnemies = currentEnemies;
	}

	public double getMaxWaveSize() {
		return maxWaveSize;
	}

	public void setMaxWaveSize(double maxWaveSize) {
		this.maxWaveSize = maxWaveSize;
	}

	public int getWaveSize() {
		return waveSize;
	}

	public void setWaveSize(int waveSize) {
		this.waveSize = waveSize;
	}

	public void update() {
		this.setCurrentEnemies(Game.enemies.size());
	}
	
	/**
	 * Renders the UI
	 * @param g Graphics to be rendered
	 */
	public void render(Graphics g) {
		
		// Draw life		
		for(int i = 0; i < Game.player.getLife(); i++) {
			g.drawImage(this.heart, 8 + 9*i, 4, null);
		}
		
		if(Game.player.isHasFireball()) {
			// Draw Fireballs
			g.drawImage(this.fireball, 8, 14, null);
			g.setFont(new Font("arial", Font.BOLD, 8));
			g.setColor(Color.white);
			g.drawString("x"+Game.player.getFireballs(), 17, 21);
			
		}else {
			// Draw note
			g.drawImage(this.note, 8, 14, null);
			g.setFont(new Font("arial", Font.BOLD, 8));
			g.setColor(Color.white);
			g.drawString("- -", 17, 21);
		}
		
		g.setColor(new Color(0xFF, 0xFF, 0xFF, 70));
		g.fillRect(8, 13, (int)((Game.player.getFramesCoolDown()/Game.player.getMaxFramesCoolDown())*18), 10);
		
		// Draw waveBar
		g.setColor(Color.red);
		g.fillRect(23, 233, (int)((this.getCurrentEnemies()/this.getMaxEnemies())*this.getMaxWaveSize()), 3);
		
		
		g.drawImage(this.waveBarCornerLeft, 16, 224, null);
		g.drawImage(this.waveBarCornerRight,224, 224, null);
	
		for(int i = 0; i < (Game.WIDTH/World.TILE_SIZE) - 4; i++) {
			g.drawImage(this.waveBarMiddle, 32 +i*16, 224, null);
		}

		
	}
	
}
