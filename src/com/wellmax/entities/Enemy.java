package com.wellmax.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.melqjpgames.entities.types.Directions;
import com.melqjpgames.main.Game;
import com.melqjpgames.world.Camera;
import com.melqjpgames.world.World;

public class Enemy extends Entity{

	//---------------------------- Attributes ----------------------------------//	

	// Sprites
	/**
	 * Sprites of enemy facing right
	 */
	protected BufferedImage[] rightEnemy;
	/**
	 * Sprites of enemy facing left
	 */
	protected BufferedImage[] leftEnemy;
	/**
	 * Sprites of enemy facing up
	 */
	protected BufferedImage[] upEnemy;
	/**
	 * Sprites of enemy facing down
	 */
	protected BufferedImage[] downEnemy;
	/**
	 * Sprite of enemy taking damage facing right
	 */
	protected BufferedImage enemyDamageRight;
	/**
	 * Sprite of enemy taking damage facing left
	 */
	protected BufferedImage enemyDamageLeft;
	/**
	 * Sprite of enemy taking damage facing up
	 */
	protected BufferedImage enemyDamageUp;
	/**
	 * Sprite of enemy taking damage facing down
	 */
	protected BufferedImage enemyDamageDown;
	
	/**
	 * Fog of view
	 */
	protected int fov;
	/**
	 * Flag that indicates if enemy is dying
	 */
	protected boolean dying;
	
	//---------------------------- Methods ----------------------------------//	
	
	/*
	 * Constructor receives the position and size of player
	 */
	public Enemy(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		this.setSpeed(1);
		this.setFaceDir(Directions.DOWN); 
		this.setLife(10);
		this.setFov(5);
		
		// Initiates sprites
		this.rightEnemy = new BufferedImage[this.getnOfSprites()];
		this.leftEnemy = new BufferedImage[this.getnOfSprites()];
		this.upEnemy = new BufferedImage[this.getnOfSprites()];
		this.downEnemy = new BufferedImage[this.getnOfSprites()];

		this.enemyDamageRight = Game.spritesheet.getSprite(64, 112, this.getWidth(), this.getHeight());
		this.enemyDamageLeft = Game.spritesheet.getSprite(64, 128, this.getWidth(), this.getHeight());
		this.enemyDamageUp = Game.spritesheet.getSprite(64, 80, this.getWidth(), this.getHeight());
		this.enemyDamageDown = Game.spritesheet.getSprite(64, 96, this.getWidth(), this.getHeight());

		for(int i = 0; i < this.getnOfSprites(); i++) {
			this.rightEnemy[i] = Game.spritesheet.getSprite(i*16, 112, this.getWidth(), this.getHeight());
			this.leftEnemy[i] = Game.spritesheet.getSprite(i*16, 128, this.getWidth(), this.getHeight());
			this.upEnemy[i] = Game.spritesheet.getSprite(i*16, 80, this.getWidth(), this.getHeight());
			this.downEnemy[i] = Game.spritesheet.getSprite(i*16, 96, this.getWidth(), this.getHeight());

		}
		
	}
	
	
	/**
	 * @return the fov
	 */
	public int getFov() {
		return fov;
	}


	/**
	 * @param fov the fov to set
	 */
	public void setFov(int fov) {
		this.fov = fov;
	}

	/**
	 * @return the dying
	 */
	public boolean isDying() {
		return dying;
	}

	/**
	 * @param dying the dying to set
	 */
	public void setDying(boolean dying) {
		this.dying = dying;
	}

