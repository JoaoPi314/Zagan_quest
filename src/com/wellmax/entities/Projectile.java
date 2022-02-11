package com.wellmax.entities;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;
import com.wellmax.world.World;

/**
 * The projectiles are all mechanisms of deal damage at distance
 * @author joao.gomes
 *
 */
public abstract class Projectile extends Entity {
	
	//---------------------------- Attributes ----------------------------------//	
	/**
	 * Direction of shoot
	 */
	Directions faceDir;
	/**
	 * x axis multiplier
	 */
	protected int dx;
	/**
	 * y axis multiplier
	 */
	protected int dy;
	/**
	 * Projectile speed
	 */
	protected double speed;
	/**
	 * Projectile damage
	 */
	protected double damage;
	/**
	 * KnockBack speed dealt by projectile
	 */
	protected int knockBackDealt;
	/**
	 * Screen time remaining of projectile
	 */
	protected int timeRemain;
	/**
	 * Total screen time of projectile
	 */
	protected int totalTime;
	
	//---------------------------- Methods ----------------------------------//	
	
	/**
	 * Projectile constructor
	 * @param x x position
	 * @param y y position
	 * @param width projectile width
	 * @param height projectile height
	 * @param dx x axis multiplier 
	 * @param dy y axis multiplier
	 */
	public Projectile(int x, int y, int width, int height, int dx, int dy) {
		super(x, y, width, height);
		this.setDx(dx);
		this.setDy(dy);
	}

	public Directions getFaceDir() {
		return faceDir;
	}

	public void setFaceDir(Directions faceDir) {
		this.faceDir = faceDir;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public int getKnockBackDealt() {
		return knockBackDealt;
	}

	public void setKnockBackDealt(int knockBackDealt) {
		this.knockBackDealt = knockBackDealt;
	}

	public int getTimeRemain() {
		return timeRemain;
	}

	public void setTimeRemain(int timeRemain) {
		this.timeRemain = timeRemain;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	/**
	 * Method to calculate the screen time left of projectile
	 */
	protected void projectileLifeTime() {
		this.setTimeRemain(this.getTimeRemain() + 1);
		if(this.getTimeRemain() >= this.getTotalTime()) {
			Game.projectiles.remove(this);
		}
	}

	@Override
	public void update() {
		if(World.isFree((int)(this.getX() + this.getDx()*this.getSpeed()),
				(int)(this.getY() + this.getDy()*this.getSpeed())) &&
				this.isNotCollidingWithScenario((int) (this.getX() + this.getDx() * this.getSpeed()),
						(int) (this.getY() + this.getDy() * this.getSpeed()))){
			
			this.setX(this.getX() + this.getDx()*this.getSpeed());
			this.setY(this.getY() + this.getDy()*this.getSpeed());
		}else {
			Game.projectiles.remove(this);
		}

		this.countFrames(true);
		
		this.projectileLifeTime();
		
	}

}
