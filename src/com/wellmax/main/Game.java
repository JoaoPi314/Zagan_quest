package com.wellmax.main;

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
import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.wellmax.entities.Collectible;
import com.wellmax.entities.DeadEnemy;
import com.wellmax.entities.Enemy;
import com.wellmax.entities.Player;
import com.wellmax.entities.Projectile;
import com.wellmax.entities.Skeleton;
import com.wellmax.graphics.Spritesheet;
import com.wellmax.graphics.UI;
import com.wellmax.world.Scenario;
import com.wellmax.world.World;

/**
 * The main class of project. It will run the game
 * @author joao.gomes
 *
 */
public class Game extends Canvas implements Runnable, KeyListener{

	//---------------------------- Attributes ----------------------------------//
	/**
	 * Serial Version UID
	 */
	@Serial
	private static final long serialVersionUID = 1L;
	/**
	 * Thread (Responsible for run the game)
	 */
	private Thread thread;
	/**
	 * Flag that indicates if game is running
	 */
	private boolean running;
	/**
	 * Screen Width
	 */
	public static final int WIDTH = 256;
	/**
	 * Screen height
	 */
	public static final int HEIGHT = 240;
	/**
	 * Screen scale
	 */
	public static final int SCALE = 4;
	/**
	 * Image to be rendered
	 */
	private BufferedImage image;
	/**
	 * List with all collectibles 
	 */
	public static List<Collectible> collectibles;
	/**
	 * List with all enemies
	 */
	public static List<Enemy> enemies;
	/**
	 * List with all projectiles
	 */
	public static List<Projectile> projectiles;
	/**
	 * List with all dead enemies
	 */
	public static List<DeadEnemy> deadEnemies;
	/**
	 * List with all scenario items
	 */
	public static List<Scenario> scenario;
	/**
	 * First spritesheet
	 */
	public static Spritesheet spritesheet;
	/**
	 * Second spritesheet
	 */
	public static Spritesheet spritesheet1;
	/**
	 * Game Over screen image
	 */
	private BufferedImage gameOverImage;
	/**
	 * World is responsible for map generation
	 */
	public static World world;
	/**
	 * Game player
	 */
	public static Player player;
	/**
	 * User interface
	 */
	public UI ui;
	/**
	 * Random object to use in various occasions
	 */
	public static Random rand;
	/**
	 * Enum with gameState
	 */
	private enum GameStates{
		NORMAL,
		GAME_OVER
	}
	/**
	 * Game state
	 */
	GameStates gameState;
	/**
	 * Flag that indicates if the game over message should be rendered
	 */
	private boolean showGameOverMessage;
	/**
	 * Frames counting to game over message blink
	 */
	private int framesGameOver;
	/**
	 * Max frames of game over text to nlink
	 */
	private int maxFramesGameOver;
	/**
	 * Flag that indicates if game should be reset
	 */
	private boolean reset;
	/**
	 * Current level
	 */
	private int currentLevel;
	/**
	 * Number of levels existing in game
	 */
	private int maxLevels;
	/**
	 * Current wave
	 */
	private int currentWave;
	/**
	 * Number of waves of current level
	 */
	private int maxWaves;

	//---------------------------- Methods ----------------------------------//	
	
	/**
	 * Game constructor
	 */
	public Game() {
		// Attributes
		this.setRunning(true);
		this.setGameState(GameStates.NORMAL);
		// game over message
		this.setFramesGameOver(0);
		this.setMaxFramesGameOver(30);
		
		// Levels
		this.setCurrentLevel(1);
		this.setMaxLevels(1);
		this.setCurrentWave(1);
		this.setMaxWaves(3);
		
		
		rand = new Random();
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		this.initFrame("Zagan quest");
		// Everything is scalable, to undo this, multiply here by scale
		// Initiates Objects
		this.initGame("/map_01.png");
	}
	

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public GameStates getGameState() {
		return gameState;
	}

	public void setGameState(GameStates gameState) {
		this.gameState = gameState;
	}

	public boolean isShowGameOverMessage() {
		return showGameOverMessage;
	}

