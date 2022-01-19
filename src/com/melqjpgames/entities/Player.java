package com.melqjpgames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.melqjpgames.main.Game;
import com.melqjpgames.world.Camera;
import com.melqjpgames.world.World;

public class Player extends Entity{

	// Directions
	private boolean right, up, left, down;
	private double speed;
	private int upDir = 0, downDir = 1, rightDir = 2, leftDir = 3;
	private int dir;
	private boolean moved;
	// Sprites
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private int nOfSprites;
	
	private BufferedImage playerDamageUp;
	private BufferedImage playerDamageDown;
	private BufferedImage playerDamageRight;
	private BufferedImage playerDamageLeft;
	
	// Animation of sprite
	private int frames;
	private final int maxFrames = 10;
	private int index;
	private final int maxIndex = 3;
	
	private double life;
	public static double maxLife = 12;
	private boolean looseLife;
	private boolean hasFireball;
	private int fireballs;
	
	public boolean isDamaged = false;
	private int damageFrames = 0;
	private int kbSpeed = 3;
	public static int kbDir;
	
	/*
	 * Constructor receives the position and size of player
	 */
	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 0.9;
		frames = 0;
		index = 0;
		nOfSprites = 4;
		dir = downDir;
		life = 12;
		setHasFireball(false);
		setFireballs(0);
		// Initiates sprites
		upPlayer = new BufferedImage[nOfSprites];
		downPlayer = new BufferedImage[nOfSprites];
		rightPlayer = new BufferedImage[nOfSprites];
		leftPlayer = new BufferedImage[nOfSprites];
		
		playerDamageUp = Game.spritesheet.getSprite(64, 16, 16, 16);
		playerDamageDown = Game.spritesheet.getSprite(64, 32, 16, 16);
		playerDamageRight = Game.spritesheet.getSprite(64, 48, 16, 16);
		playerDamageLeft = Game.spritesheet.getSprite(64, 64, 16, 16);
		
		for(int i = 0; i < nOfSprites; i++) {
			upPlayer[i] = Game.spritesheet.getSprite(i*16, 16, getWidth(), getHeight());
			downPlayer[i] = Game.spritesheet.getSprite(i*16, 32, getWidth(), getHeight());
			rightPlayer[i] = Game.spritesheet.getSprite(i*16, 48, getWidth(), getHeight());
			leftPlayer[i] = Game.spritesheet.getSprite(i*16, 64, getWidth(), getHeight());
		}
		
	}
	
	// Update and render methods
	
	public void update() {
		moved = false;
		if(isRight() && World.isFree((int)(getX() + speed), (int) getY())) {
			setX(getX() + speed);
			dir = rightDir;
			moved = true;
		}else if(isLeft() && World.isFree((int)(getX() - speed), (int) getY())) {
			setX(getX() - speed);
			dir = leftDir;
			moved = true;
		}
		if(isUp() && World.isFree((int)getX(), (int) (getY() - speed))) {
			setY(getY() - speed);
			dir = upDir;
			moved = true;
		}else if(isDown() && World.isFree((int)getX(), (int) (getY() + speed))) {
			setY(getY() + speed);
			dir = downDir;
			moved = true;
		}
		// By now, always moving
		if(isDamaged) {
			if(kbDir == rightDir && World.isFree((int)(getX() + speed*kbSpeed), (int) getY())) {
				setX(getX() + speed*kbSpeed);
				moved = true;
			}else if(kbDir == leftDir && World.isFree((int)(getX() - speed*kbSpeed), (int) getY())) {
				setX(getX() - speed*kbSpeed);
				moved = true;
			}
			if(kbDir == upDir && World.isFree((int)getX(), (int) (getY() - speed*kbSpeed))) {
				setY(getY() - speed*kbSpeed);
				moved = true;
			}else if(kbDir == downDir && World.isFree((int)getX(), (int) (getY() + speed*kbSpeed))) {
				setY(getY() + speed*kbSpeed);
				moved = true;
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
		checkItens();
		
		if(isDamaged) {
			damageFrames++;
			if(damageFrames ==8) {
				damageFrames = 0;
				isDamaged = false;
			}
		}
		
		if(life <= 0) {
			
		}
		
		// Makes camera follow player
		Camera.x = Camera.clamp((int)getX() - (Game.WIDTH / 2), 0, World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp((int)getY() - (Game.HEIGHT / 2), 0, World.HEIGHT*16 - Game.HEIGHT);
		
	}

	public void checkItens() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity en = Game.entities.get(i);
			if(en instanceof HealthPotion) {
				if(Entity.isColiding(this, en)) {
					setLife(getLife() + 6);
					if(getLife() > 12)
						setLife(12);
					Game.entities.remove(en);
					
				}
			}else if(en instanceof Fireball) {
				if(Entity.isColiding(this, en)) {
					setHasFireball(true);
					setFireballs(getFireballs() + 5);
					Game.entities.remove(en);
				}
				
			}
		}
	}
	
	
	public void render(Graphics g) {
		if(!isDamaged) {
			if(dir == upDir) {
				g.drawImage(upPlayer[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
			}else if(dir == downDir) {
				g.drawImage(downPlayer[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
			}else if(dir == rightDir) {
				g.drawImage(rightPlayer[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
			}else if(dir == leftDir) {
				g.drawImage(leftPlayer[index],  (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
			}
		}else {
			if(dir == upDir) {
				g.drawImage(playerDamageUp, (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
			}else if(dir == downDir) {
				g.drawImage(playerDamageDown, (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
			}else if(dir == rightDir) {
				g.drawImage(playerDamageRight, (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
			}else if(dir == leftDir) {
				g.drawImage(playerDamageLeft,  (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
			}
		}
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

	public double getLife() {
		return life;
	}
	
	public void setLife(double life) {
		this.life = life;
	}

	public boolean isHasFireball() {
		return hasFireball;
	}

	public void setHasFireball(boolean hasFireball) {
		this.hasFireball = hasFireball;
	}

	public int getFireballs() {
		return fireballs;
	}

	public void setFireballs(int fireballs) {
		this.fireballs = fireballs;
	}
	
}
