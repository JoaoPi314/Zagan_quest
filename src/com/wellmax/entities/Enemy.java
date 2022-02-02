package com.wellmax.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.wellmax.entities.types.Directions;
import com.wellmax.entities.types.Utils;
import com.wellmax.main.Game;
import com.wellmax.world.Camera;
import com.wellmax.world.World;

public class Enemy extends Entity{

	//---------------------------- Attributes ----------------------------------//	
	
	/**
	 * Fog of view
	 */
	protected int fov;
	/**
	 * Flag that indicates if enemy is dying
	 */
	protected boolean dying;
	
	/**
	 * Damage dealed by enemy
	 */
	protected double damage;
	 
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
	 * @return the damage
	 */
	public double getDamage() {
		return damage;
	}


	/**
	 * @param damage the damage to set
	 */
	public void setDamage(double damage) {
		this.damage = damage;
	}


	/**
	 * Method to check if the player is near enemy
	 * @return true if player is inside the fov of enemy and the player is alive
	 */
	private boolean nearPlayer() {
		
		boolean xAxisNear = (Math.abs(this.getX() - Game.player.getX()) <= this.getWidth()*this.getFov());
		boolean yAxisNear = (Math.abs(this.getY() - Game.player.getY()) <= this.getHeight()*this.getFov());
		boolean movingChance = (Game.rand.nextInt(100) < 75);
		boolean playerAlive = (Game.player.getLife() > 0);
		
		return (xAxisNear && yAxisNear && movingChance && playerAlive);
	}
	
	/**
	 * Method to check if the enemy will collide with another enemies
	 * @param xx Next x position
	 * @param yy Next y position
	 * @return true if enemy collides with another enemy
	 */

