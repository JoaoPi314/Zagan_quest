package com.melqjpgames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.melqjpgames.entities.types.Directions;
import com.melqjpgames.main.Game;
import com.melqjpgames.world.Camera;
import com.melqjpgames.world.World;

public class Player extends Entity{

	//---------------------------- Attributes ----------------------------------//	

	/**
	 * Directions of player movement
	 */
	private boolean right, left, up, down;
	
	
	// Sprites
	/**
	 * Sprites of player facing right
	 */
	private BufferedImage[] rightPlayer;
	/**
	 * sprites of player facing left
	 */
	private BufferedImage[] leftPlayer;
	/**
	 * Sprites of player facing up
	 */
	private BufferedImage[] upPlayer;
	/**
	 * Sprites of player facing down
	 */
	private BufferedImage[] downPlayer;
	
	/**
	 * Sprite of player taking damage facing right
	 */
	private BufferedImage playerDamageRight;
	/**
	 * Sprite of player taking damage facing left
	 */
	private BufferedImage playerDamageLeft;
	/**
	 * Sprite of player taking damage facing up
	 */
	private BufferedImage playerDamageUp;
	/**
	 * Sprite of player taking damage facing down
	 */
	private BufferedImage playerDamageDown;

	/**
	 * Sprite of player dead
	 */
	private BufferedImage playerDead;
	
	/**
	 * Flag that indicates if player has fireball power
	 */
	private boolean hasFireball;
	/**
	 * Number of fireballs remaining
	 */
	private int fireballs;
	/**
	 * Flag that indicates if player is shooting
	 */
	private boolean shoot;
	/**
	 * Flag that indicates if player has shoot recently (to apply cooldown)
	 */
	private boolean coolDown;
	/**
	 * Cooldown frames (Cooldown calculus)
	 */
	private double framesCoolDown;
	/**
	 * Number of frames that cooldown must be applied (cooldown calculus)
	 */
	private double maxFramesCoolDown;
	
	//---------------------------- Methods ----------------------------------//	

	
	/**
	 * The constructor creates a player with the parameters
	 * @param x x position
	 * @param y y position
	 * @param width player width
	 * @param height player height
	 */
	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		this.setSpeed(0.9);
		this.setFaceDir(Directions.DOWN); 
		this.setLife(12);
		
		this.setHasFireball(false);
		this.setFireballs(0);
		
		// Initiates sprites
		this.rightPlayer = new BufferedImage[this.getnOfSprites()];
		this.leftPlayer = new BufferedImage[this.getnOfSprites()];
		this.upPlayer = new BufferedImage[this.getnOfSprites()];
		this.downPlayer = new BufferedImage[this.getnOfSprites()];

		this.playerDamageRight = Game.spritesheet.getSprite(64, 48, this.getWidth(), this.getHeight());
		this.playerDamageLeft = Game.spritesheet.getSprite(64, 64, this.getWidth(), this.getHeight());
		this.playerDamageUp = Game.spritesheet.getSprite(64, 16, this.getWidth(), this.getHeight());
		this.playerDamageDown = Game.spritesheet.getSprite(64, 32, this.getWidth(), this.getHeight());
		
		this.playerDead = Game.spritesheet.getSprite(16, 144, this.getWidth(), this.getHeight());
		
