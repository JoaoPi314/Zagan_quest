package com.wellmax.main;

import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    private Clip clip;

    public static final Sound musicBackground = new Sound("/menu.wav");
    public static final Sound scytheHit = new Sound("/scythe.wav");
    public static final Sound walk = new Sound("/walk.wav");
    public static final Sound enemyHit = new Sound("/enemyHit.wav");
    public static final Sound zaganHit = new Sound("/zaganHit.wav");
    public static final Sound fire = new Sound("/fire.wav");
    public static final Sound enemyDead = new Sound("/enemyDead.wav");
    public static final Sound healthPotion = new Sound("/healthPotion.wav");
    public static final Sound rain = new Sound("/rain.wav");
    public static final Sound ambienceLevel1 = new Sound("/ambienceLevel1.wav");
    public static final Sound select = new Sound("/select.wav");
    public static final Sound jump = new Sound("/jump.wav");

    private URL is;
    private AudioInputStream ais;
    private Sound(String name) {
        try {
            clip = AudioSystem.getClip();
            this.is = Sound.class.getResource(name);
            this.ais = AudioSystem.getAudioInputStream(is);
            clip.open(ais);

        } catch(Throwable e) {
            System.out.println(e);
        }
    }
    
    public void play() {
        if(!clip.isRunning())
            try {
                new Thread() {
                    public void run() {
                        clip.setFramePosition(0);
                        clip.start();
                    }
                }.start();
            } catch(Throwable e) {
                System.out.println(e);
            }
    }

    public void loop() {
        if(!clip.isRunning())
            try {
                new Thread() {
                    public void run() {
                        clip.setMicrosecondPosition(0);
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                }.start();
            } catch(Throwable e) {
                System.out.println(e);
            }
    }

    public void stop() {
        clip.stop();
    }

}
