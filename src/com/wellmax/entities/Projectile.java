package com.wellmax.entities;

import java.awt.Graphics;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;
import com.wellmax.world.World;

/**
 * The projectiles are all mechanisms of deal damage at distance
 * @author joao.gomes
 *
 */
public class Projectile extends GenericEntity{
	
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
	 * KnockBack speed dealed by projectile
	 */
	protected int kbDealed;
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

	/**
	 * @return the faceDir
	 */
	public Directions getFaceDir() {
		return faceDir;
	}

	/**
	 * @param faceDir the faceDir to set
	 */
	public void setFaceDir(Directions faceDir) {
		this.faceDir = faceDir;
	}
	
	/**
	 * @return the dx
	 */
	public int getDx() {
		return dx;
	}

	/**
	 * @param dx the dx to set
	 */
	public void setDx(int dx) {
		this.dx = dx;
	}

	/**
	 * @return the dy
	 */
	public int getDy() {
		return dy;
	}

	/**
	 * @param dy the dy to set
	 */
	public void setDy(int dy) {
		this.dy = dy;
	}

	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
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
	 * @return the kbDealed
	 */
	public int getKbDealed() {
		return kbDealed;
	}

	/**
	 * @param kbDealed the kbDealed to set
	 */
	public void setKbDealed(int kbDealed) {
		this.kbDealed = kbDealed;
	}

	/**
	 * @return the timeRemain
	 */
	public int getTimeRemain() {
		return timeRemain;
	}

	/**
	 * @param timeRemain the timeRemain to set
	 */
	public void setTimeRemain(int timeRemain) {
		this.timeRemain = timeRemain;
	}

	/**
	 * @return the totalTime
	 */
	public int getTotalTime() {
		return totalTime;
	}

	/**
	 * @param totalTime the totalTime to set
	 */
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
			return;
		}
	}
	


	@Override
	public void update() {
		if(World.isFree((int)(this.getX() + this.getDx()*this.getSpeed()),(int)(this.getY() + this.getDy()*this.getSpeed())) &&
				!this.isCollidingWithScenario((int)(this.getX() + this.getDx()*this.getSpeed()),(int)(this.getY() + this.getDy()*this.getSpeed()))){
			
			this.setX(this.getX() + this.getDx()*this.getSpeed());
			this.setY(this.getY() + this.getDy()*this.getSpeed());
		}else {
			Game.projectiles.remove(this);
		}

		this.countFrames(true);
		
		this.projectileLifeTime();
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
