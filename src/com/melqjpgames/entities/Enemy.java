package com.melqjpgames.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.melqjpgames.main.Game;
import com.melqjpgames.world.Camera;
import com.melqjpgames.world.World;

public class Enemy extends Entity{

	// Directions
	private boolean right, up, left, down;
	private double speed;
	private final int upDir = 0, downDir = 1, rightDir = 2, leftDir = 3;
	private int dir;
	private boolean moved;
	// Sprites
	private BufferedImage[] upEnemy;
	private BufferedImage[] downEnemy;
	private BufferedImage[] rightEnemy;
	private BufferedImage[] leftEnemy;
	private int nOfSprites;
	
	// Animation of sprite
	private int frames;
	private final int maxFrames = 10;
	private int index;
	private final int maxIndex = 3;
	
	
	private int fov;
	public final int maskx = 0;
	public final int masky = 0;
	public final int maskw = 16;
	public final int maskh = 16;
	
	private int life = 10;
	
	
	
	/*
	 * Constructor receives the position and size of player
	 */
	public Enemy(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 1;
		frames = 0;
		index = 0;
		nOfSprites = 4;
		dir = downDir;
		fov = 5;
		
		// Initiates sprites
		upEnemy = new BufferedImage[nOfSprites];
		downEnemy = new BufferedImage[nOfSprites];
		rightEnemy = new BufferedImage[nOfSprites];
		leftEnemy = new BufferedImage[nOfSprites];
		
		
		for(int i = 0; i < nOfSprites; i++) {
			upEnemy[i] = Game.spritesheet.getSprite(i*16, 80, getWidth(), getHeight());
			downEnemy[i] = Game.spritesheet.getSprite(i*16, 96, getWidth(), getHeight());
			rightEnemy[i] = Game.spritesheet.getSprite(i*16, 112, getWidth(), getHeight());
			leftEnemy[i] = Game.spritesheet.getSprite(i*16, 128, getWidth(), getHeight());
		}
		
	}
	
	// Update and render methods
	
	public void update() {
		moved = false;
		if(!this.isColidingWithPlayer()) {
			if(Math.abs(x - Game.player.getX()) <= width*fov &&
			   Math.abs(y - Game.player.getY()) <= height*fov &&
			   Game.rand.nextInt(100) < 75) {
				if((int)x < (int)Game.player.getX() && World.isFree((int)(getX() + speed), (int) getY()) &&
						!isColiding((int)(getX() + speed), (int)getY())) {
					x += speed;
					dir = rightDir;
					moved = true;
				}else if((int)x > (int)Game.player.getX() && World.isFree((int)(getX() - speed), (int) getY()) &&
						!isColiding((int)(getX() - speed), (int)getY())) {
					x -= speed;
					dir = leftDir;
					moved = true;
				}else if((int)y < (int)Game.player.getY() && World.isFree((int)getX(), (int) (getY() + speed)) &&
						!isColiding((int)getX(), (int)(getY() + speed))) {
					y += speed;
					dir = downDir;
					moved = true;
				}else if((int)y > (int)Game.player.getY() && World.isFree((int)getX(), (int) (getY() - speed)) &&
						!isColiding((int)getX(), (int)(getY() - speed))) {
					y -= speed;
					dir = upDir;
					moved = true;
				}
			}else {
				int randDir;
				
				randDir = Game.rand.nextInt(4);
				
				if(Game.rand.nextInt(100) < 5) {
					switch(randDir) {
						case upDir:
							dir = upDir;
							y -= speed;
							break;
						case downDir:
							dir = downDir;
							y += speed;
							break;
						case rightDir:
							dir = rightDir;
							x += speed;
							break;
						case leftDir:
							dir = leftDir;
							x -= speed;
							break;
							
					}
				}
			}
		}else {
			// Attacking player
			Game.player.setKbDir(dir);
			if(Game.rand.nextInt(100) < 10) {
				Game.player.setLife(Game.player.getLife() - 1);
				Game.player.isDamaged = true;
			}
		}
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
			}
		}
		
		colidingBullet();
		
		if(this.life <= 0) {
			selfDestruction();
		}
		
	}
	
	public void render(Graphics g) {
		if(dir == upDir) {
			g.drawImage(upEnemy[index],  (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
		}else if(dir == downDir) {
			g.drawImage(downEnemy[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
		}else if(dir == rightDir) {
			g.drawImage(rightEnemy[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
		}else if(dir == leftDir) {
			g.drawImage(leftEnemy[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
		}
	}
	public void selfDestruction() {
		Game.entities.remove(this);
	}

	public void colidingBullet() {
		for(int i = 0; i < Game.fireballs.size(); i++) {
			Entity e = Game.fireballs.get(i);
			
			if(Entity.isColiding(this, e)) {
				this.life -= 5;
				Game.fireballs.remove(i);
				return;
			}
			
		}
		
		for(int i = 0; i < Game.luteFires.size(); i++) {
			Entity e = Game.luteFires.get(i);
			
			if(Entity.isColiding(this, e)) {
				this.life -= 1;
				Game.luteFires.remove(i);
				return;
			}
			
		}
		
	}
	
	
	public boolean isColidingWithPlayer() {
		
		Rectangle enemyCurrent = new Rectangle((int)(getX() + maskx), (int)(getY() + masky), maskw, maskh);
		Rectangle player = new Rectangle((int)Game.player.getX(), (int)Game.player.getY(), 16, 16);
		
		return enemyCurrent.intersects(player);
	}
	
	
	public boolean isColiding(int xx, int yy) {
		Rectangle enemyCurrent = new Rectangle(xx + maskx, yy + masky, maskw, maskh);
		
		for(int i = 0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
			if(e == this) {
				continue;
			}
			Rectangle enemyTarget = new Rectangle((int)(e.getX() + maskx), (int)(e.getY() + masky), maskw, maskh);
			if(enemyCurrent.intersects(enemyTarget)){
				return true;
			}
		}

		return false;
	}
	
	// Getter and Setters methods
	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

}
