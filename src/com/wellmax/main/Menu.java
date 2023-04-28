package com.wellmax.main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {

	//---------------------------- Attributes ----------------------------------//
    private final String[] OPTIONS = {"New Game", "Load Game", "Exit"};
    private final int MAXCURSORPOSITION = OPTIONS.length - 1;

    private int cursor = 0;
    private int cursorY;

	//---------------------------- Methods ----------------------------------//

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
        int baseCursor = (int)(Game.HEIGHT*Game.SCALE*0.458333333);
        switch(cursor) {
            case 0 -> cursorY = baseCursor;
            case 1 -> cursorY = baseCursor + 50;
            case 2 -> cursorY = baseCursor + 100;
            default -> {
            }
        }
    }

    /**
     *  Method to render frame
	 */
    public void render(Graphics g) {
        // Draw background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);

        // Draw title
        Font title_font = new Font("arial", Font.BOLD, 72);
        String title_name = "Zagan Quest";
        g.setColor(Color.RED);
        g.setFont(title_font);
        g.drawString(title_name, (Game.WIDTH*Game.SCALE - g.getFontMetrics(title_font).stringWidth(title_name))/2, Game.HEIGHT*Game.SCALE/8);

        // Draw Options
        g.setColor(Color.WHITE);
        Font menu_font = new Font("arial", Font.BOLD, 36); 
        g.setFont(menu_font);
        int i = Game.HEIGHT*Game.SCALE/3;
        for (String string : OPTIONS) {
            g.drawString(string, (Game.WIDTH*Game.SCALE - g.getFontMetrics(menu_font).stringWidth(string))/2, Game.HEIGHT*Game.SCALE/8 + i);
            i += 50;
        }

        // Draw cursor
        g.setColor(new Color(135, 12, 52));
        g.drawString(">", Game.WIDTH*Game.SCALE/4, cursorY);
        g.drawString(OPTIONS[cursor], (Game.WIDTH*Game.SCALE - g.getFontMetrics(menu_font).stringWidth(OPTIONS[cursor]))/2, cursorY);

    }

    public int getCursor() {
        return this.cursor;
    }
}