		for(int i = 0; i < this.getnOfSprites(); i++) {
			this.rightPlayer[i] = Game.spritesheet.getSprite(i*16, 48, this.getWidth(), this.getHeight());
			this.leftPlayer[i] = Game.spritesheet.getSprite(i*16, 64, this.getWidth(), this.getHeight());
			this.upPlayer[i] = Game.spritesheet.getSprite(i*16, 16, this.getWidth(), this.getHeight());
			this.downPlayer[i] = Game.spritesheet.getSprite(i*16, 32, this.getWidth(), this.getHeight());
		}
		
	}
	
	
	/**
	 * @return the right
	 */
	public boolean isRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(boolean right) {
		this.right = right;
	}

	/**
	 * @return the left
	 */
	public boolean isLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(boolean left) {
		this.left = left;
	}

	/**
	 * @return the up
	 */
	public boolean isUp() {
		return up;
	}

	/**
	 * @param up the up to set
	 */
	public void setUp(boolean up) {
		this.up = up;
	}

	/**
	 * @return the down
	 */
	public boolean isDown() {
		return down;
	}

	/**
	 * @param down the down to set
	 */
	public void setDown(boolean down) {
		this.down = down;
	}

	/**
	 * @return the hasFireball
	 */
	public boolean isHasFireball() {
		return hasFireball;
	}

	/**
	 * @param hasFireball the hasFireball to set
	 */
	public void setHasFireball(boolean hasFireball) {
		this.hasFireball = hasFireball;
	}

	/**
	 * @return the fireballs
	 */
	public int getFireballs() {
		return fireballs;
	}

	/**
	 * @param fireballs the fireballs to set
	 */
	public void setFireballs(int fireballs) {
		this.fireballs = fireballs;
	}

	/**
	 * @return the shoot
	 */
	public boolean isShoot() {
		return shoot;
	}

	/**
	 * @param shoot the shoot to set
	 */
	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}

	/**
	 * @return the coolDown
	 */
	public boolean isCoolDown() {
		return coolDown;
	}

	/**
	 * @param coolDown the coolDown to set
	 */
	public void setCoolDown(boolean coolDown) {
		this.coolDown = coolDown;
	}

	/**
	 * @return the framesCoolDown
	 */
	public double getFramesCoolDown() {
		return framesCoolDown;
	}

	/**
	 * @param framesCoolDown the framesCoolDown to set
	 */
	public void setFramesCoolDown(double framesCoolDown) {
		this.framesCoolDown = framesCoolDown;
	}

	/**
	 * @return the maxFramesCoolDown
	 */
	public double getMaxFramesCoolDown() {
		return maxFramesCoolDown;
	}

	/**
	 * @param maxFramesCoolDown the maxFramesCoolDown to set
	 */
	public void setMaxFramesCoolDown(double maxFramesCoolDown) {
		this.maxFramesCoolDown = maxFramesCoolDown;
	}

	/**
	 * Method to calculate player movement based on
	 * user inputs
	 */
	private void controlledMovement() {
		
		this.setMoving(false);
		
		if(this.isRight() && World.isFree((int)(this.getX() + this.getSpeed()), (int) this.getY())){
			this.setX(this.getX() + this.getSpeed());
			this.setFaceDir(Directions.RIGHT);
			this.setMoving(true);
		}else if(this.isLeft() && World.isFree((int)(this.getX() - this.getSpeed()), (int) this.getY())) {
			this.setX(this.getX() - this.getSpeed());
			this.setFaceDir(Directions.LEFT);
			this.setMoving(true);
		}
		if(this.isUp() && World.isFree((int)this.getX(), (int) (this.getY() - this.getSpeed()))) {
			this.setY(this.getY() - this.getSpeed());
			this.setFaceDir(Directions.UP);
			this.setMoving(true);
		}else if(this.isDown() && World.isFree((int)this.getX(), (int) (this.getY() + this.getSpeed()))) {
			this.setY(this.getY() + this.getSpeed());
			this.setFaceDir(Directions.DOWN);
			this.setMoving(true);
		}
		
	}

	/**
	 * Method to calculate player movement when it is
	 * taking damage
	 */
	private void damageMovement() {
		if(this.isDamaged()) {
			
			switch(this.getKbDir()) {
				case RIGHT:
					if(World.isFree((int)(this.getX() + this.getSpeed()*this.getKbSpeed()), (int) this.getY())) {
						this.setX(this.getX() + this.getSpeed()*this.getKbSpeed());
						this.setMoving(true);
					}
					break;
				case LEFT:
					if(World.isFree((int)(this.getX() - this.getSpeed()*this.getKbSpeed()), (int) this.getY())) {
						this.setX(this.getX() - this.getSpeed()*this.getKbSpeed());
						this.setMoving(true);
					}
					break;
				case UP:
					if(World.isFree((int)this.getX(), (int) (this.getY() - this.getSpeed()*this.getKbSpeed()))) {
						this.setY(this.getY() - this.getSpeed()*this.getKbSpeed());
						this.setMoving(true);						
					}
					break;
				case DOWN:
					if(World.isFree((int)this.getX(), (int) (this.getY() + this.getSpeed()*this.getKbSpeed()))) {
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
	 * Method to check collision of player with collectibles
	 */
	public void checkItens() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity en = Game.entities.get(i);
			if(en instanceof HealthPotion) {
				if(GenericEntity.isColliding(this, en)) {
					setLife(getLife() + 6);
					if(getLife() > 12)
						setLife(12);
					Game.entities.remove(en);
					
				}
			}else if(en instanceof Fireball) {
				if(GenericEntity.isColliding(this, en)) {
					setHasFireball(true);
					setFireballs(getFireballs() + 5);
					Game.entities.remove(en);
				}
				
			}
		}
	}
	
	/**
	 * Method to compute shooting logic
	 */
	private void shooting() {
		if(this.isShoot() && !this.isCoolDown()) {
			// FIRE
			this.setShoot(false);
			int dx = 0;
			int dy = 0;
			
			switch(this.getFaceDir()) {
				case RIGHT:
					dx = 1;
					break;
				case LEFT:
					dx = -1;
					break;
				case UP:
					dy = -1;
					break;
				case DOWN:
					dy = 1;
					break;
				default:
					break;
			}
			
			// After shoot, player must wait a cooldown
			this.setCoolDown(true);
			
			// If player has fireball power and fireballs remaining
			if(this.isHasFireball() && this.getFireballs() > 0) {	
				FireballShoot fire = new FireballShoot((int)(this.getX()), (int)(this.getY()), this.getWidth(), this.getHeight(), this.getFaceDir(), dx, dy);
				Game.fireballs.add(fire);
				this.setFireballs(this.getFireballs() - 1);
				
				if(this.getFireballs() <= 0)
					this.setHasFireball(false);
				
				// Cooldown time of fireball
				this.setMaxFramesCoolDown(60);
			}else { // Default shoot is lute fire
				// Choose a random note sprite
				int randFire = Game.rand.nextInt(3);
				LuteFire fire = new LuteFire((int)(this.getX()), (int)(this.getY()), this.getWidth(), this.getHeight(), this.getFaceDir(), dx, dy, randFire);
				Game.luteFires.add(fire);
				
				// Cooldown time of lute fire
				this.setMaxFramesCoolDown(30);
			}
			// The initial frames is equal to max frames
			this.setFramesCoolDown(getMaxFramesCoolDown());
		}
		
		// Cooldown calculus
		if(this.isCoolDown()) {
			this.setFramesCoolDown(this.getFramesCoolDown() - 1);
			if(this.getFramesCoolDown() <= 0) {
				this.setCoolDown(false);
			}
		}
	}


	public void update() {
		
		// Keyboard movement
		this.controlledMovement();
		
		// Damage movement
		this.damageMovement();

		// Sprite update
		this.countFrames(moving);
		
		// Check collectibles
		this.checkItens();
	
		// Shooting update
		this.shooting();
		
		// Makes camera follow player
		Camera.x = Camera.clamp((int)getX() - (Game.WIDTH / 2), 0, World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp((int)getY() - (Game.HEIGHT / 2), 0, World.HEIGHT*16 - Game.HEIGHT);
		
	}


	public void render(Graphics g) {
		
		BufferedImage currentSprite;
		
		switch(this.getFaceDir()) {
			case RIGHT:
				if(!this.isDamaged()) {
					currentSprite = this.rightPlayer[index];
				}else {
					currentSprite = this.playerDamageRight;
				}
				break;
			case LEFT:
				if(!this.isDamaged()) {
					currentSprite = this.leftPlayer[index];
				}else {
					currentSprite = this.playerDamageLeft;
				}
				break;
			case UP:
				if(!this.isDamaged()) {
					currentSprite = this.upPlayer[index];
				}else {
					currentSprite = this.playerDamageUp;
				}
				break;
			case DOWN: // If DOWN or NONE, sprite down is rendered	
			default:
				if(!this.isDamaged()) {
					currentSprite = this.downPlayer[index];
				}else {
					currentSprite = this.playerDamageDown;
				}
				break;
		}
		
		g.drawImage(currentSprite, (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);

	}
	
	/**
	 * Method to render sprite of player dead
	 * @param g Graphics to render sprite
	 */
	public void renderDead(Graphics g) {
		g.drawImage(playerDead, (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
	}
	
	
}
