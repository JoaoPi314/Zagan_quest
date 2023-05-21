package com.wellmax.main;

public class OptionsMenu extends PauseMenu {

    public OptionsMenu() {
        this.options = new String[4];
        this.options[0] = "Video";
        this.options[1] = "Audio";
        this.options[2] = "Controls";
        this.options[3] = "Back";

        this.maxCursorPosition = options.length -1 ;
    }

}
