package com.melqjpgames.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.melqjpgames.entities.Enemy;
import com.melqjpgames.entities.EnemyDied;
import com.melqjpgames.entities.Entity;
import com.melqjpgames.entities.FireballShoot;
import com.melqjpgames.entities.LuteFire;
import com.melqjpgames.entities.Player;
import com.melqjpgames.entities.Skeleton;
import com.melqjpgames.graphics.Spritesheet;
import com.melqjpgames.graphics.UI;
import com.melqjpgames.world.World;

public class Game extends Canvas implements Runnable, KeyListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	
	public static final int WIDTH = 256;
	public static final int HEIGHT = 240;
	public static final int SCALE = 4;
	
	private BufferedImage image;

	public static List<Entity> entities;
	public static List<Enemy> enemies;
	public static List<FireballShoot> fireballs;
	public static List<LuteFire> luteFires;
	public static List<EnemyDied> deadEnemies;
	public static Spritesheet spritesheet;
	public static Spritesheet spritesheet1;
	
	public static World world;
	public static Player player;
	public UI ui;
	public static Random rand;
	
	private String gameState = "NORMAL";
	private boolean showGOMessage;
	private int framesGO = 0;
	private int maxFramesGO = 30;
	private boolean reset;
	
	private int currentLevel = 1;
	private int maxLevels = 2;
	private int currentWave = 1;
	private int maxWaves = 3;
	
	public Game() {
		rand = new Random();
		addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		// Everything is scalable, to undo this, multiply here by scale
		// Initiates Objects
		initGame("/map_01.png");
	}
	
	public void initGame(String level) {
		spritesheet = new Spritesheet("/spritesheet.png");
		spritesheet1 = new Spritesheet("/spritesheet1.png");
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Enemy>();
		fireballs = new ArrayList<FireballShoot>();
		luteFires = new ArrayList<LuteFire>();
		deadEnemies = new ArrayList<EnemyDied>();
		player = new Player(32, 32, 16, 16);
		world = new World(level);
		ui = new UI();

		
	}
	
	
	public void initFrame() {
		frame = new JFrame("Zagan Quest");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
	

	
	public void update() {
		
		if(gameState.equals("NORMAL")) {
			reset = false;
		}
		for(int i = 0; i < deadEnemies.size(); i++) {
			EnemyDied e = deadEnemies.get(i);
			e.update();
		}
		
		if(gameState.equals("NORMAL"))
			player.update();
		
		
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.update();
		}
		
		ui.update();
		
		for(int i = 0; i < fireballs.size(); i++) {
			FireballShoot f = fireballs.get(i);
			f.update();
		}
		
		for(int i = 0; i < luteFires.size(); i++) {
			LuteFire f = luteFires.get(i);
			f.update();
		}
		
		
		if(player.getLife() <= 0) {
			gameState = "GAMEOVER";
		}
		
		if(enemies.size() == 0) {
			nextWaveorLevel();
		}
		
		if(gameState.equals("GAMEOVER")) {
			framesGO++;
			if(framesGO > maxFramesGO) {
				framesGO = 0;
				if(this.showGOMessage) {
					this.showGOMessage = false;
				}else {
					this.showGOMessage = true;
				}
			}
			if(reset) {
				initGame("/map_01.png");
				reset = false;
				gameState = "NORMAL";
			}
		}
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		world.render(g);
		for(int i = 0; i < deadEnemies.size(); i++) {
			EnemyDied e = deadEnemies.get(i);
			e.render(g);
		}
		
		if(gameState.equals("NORMAL"))
			player.render(g);
		else
			player.renderDead(g);
		
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		
		
		for(int i = 0; i < fireballs.size(); i++) {
			FireballShoot f = fireballs.get(i);
			f.render(g);
		}
		
		for(int i = 0; i < luteFires.size(); i++) {
			LuteFire f = luteFires.get(i);
			f.render(g);
		}
		ui.render(g);
		
		
		//
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image,  0,  0,  WIDTH*SCALE,  HEIGHT*SCALE, null);
		
		if(gameState == "GAMEOVER") {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(0x00, 0x00, 0x00, 150));
			g2.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.BOLD, 48));
			g.drawString("Game Over", WIDTH*SCALE / 2 - 125, HEIGHT*SCALE / 2);
			g.setFont(new Font("arial", Font.BOLD, 28));
			
			if(showGOMessage) {
				g.drawString("Try again? (Press ENTER)", WIDTH*SCALE / 2 - 175, HEIGHT*SCALE / 2 + 48);
			}
			
			
		}
		
		bs.show();
		
	}
	
	
	public void nextWaveorLevel() {
		
		switch(currentLevel) {
			case 1:
				switch(currentWave) {
					case 1: // Jumps to wave 2
						for(int i = 0; i < deadEnemies.size(); i++) {
							EnemyDied en = deadEnemies.get(i);
							Skeleton skeleton = new Skeleton((int)en.getX(), (int)en.getY(), en.getWidth(), en.getHeight(), en.getDir());
							enemies.add(skeleton);
							entities.add(skeleton);
						}
						deadEnemies.removeAll(deadEnemies);
						currentWave++;
						break;
					case 2: // Jumps to wave 3
						deadEnemies.removeAll(deadEnemies);
						currentWave++;
						break;
					case 3: // Jumps to level 2
						break;
				}
				break;
			
		}
		
		
		
		
//		currentLevel++;
//		if(currentLevel > maxLevels) {
//			currentLevel = 1;
//		}
//		String newWorld = "/map_0"+currentLevel+".png";
//		System.out.println(newWorld);
//		initGame(newWorld);
	}
	
	
	
	
	@Override
	public void run() {
		// Catch current time from PC
		long lastTime = System.nanoTime();
		// FPS
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0.0;
		
		// Check FPS
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		requestFocus();
		
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				update();
				render();
				delta--;
				frames++;
			}
			
			// Check FPS
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}
		
		stop();
		
	}

	// Keyboard events methods
	
	@Override
	public void keyTyped(KeyEvent e) {

		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
	
		if(e.getKeyCode() == KeyEvent.VK_RIGHT ||
				e.getKeyCode() == KeyEvent.VK_D) {
			// Moves Right
			player.setRight(true);
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT ||
				e.getKeyCode() == KeyEvent.VK_A) {
			// Moves Left
			player.setLeft(true);
		}

		if(e.getKeyCode() == KeyEvent.VK_UP ||
				e.getKeyCode() == KeyEvent.VK_W) {
			// Moves up
			player.setUp(true);
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN ||
				e.getKeyCode() == KeyEvent.VK_S) {
			// Moves down
			player.setDown(true);
		}
		
		if(!player.isCoolDown()) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				player.setShoot(true);
			}
		}
		
		if(gameState.equals("GAMEOVER")) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				reset = true;
			}
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT ||
				e.getKeyCode() == KeyEvent.VK_D) {
			// Stops Right
			player.setRight(false);
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT ||
				e.getKeyCode() == KeyEvent.VK_A) {
			// Stops Left
			player.setLeft(false);
		}

		if(e.getKeyCode() == KeyEvent.VK_UP ||
				e.getKeyCode() == KeyEvent.VK_W) {
			// Stops up
			player.setUp(false);
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN ||
				e.getKeyCode() == KeyEvent.VK_S) {
			// Stops down
			player.setDown(false);
		}	
		
	}

}
