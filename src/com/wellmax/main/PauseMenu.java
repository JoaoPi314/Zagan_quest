package com.wellmax.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class PauseMenu {
  	//---------------------------- Attributes ----------------------------------//

    protected String[] options = {"Resume", "Save Game", "Load Game", "Options", "Exit to menu"};
    protected int maxCursorPosition = options.length - 1;

    protected int cursor = 0;
    protected int cursorY;

    
    //---------------------------- Methods ----------------------------------//

    public void upCursor() {
        if(cursor > 0)
            cursor--;
    }

    public void downCursor() {
        if(cursor < maxCursorPosition)
            cursor++;
    }

    /**
     * Method to update frame at frame
     */
    public void update() {
        int baseCursor = (int)(Game.HEIGHT*0.458333333);
        switch(cursor) {
            case 0 -> cursorY = baseCursor;
            case 1 -> cursorY = baseCursor + 25;
            case 2 -> cursorY = baseCursor + 50;
            case 3 -> cursorY = baseCursor + 75;
            case 4 -> cursorY = baseCursor + 100;
            default -> {
            }
        }
    }

    /**
     *  Method to render frame
     */
    public void render(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(0x00, 0x00, 0x00, 150));
        g2.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

        // Draw Options
        g.setColor(Color.WHITE);
        Font menuFont = new Font("URW Chancery L Medium Italic", Font.BOLD, 18); 
        g.setFont(menuFont);
        int i = Game.HEIGHT/3;
        for (String string : options) {
            g.drawString(string, (Game.WIDTH - g.getFontMetrics(menuFont).stringWidth(string))/2, Game.HEIGHT/8 + i);
            i += 25;
        }

        // Draw cursor
        g.drawString(">", Game.WIDTH/4+25, cursorY);
    }

    public int getCursor() {
        return this.cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

}
