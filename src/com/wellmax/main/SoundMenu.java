package com.wellmax.main;

public class SoundMenu extends OptionsMenu {
    
    private float sfxVolume;
    private float musicVolume;

    public SoundMenu() {
        this.options = new String[3];
        this.options[0] = "SFX [==========]";
        this.options[1] = "Music [==========]";
        this.options[2] = "Back";

        this.maxCursorPosition = options.length - 1;
        
        this.sfxVolume = 1.0f;
        this.musicVolume = 1.0f;
    }

    public void sfxDecrease() {
        if(sfxVolume >= 0.1f)
            sfxVolume -= 0.1f;

        Sound.scytheHit.setVolume(sfxVolume);
        Sound.walk.setVolume(sfxVolume);
        Sound.enemyHit.setVolume(sfxVolume);
        Sound.zaganHit.setVolume(sfxVolume);
        Sound.fire.setVolume(sfxVolume);
        Sound.enemyDead.setVolume(sfxVolume);
        Sound.healthPotion.setVolume(sfxVolume);
        Sound.select.setVolume(sfxVolume);
        Sound.jump.setVolume(sfxVolume);

        this.options[0] = "SFX " + "[" + "=".repeat((int) (sfxVolume*10)) + "~".repeat((int) ((1 - sfxVolume)*10)) + "]";
    }

    public void musicDecrease() {
        if(musicVolume >= 0.1f)
            musicVolume -= 0.1f;

        Sound.musicBackground.setVolume(musicVolume);
        Sound.ambienceLevel1.setVolume(musicVolume);

        this.options[1] = "Music " + "[" + "=".repeat((int) (musicVolume*10)) + "~".repeat((int) ((1 - musicVolume)*10)) + "]";

    }

    public void sfxIncrease() {
        if(sfxVolume < 1.0f)
            sfxVolume += 0.1f;

        Sound.scytheHit.setVolume(sfxVolume);
        Sound.walk.setVolume(sfxVolume);
        Sound.enemyHit.setVolume(sfxVolume);
        Sound.zaganHit.setVolume(sfxVolume);
        Sound.fire.setVolume(sfxVolume);
        Sound.enemyDead.setVolume(sfxVolume);
        Sound.healthPotion.setVolume(sfxVolume);
        Sound.select.setVolume(sfxVolume);
        Sound.jump.setVolume(sfxVolume);

        this.options[0] = "SFX " + "[" + "=".repeat((int) (sfxVolume*10)) + "~".repeat((int) ((1 - sfxVolume)*10)) + "]";
    }

    public void musicIncrease() {
        if(musicVolume < 1.0f)
            musicVolume += 0.1f;

        Sound.musicBackground.setVolume(musicVolume);
        Sound.ambienceLevel1.setVolume(musicVolume);

        this.options[1] = "Music " + "[" + "=".repeat((int) (musicVolume*10)) + "~".repeat((int) ((1 - musicVolume)*10)) + "]";
    }




}