	public void setShowGameOverMessage(boolean showGameOverMessage) {
		this.showGameOverMessage = showGameOverMessage;
	}

	public int getFramesGameOver() {
		return framesGameOver;
	}

	public void setFramesGameOver(int framesGameOver) {
		this.framesGameOver = framesGameOver;
	}

	public int getMaxFramesGameOver() {
		return maxFramesGameOver;
	}

	public void setMaxFramesGameOver(int maxFramesGameOver) {
		this.maxFramesGameOver = maxFramesGameOver;
	}

	public boolean isReset() {
		return reset;
	}

	public void setReset(boolean reset) {
		this.reset = reset;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public int getMaxLevels() {
		return maxLevels;
	}

	public void setMaxLevels(int maxLevels) {
		this.maxLevels = maxLevels;
	}

	public int getCurrentWave() {
		return currentWave;
	}

	public void setCurrentWave(int currentWave) {
		this.currentWave = currentWave;
	}

	public int getMaxWaves() {
		return maxWaves;
	}

	public void setMaxWaves(int maxWaves) {
		this.maxWaves = maxWaves;
	}

	/**
	 * Method to initiates all objects of the game
	 * @param level Path to current map image
	 */
	public void initGame(String level) {
		Game.spritesheet = new Spritesheet("/spritesheet.png");
		Game.spritesheet1 = new Spritesheet("/spritesheet1.png");
		
		try {
			this.gameOverImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/gameoverscreen.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Game.collectibles = new ArrayList<>();
		Game.enemies = new ArrayList<>();
		Game.projectiles = new ArrayList<>();
		Game.deadEnemies = new ArrayList<>();
		Game.scenario = new ArrayList<>();
		Game.player = new Player(32, 32, 16, 16);
		Game.world = new World(level);
		this.ui = new UI();
	}
	
	/**
	 * Method to initiates screen
	 */
	public void initFrame(String title) {
		JFrame frame = new JFrame(title);
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/**
	 * Method to start the game thread
	 */
	public synchronized void start() {
		thread = new Thread(this);
		this.setRunning(true);
		thread.start();
	}
	
	/**
	 * Method to stop game thread
	 */
	public synchronized void stop() {
		this.setRunning(false);
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Main method
	 */
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	/**
	 * Method to check if game jumps to next level ow wave
	 */
	private void nextWaveOrLevel() {
		
		switch(this.getCurrentLevel()) {
			case 1:
				switch(this.getCurrentWave()) {
					case 1: // Jumps to wave 2
						for(int i = 0; i < Game.deadEnemies.size(); i++) {
							DeadEnemy en = Game.deadEnemies.get(i);
							Skeleton skeleton = new Skeleton((int)en.getX(), (int)en.getY(), en.getWidth(), en.getHeight(), en.getFaceDir());
							Game.enemies.add(skeleton);
						}
						Game.deadEnemies.clear();
						this.setCurrentWave(this.getCurrentWave() + 1);
						break;
					case 2: // Jumps to wave 3
						Game.deadEnemies.clear();
						this.setCurrentWave(this.getCurrentWave() + 1);
						break;
					case 3: // Jumps to level 2
						break;
				}
				break;
			
		}
	}

	/**
	 * Method to update frame at frame
	 */
	public void update() {
		
		// Updates deadEnemies
		for(int i = 0; i < deadEnemies.size(); i++) {
			DeadEnemy e = deadEnemies.get(i);
			e.update();
		}
		
		// Checks game state
		switch(gameState) {
			case NORMAL:
				// Game should not be reseted
				this.setReset(false);
				Game.player.update();
				//
				break;
			case GAME_OVER:
			default:
				this.setFramesGameOver(this.getFramesGameOver() + 1);
				if(this.getFramesGameOver() > this.getMaxFramesGameOver()) {
					this.setFramesGameOver(0);
					if(this.isShowGameOverMessage()) {
						this.setShowGameOverMessage(false);
					}else {
						this.setShowGameOverMessage(true);
					}
				}
				if(this.isReset()) {
					this.initGame("/map_01.png");
					this.setReset(false);
					this.setGameState(GameStates.NORMAL);
				}
				break;
		}
		
		// Updates collectibles
		for(int i = 0; i < collectibles.size(); i++) {
			Collectible e = collectibles.get(i);
			e.update();
		}
	
		// Updates enemies
		for(int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.update();
		}
		
		// Updates projectile
		for(int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			p.update();
		}
		
		// Checks for game over
		if(player.getLife() <= 0) {
			this.setGameState(GameStates.GAME_OVER);
		}
		
		// Updates User Interface
		ui.update();
		
		// Checks for next wave or level
		if(enemies.size() == 0) {
			nextWaveOrLevel();
		}

	}

	/**
	 * Render method
	 */
	public void render() {
		
		// Creates BufferedStrategy
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		// Creates Graphics
		Graphics g = image.getGraphics();
		
		// Default is black screen
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// Renders world
		world.render(g);
		for(int i = 0; i < deadEnemies.size(); i++) {
			DeadEnemy e = deadEnemies.get(i);
			e.render(g);
		}
				
		
		if(this.getGameState() == GameStates.NORMAL) {
			Game.player.render(g);
		}else if(this.getGameState() == GameStates.GAME_OVER) {
			Game.player.renderDead(g);
		}
		

		// Renders projectiles
		for(int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			p.render(g);
		}
		
		// Renders enemies
		for(int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.render(g);
		}
		
		// Renders collectibles
		for(int i = 0; i < collectibles.size(); i++) {
			Collectible e = collectibles.get(i);
			e.render(g);
		}
		
		// Renders Scenario items
		for(int i = 0; i < Game.scenario.size(); i++) {
			Scenario s = Game.scenario.get(i);
			s.render(g);	
		}
		
		// Renders User interface
		ui.render(g);
				
		// Draws everything
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image,  0,  0,  WIDTH*SCALE,  HEIGHT*SCALE, null);
		
		if(this.getGameState() == GameStates.GAME_OVER) {
			// Shows Game over message
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(0x00, 0x00, 0x00, 150));
			g2.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
			g.drawImage(this.gameOverImage, 0, 0,WIDTH*SCALE, HEIGHT*SCALE, null);
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.BOLD, 28));
			
			if(showGameOverMessage) {
				g.drawString("Try again? (Press ENTER)", WIDTH*SCALE / 2 - 175, HEIGHT*SCALE / 2 + 175);
			}
			
		}
		
		
		bs.show();
		
	}
	