	/**
	 * Method to check if the player is near enemy
	 * @return true if player is inside the fov of enemy and the player is alive
	 */
	private boolean nearPlayer() {
		
		boolean xAxisNear = (Math.abs(this.getX() - Game.player.getX()) <= this.getWidth()*this.getFov());
		boolean yAxisNear = (Math.abs(this.getY() - Game.player.getY()) <= this.getHeight()*this.getFov()_;
		boolean movingChance = (Game.rand.nextInt(100) < 75);
		boolean playerAlive = (Game.player.getLife() > 0);
		
		return (xAxisNear && yAxisNear && movingChance && playerAlive);
	}
	
	/**
	 * Method to execute enemy IA
	 */
	protected void enemyIA() {
		
	}
	
	
	public void update() {
		
		this.setMoving(false);
		
		if(!this.isColidingWithPlayer() || Game.player.getLife() <= 0) {
			if(nearPlayer()) {
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
							if(World.isFree((int)getX(), (int) (getY() - speed)) &&
									!isColiding((int)getX(), (int)(getY() - speed))) {
								y -= speed;
							}
							
							break;
						case downDir:
							dir = downDir;
							if(World.isFree((int)getX(), (int) (getY() + speed)) &&
									!isColiding((int)getX(), (int)(getY() + speed))) {
								y += speed;
							}
							break;
						case rightDir:
							dir = rightDir;
							if(World.isFree((int)(getX() + speed), (int) getY()) &&
									!isColiding((int)(getX() + speed), (int)getY())) {
								x += speed;	
							}
							break;
						case leftDir:
							dir = leftDir;
							if(World.isFree((int)(getX() - speed), (int) getY()) &&
									!isColiding((int)(getX() - speed), (int)getY())) {
								x -= speed;	
							}
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
		
		if(isDamaged) {
			if(getKbDir() == rightDir && World.isFree((int)(getX() + speed*kbSpeed), (int) getY()) &&
					!isColiding((int)(getX() + speed*kbSpeed), (int)getY())) {
				setX(getX() + speed*kbSpeed);
				moved = true;
			}else if(getKbDir() == leftDir && World.isFree((int)(getX() - speed*kbSpeed), (int) getY()) &&
					!isColiding((int)(getX() - speed*kbSpeed), (int)getY())) {
				setX(getX() - speed*kbSpeed);
				moved = true;
			}
			if(getKbDir() == upDir && World.isFree((int)getX(), (int) (getY() - speed*kbSpeed)) &&
					!isColiding((int)getX(), (int)(getY() - speed*kbSpeed))) {
				setY(getY() - speed*kbSpeed);
				moved = true;
			}else if(getKbDir() == downDir && World.isFree((int)getX(), (int) (getY() + speed*kbSpeed)) &&
					!isColiding((int)getX(), (int)(getY() + speed*kbSpeed))) {
				setY(getY() + speed*kbSpeed);
				moved = true;
			}
		}
		
		if(isDamaged) {
			damageFrames++;
			if(damageFrames ==8) {
				damageFrames = 0;
				isDamaged = false;
			}
		}
		
		this.countFrames(moved);
		
		colidingBullet();
		
		if(this.life <= 0) {
			selfDestruction();
		}
		
	}
	
	public void render(Graphics g) {
		
		if(!isDamaged) {
			if(dir == upDir) {
				g.drawImage(upEnemy[index],  (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
			}else if(dir == downDir) {
				g.drawImage(downEnemy[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
			}else if(dir == rightDir) {
				g.drawImage(rightEnemy[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
			}else if(dir == leftDir) {
				g.drawImage(leftEnemy[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
			}
		}else {
			if(dir == upDir) {
				g.drawImage(enemyDamageUp, (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
			}else if(dir == downDir) {
				g.drawImage(enemyDamageDown, (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
			}else if(dir == rightDir) {
				g.drawImage(enemyDamageRight, (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
			}else if(dir == leftDir) {
				g.drawImage(enemyDamageLeft,  (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
			}

		}
	}
	public void selfDestruction() {
		Game.deadEnemies.add(new EnemyDied((int)getX(), (int)getY(), getWidth(), getHeight(), dir));
		Game.entities.remove(this);
		Game.enemies.remove(this);
	}

	public void colidingBullet() {
		for(int i = 0; i < Game.fireballs.size(); i++) {
			FireballShoot e = Game.fireballs.get(i);
			
			if(GenericEntity.isColliding(this, e)) {
				this.kbDir = e.getDir();
				this.kbSpeed = 3;
				this.isDamaged = true;
				this.life -= 5;
				Game.fireballs.remove(i);
				return;
			}
			
		}
		
		for(int i = 0; i < Game.luteFires.size(); i++) {
			LuteFire e = Game.luteFires.get(i);
			
			if(GenericEntity.isColliding(this, e)) {
				this.isDamaged = true;
				this.kbDir = e.getDir();
				this.kbSpeed = 1;
				this.life -= 2;
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
	

}
