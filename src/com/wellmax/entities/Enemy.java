package com.wellmax.entities;

import java.awt.Rectangle;

import com.wellmax.entities.types.Directions;
import com.wellmax.entities.types.Utils;
import com.wellmax.main.Game;
import com.wellmax.world.World;

public abstract class Enemy extends Creature {

	//---------------------------- Attributes ----------------------------------//	
	
	/**
	 * Fog of view
	 */
	private int fov;
	/**
	 * Damage dealt by enemy
	 */
	private double damage;
	 
	//---------------------------- Methods ----------------------------------//
	/*
	 * Constructor receives the position and size of player
	 */
	public Enemy(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	public int getFov() {
		return fov;
	}

	public void setFov(int fov) {
		this.fov = fov;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	/**
	 * Method to check if the player is near enemy
	 * @return true if player is inside the fov of enemy and the player is alive
	 */
	protected boolean nearPlayer() {
		
		boolean xAxisNear = (Math.abs(this.getX() - Game.player.getX()) <= this.getWidth()*this.getFov());
		boolean yAxisNear = (Math.abs(this.getY() - Game.player.getY()) <= this.getHeight()*this.getFov());
		boolean movingChance = (Game.rand.nextInt(100) < 75);
		boolean playerAlive = (Game.player.getLife() > 0);
		
		return (xAxisNear && yAxisNear && movingChance && playerAlive);
	}

	/**
	 * Method to Idle movement
	 */
	protected void enemyIdleIA(){
		Directions randDir;
		randDir = Utils.randomEnum(Directions.class);

		// Enemy has a chance of 5% to move
		if(Game.rand.nextInt(100) < 5) {
			switch (randDir) {
				case RIGHT -> {
					this.setFaceDir(Directions.RIGHT);
					if (World.isFree((int) (this.getX() + this.getSpeed()), (int) this.getY()) &&
							this.isNotCollidingWithEnemies((int) (this.getX() + this.getSpeed()),
									(int) this.getY()) &&
							this.isNotCollidingWithScenario((int) (this.getX() + this.getSpeed()),
									(int) this.getY())) {
						this.setX(this.getX() + this.getSpeed());
					}
				}
				case LEFT -> {
					this.setFaceDir(Directions.LEFT);
					if (World.isFree((int) (this.getX() - this.getSpeed()), (int) this.getY()) &&
							this.isNotCollidingWithEnemies((int) (this.getX() - this.getSpeed()),
									(int) this.getY()) &&
							this.isNotCollidingWithScenario((int) (this.getX() - this.getSpeed()),
									(int) this.getY())) {
						this.setX(this.getX() - this.getSpeed());
					}
				}
				case UP -> {
					this.setFaceDir(Directions.UP);
					if (World.isFree((int) this.getX(), (int) (this.getY() - this.getSpeed())) &&
							this.isNotCollidingWithEnemies((int) this.getX(),
									(int) (this.getY() - this.getSpeed())) &&
							this.isNotCollidingWithScenario((int) this.getX(),
									(int) (this.getY() - this.getSpeed()))) {
						this.setY(this.getY() - this.getSpeed());
					}
				} // Default movement is down
				case DOWN-> {
					this.setFaceDir(Directions.DOWN);
					if (World.isFree((int) this.getX(), (int) (this.getY() + this.getSpeed())) &&
							this.isNotCollidingWithEnemies((int) this.getX(),
									(int) (this.getY() + this.getSpeed())) &&
							this.isNotCollidingWithScenario((int) this.getX(),
									(int) (this.getY() + this.getSpeed()))) {
						this.setY(this.getY() + this.getSpeed());
					}
				}
			}
		}
	}

	/**
	 * Method to calculate enemy movement when following player
	 */
	protected void enemyFollowingIA(){
		// Enemy to the player's left - Moves right
		if((int)this.getX() < (int)Game.player.getX() && World.isFree((int)(this.getX() + this.getSpeed()),
				(int) this.getY()) &&
				this.isNotCollidingWithEnemies((int) (this.getX() + this.getSpeed()), (int) this.getY()) &&
				this.isNotCollidingWithScenario((int) (this.getX() + this.getSpeed()), (int) this.getY())) {
			this.setX(this.getX() + this.getSpeed());
			this.setFaceDir(Directions.RIGHT);
			this.setMoving(true);
			// Enemy to the player's right - Moves left
		}else if((int)this.getX() > (int)Game.player.getX() && World.isFree((int)(this.getX() - this.getSpeed()),
				(int) this.getY()) &&
				this.isNotCollidingWithEnemies((int) (this.getX() - this.getSpeed()), (int) this.getY()) &&
				this.isNotCollidingWithScenario((int) (this.getX() - this.getSpeed()), (int) this.getY())) {
			this.setX(this.getX() - this.getSpeed());
			this.setFaceDir(Directions.LEFT);
			this.setMoving(true);
			// Enemy above player - Moves down
		}else if((int)this.getY() < (int)Game.player.getY() && World.isFree((int)this.getX(),
				(int) (this.getY() + this.getSpeed())) &&
				this.isNotCollidingWithEnemies((int) this.getX(), (int) (this.getY() + this.getSpeed())) &&
				this.isNotCollidingWithScenario((int) this.getX(), (int) (this.getY() + this.getSpeed()))) {
			this.setY(this.getY() + this.getSpeed());
			this.setFaceDir(Directions.DOWN);
			this.setMoving(true);
			// Enemy below player - Moves up
		}else if((int)this.getY() > (int)Game.player.getY() && World.isFree((int)this.getX(),
				(int) (this.getY() - this.getSpeed())) &&
				this.isNotCollidingWithEnemies((int) this.getX(), (int) (this.getY() - this.getSpeed())) &&
				this.isNotCollidingWithScenario((int) this.getX(), (int) (this.getY() - this.getSpeed()))) {
			this.setY(this.getY() - this.getSpeed());
			this.setFaceDir(Directions.UP);
			this.setMoving(true);
		}
	}
	/**
	 * Method to execute enemy IA
	 */
	protected void enemyIA() {

		if(!Entity.isColliding(this, Game.player) || Game.player.getLife() <= 0) {
			if(this.nearPlayer()) { // Enemy is near player
				this.enemyFollowingIA();
			}else{ // Enemy is idle - Moves randomly and slowly
				this.enemyIdleIA();
			}
		}else if(Game.player.getLife() > 0) { // If enemy is colliding with player, and player is alive
			// Attacking player
			if(!this.isCoolDown()) {
				this.attack();
			}
		}

	}
	/**
	 * Method to calculate knock-back movement
	 */
	protected void damageMovement() {
		if(this.isDamaged()) {
			switch(this.getKnockBackDir()) {
				case RIGHT:
					if(World.isFree((int)(this.getX() + this.getSpeed()*this.getKnockBackSpeed()),
							(int) this.getY()) &&
							this.isNotCollidingWithEnemies((int) (this.getX() + this.getKnockBackSpeed()),
									(int) this.getY()) &&
							this.isNotCollidingWithScenario((int) (this.getX() + this.getKnockBackSpeed()),
									(int) this.getY())) {
						this.setX(this.getX() + this.getSpeed()*this.getKnockBackSpeed());
						this.setMoving(true);
					}
					break;
				case LEFT:
					if(World.isFree((int)(this.getX() - this.getSpeed()*this.getKnockBackSpeed()),
							(int) this.getY()) &&
							this.isNotCollidingWithEnemies((int) (this.getX() - this.getKnockBackSpeed()),
									(int) this.getY()) &&
							this.isNotCollidingWithScenario((int) (this.getX() - this.getKnockBackSpeed()),
									(int) this.getY())) {
						this.setX(this.getX() - this.getSpeed()*this.getKnockBackSpeed());
						this.setMoving(true);
					}
					break;
				case UP:
					if(World.isFree((int)this.getX(), (int) (this.getY() - this.getSpeed()*this.getKnockBackSpeed()))&&
							this.isNotCollidingWithEnemies((int) this.getX(),
									(int) (this.getY() - this.getKnockBackSpeed())) &&
							this.isNotCollidingWithScenario((int) this.getX(),
									(int) (this.getY() - this.getKnockBackSpeed()))) {
						this.setY(this.getY() - this.getSpeed()*this.getKnockBackSpeed());
						this.setMoving(true);						
					}
					break;
				case DOWN:
					if(World.isFree((int)this.getX(), (int) (this.getY() + this.getSpeed()*this.getKnockBackSpeed())) &&
							this.isNotCollidingWithEnemies((int) this.getX(),
									(int) (this.getY() + this.getKnockBackSpeed())) &&
							this.isNotCollidingWithScenario((int) this.getX(),
									(int) (this.getY() + this.getKnockBackSpeed()))) {
						this.setY(this.getY() + this.getSpeed()*this.getKnockBackSpeed());
						this.setMoving(true);						
					}
					break;
				default:
					break;
			
			}
			
			// Sprite damage calculus
			this.setDamageFrames(this.getDamageFrames() + 1);
			if(this.getDamageFrames() == 8) {
				this.setDamageFrames(0);
				setDamaged(false);
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

	@Override
	public void update() {
		
		this.setMoving(false);
		
		this.enemyIA();

		this.coolDownCalculus();

		this.damageMovement();
		
		this.countFrames(this.isMoving());
		
		this.collidingProjectile();
		
		if(this.getLife() <= 0) {
			this.enemyDeath();
		}


		
	}
}
	