	/**
	 * Method to run game
	 */
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
		
		while(this.isRunning()) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				this.update();
				this.render();
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
		
		// Stops game when screen is closed (Security redundancy)
		this.stop();
	}
	/**
	 * Method to check event KeyTyped
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Method to check event KeyPressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
	
		
		// Horizontal keys
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> Game.player.setRight(true);
			case KeyEvent.VK_LEFT, KeyEvent.VK_A -> Game.player.setLeft(true);
			default -> {
			}
		}

		// Vertical keys
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP, KeyEvent.VK_W -> Game.player.setUp(true);
			case KeyEvent.VK_DOWN, KeyEvent.VK_S -> Game.player.setDown(true);
			default -> {
			}
		}
		
		// Shoot key
		if(!player.isCoolDown()) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				player.setShoot(true);
			}
		}
		
		if(this.getGameState() == GameStates.GAME_OVER) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				reset = true;
			}
		}
		
		
	}

	/**
	 * Method to check event KeyReleased
	 */
	@Override
	public void keyReleased(KeyEvent e) {

		
		// Horizontal keys
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> Game.player.setRight(false);
			case KeyEvent.VK_LEFT, KeyEvent.VK_A -> Game.player.setLeft(false);
			default -> {
			}
		}

		// Vertical keys
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP, KeyEvent.VK_W -> Game.player.setUp(false);
			case KeyEvent.VK_DOWN, KeyEvent.VK_S -> Game.player.setDown(false);
			default -> {
			}
		}
		
	}

}