	public boolean isCollidingWithEnemies(int xx, int yy) {
		Rectangle enemyCurrent = new Rectangle(xx + this.getMaskX(), yy + this.getMaskY(), this.getmWidth(), this.getmHeight());
		
		for(int i = 0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
			if(e == this) {
				continue;
			}
			Rectangle enemyTarget = new Rectangle((int)(e.getX() + this.getMaskX()), (int)(e.getY() +  this.getMaskY()), this.getmWidth(), this.getmHeight());
			if(enemyCurrent.intersects(enemyTarget)){
				return true;
			}
		}

		return false;
	}

	
	/**
	 * Method to execute enemy IA
	 */
	protected void enemyIA() {
		
		if(!GenericEntity.isColliding(this, Game.player) || Game.player.getLife() <= 0) {
			if(this.nearPlayer()) { // Enemy is near player
				// Enemy to the player's left - Moves right
				if((int)this.getX() < (int)Game.player.getX() && World.isFree((int)(this.getX() + this.getSpeed()), (int) this.getY()) &&
						!this.isCollidingWithEnemies((int)(this.getX() + this.getSpeed()), (int)this.getY())) {
					this.setX(this.getX() + this.getSpeed());
					this.setFaceDir(Directions.RIGHT);
					this.setMoving(true);
				// Enemy to the player's right - Moves left
				}else if((int)this.getX() > (int)Game.player.getX() && World.isFree((int)(this.getX() - this.getSpeed()), (int) this.getY()) &&
						!this.isCollidingWithEnemies((int)(this.getX() - this.getSpeed()), (int)this.getY())) {
					this.setX(this.getX() - this.getSpeed());
					this.setFaceDir(Directions.LEFT);
					this.setMoving(true);
				// Enemy above player - Moves down
				}else if((int)this.getY() < (int)Game.player.getY() && World.isFree((int)this.getX(), (int) (this.getY() + this.getSpeed())) &&
						!this.isCollidingWithEnemies((int)this.getX(), (int)(this.getY() + this.getSpeed()))) {
					this.setY(this.getY() + this.getSpeed());
					this.setFaceDir(Directions.DOWN);
					this.setMoving(true);
				// Enemy below player - Moves up
				}else if((int)this.getY() > (int)Game.player.getY() && World.isFree((int)this.getX(), (int) (this.getY() - this.getSpeed())) &&
						!this.isCollidingWithEnemies((int)this.getX(), (int)(this.getY() - this.getSpeed()))) {
					this.setY(this.getY() - this.getSpeed());
					this.setFaceDir(Directions.UP);
					this.setMoving(true);
				}
			}else { // Enemy is idle - Moves randomly and slowly
				Directions randDir;
				randDir = Utils.randomEnum(Directions.class);
				
				// Enemy has a chance of 5% to move
				if(Game.rand.nextInt(100) < 5) {
					switch(randDir) {
						case RIGHT:
							this.setFaceDir(Directions.RIGHT);
							if(World.isFree((int)(this.getX() + this.getSpeed()), (int) this.getY()) &&
									!this.isCollidingWithEnemies((int)(this.getX() + this.getSpeed()), (int)this.getY())) {
								this.setX(this.getX() + this.getSpeed());
							}
							break;
						case LEFT:
							this.setFaceDir(Directions.LEFT);
							if(World.isFree((int)(this.getX() - this.getSpeed()), (int) this.getY()) &&
									!this.isCollidingWithEnemies((int)(this.getX() - this.getSpeed()), (int)this.getY())) {
								this.setX(this.getX() - this.getSpeed());	
							}
							break;
						case UP:
							this.setFaceDir(Directions.UP);
							if(World.isFree((int)this.getX(), (int) (this.getY() - this.getSpeed())) &&
									!this.isCollidingWithEnemies((int)this.getX(), (int)(this.getY() - this.getSpeed()))) {
								this.setY(this.getY() - this.getSpeed());	
							}
							break;
						case DOWN: // Default movement is down
						default:
							this.setFaceDir(Directions.DOWN);
							if(World.isFree((int)this.getX(), (int) (this.getY() + this.getSpeed())) &&
									!this.isCollidingWithEnemies((int)this.getX(), (int)(this.getY() + this.getSpeed()))) {
								this.setY(this.getY() + this.getSpeed());	
							}
							break;
							
					}
				}
			}
		}else if(Game.player.getLife() > 0) { // If enemy is colliding with player, and player is alive
			// Attacking player
			if(Game.rand.nextInt(100) < 5) { // When in contact, enemy has a chance of 5% to deal damage
				Game.player.setLife(Game.player.getLife() - this.getDamage()/Game.player.getDefense());
				Game.player.setKbDir(this.getFaceDir());
				Game.player.setKbspeed(3);
				Game.player.setDamaged(true);
			}
		}
		
	}
	/**
	 * Method to calculate knockback movement
	 */
	protected void damageMovement() {
		if(this.isDamaged()) {
			switch(this.getKbDir()) {
				case RIGHT:
					if(World.isFree((int)(this.getX() + this.getSpeed()*this.getKbSpeed()), (int) this.getY()) &&
							!this.isCollidingWithEnemies((int)(this.getX() + this.getSpeed()), (int)this.getY())) {
						this.setX(this.getX() + this.getSpeed()*this.getKbSpeed());
						this.setMoving(true);
					}
					break;
				case LEFT:
					if(World.isFree((int)(this.getX() - this.getSpeed()*this.getKbSpeed()), (int) this.getY()) &&
							!this.isCollidingWithEnemies((int)(this.getX() - this.getSpeed()), (int)this.getY())) {
						this.setX(this.getX() - this.getSpeed()*this.getKbSpeed());
						this.setMoving(true);
					}
					break;
				case UP:
					if(World.isFree((int)this.getX(), (int) (this.getY() - this.getSpeed()*this.getKbSpeed()))&&
							!this.isCollidingWithEnemies((int)this.getX(), (int)(this.getY() - this.getSpeed()))) {
						this.setY(this.getY() - this.getSpeed()*this.getKbSpeed());
						this.setMoving(true);						
					}
					break;
				case DOWN:
					if(World.isFree((int)this.getX(), (int) (this.getY() + this.getSpeed()*this.getKbSpeed())) &&
							!this.isCollidingWithEnemies((int)this.getX(), (int)(this.getY() + this.getSpeed()))) {
						this.setY(this.getY() + this.getSpeed()*this.getKbSpeed());
						this.setMoving(true);						
					}
					break;
				default:
					break;
			
			}
			
			// Sprite damage calculus
			damageFrames++;
			if(damageFrames ==8) {
				damageFrames = 0;
				setDamaged(false);
			}	
		}
	}
	
	/**
	 * Method to compute if a projectile hits enemy
	 */
	protected void collidingProjectile() {
		// Search for fireballs
		for(int i = 0; i < Game.fireballs.size(); i++) {
			FireballShoot e = Game.fireballs.get(i);
			
			if(GenericEntity.isColliding(this, e)) {
				this.setKbDir(e.getFaceDir());
				this.setKbspeed(3);
				this.setDamaged(true);
				this.setLife(this.getLife() - 5/this.getDefense());
				Game.fireballs.remove(i);
				return;
			}
			
		}
		// Search for LuteFires
		for(int i = 0; i < Game.luteFires.size(); i++) {
			LuteFire e = Game.luteFires.get(i);
			
			if(GenericEntity.isColliding(this, e)) {
				this.setKbDir(e.getFaceDir());
				this.setKbspeed(1);
				this.setDamaged(true);
				this.setLife(this.getLife() - 2/this.getDefense());
				Game.luteFires.remove(i);
				return;
			}
			
		}
		
	}
	
	/**
	 * Method called when enemy dies
	 */
	protected void enemyDeath() {
		Game.deadEnemies.add(new DeadEnemy((int)getX(), (int)getY(), getWidth(), getHeight(), this.getFaceDir()));
		Game.enemies.remove(this);
	}

	
	
	public void update() {
		
		this.setMoving(false);
		
		this.enemyIA();
		
		this.damageMovement();
		
		this.countFrames(this.isMoving());
		
		this.collidingProjectile();
		
		if(this.life <= 0) {
			this.enemyDeath();
		}
		
	}
}
	
