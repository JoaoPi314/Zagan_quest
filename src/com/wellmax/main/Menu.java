package com.wellmax.main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import javax.imageio.ImageIO;

public class Menu {

	//---------------------------- Attributes ----------------------------------//

    private final int NUMBER_OF_STARS = 10;
    private final int STARS_SPRITES = 6;
    private final String[] OPTIONS = {"New Game", "Load Game", "Exit"};
    private final int MAXCURSORPOSITION = OPTIONS.length - 1;
    private final int[] STARXPOSITION = {588, 618, 255, 184, 343, 325, 188, 786, 438, 476};
    private final int[] STARYPOSITION = {39, 37, 93, 219, 182, 421, 251, 64, 238, 261};
    private final int[] STARSIZE = {48, 32, 8, 16, 48, 16, 32, 48, 32, 32};

    private BufferedImage menuBackground;
    private BufferedImage menuZagan;
    private BufferedImage[] menuFire;
    private BufferedImage[] menuStars;

    private int fireNumberOfSprites;
    private int fireWidth;


    private int index = 0;
    private int[] indexStar;
    private int countFrames = 0;
    private int cursor = 0;
    private int cursorY;

    
	//---------------------------- Methods ----------------------------------//
    
    public Menu(){
        
        Random rand = new Random();

        this.setFireNumberOfSprites(8);
        this.setFireWidth(427);
        this.indexStar = new int[NUMBER_OF_STARS];

        for(int i = 0; i < NUMBER_OF_STARS; i++){
            this.indexStar[i] = rand.nextInt(STARS_SPRITES);
        }

        this.menuFire = new BufferedImage[this.getFireNumberOfSprites()];
        this.menuStars = new BufferedImage[this.STARS_SPRITES];

        for(int i = 0; i < this.getFireNumberOfSprites(); i++)
            menuFire[i] = Game.menuFireSpritesheet.getSprite(i*this.getFireWidth(), 0, this.getFireWidth(), this.getFireWidth());

        for(int i = 0; i < STARS_SPRITES; i++) {
            menuStars[i] = Game.menuStarsSpritesheet.getSprite(i*32, 0, 32, 32);
        }

        try {
            this.menuBackground = ImageIO.read(Objects.requireNonNull(getClass().getResource("/menuBackground.png")));
            this.menuZagan = ImageIO.read(Objects.requireNonNull(getClass().getResource("/menuZagan.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void upCursor() {
        if(cursor > 0)
            cursor--;
    }

    public void downCursor() {
        if(cursor < MAXCURSORPOSITION)
            cursor++;
    }

    /**
	 * Method to update frame at frame
	 */
    public void update() {
        final int timeFactor = 20;

        this.countFrames++;
        if(this.countFrames == timeFactor){
            this.setIndex((this.getIndex()+1)%this.getFireNumberOfSprites());
            for(int j = 0; j < NUMBER_OF_STARS; j++)
                this.indexStar[j] = (this.indexStar[j] + 1) % STARS_SPRITES;
            countFrames = 0;
        }

        int baseCursor = (int)(Game.HEIGHT*Game.SCALE*0.458333333);
        switch(cursor) {
            case 0 -> cursorY = baseCursor;
            case 1 -> cursorY = baseCursor + 50;
            case 2 -> cursorY = baseCursor + 100;
            default -> {
            }
        }
    }

    private void setIndex(int i) {
        this.index = i;
    }

    /**
     *  Method to render frame
	 */
    public void render(Graphics g) {
        // Draw background
        g.setColor(Color.BLACK);
        g.drawImage(this.menuBackground, 0, 0,Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE, null);
        
        for(int i = 0; i < NUMBER_OF_STARS; i++) {
            g.drawImage(this.menuStars[this.indexStar[i]], this.STARXPOSITION[i], this.STARYPOSITION[i], this.STARSIZE[i], this.STARSIZE[i], null);
        }
        
        g.drawImage(this.menuFire[this.getIndex()], 0, Game.HEIGHT*Game.SCALE - this.getFireWidth() +55, this.getFireWidth()+100, this.getFireWidth(), null);
        g.drawImage(this.menuZagan, 0, 0,Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE, null);
        

        // Draw title
        Font title_font = new Font("URW Chancery L Medium Italic", Font.BOLD, 96);
        String title_name = "Zagan Quest";
        g.setColor(Color.WHITE);
        g.setFont(title_font);
        g.drawString(title_name, Game.WIDTH/8, Game.HEIGHT*Game.SCALE/6);

        // Draw Options
        g.setColor(Color.WHITE);
        Font menu_font = new Font("URW Chancery L Medium Italic", Font.BOLD, 36); 
        g.setFont(menu_font);
        int i = Game.HEIGHT*Game.SCALE/3;
        for (String string : OPTIONS) {
            g.drawString(string, (Game.WIDTH*Game.SCALE - g.getFontMetrics(menu_font).stringWidth(string))/2, Game.HEIGHT*Game.SCALE/8 + i);
            i += 50;
        }

        // Draw cursor
        g.drawString(">", Game.WIDTH*Game.SCALE/3+50, cursorY);
    }

    private int getIndex() {
        return this.index;
    }

    public int getCursor() {
        return this.cursor;
    }

    public int getFireNumberOfSprites() {
        return this.fireNumberOfSprites;
    }

    public int getFireWidth() {
        return this.fireWidth;
    }

    public void setFireWidth(int n) {
        this.fireWidth = n;
    }
    public void setFireNumberOfSprites(int n) {
        this.fireNumberOfSprites = n;
    }

}
