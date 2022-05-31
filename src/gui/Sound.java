package gui;


import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;

    private boolean isPlaying = false;
    private boolean looping = false;

    public Sound() {

    }

    public Sound(String filePath){
        setFile(filePath);
    }

    public void setFile(String filePath) {
        try {
            File file = new File(filePath);
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        }
        catch (Exception e) {
            System.err.println("Error in " + filePath + ": " + e.getMessage());
        }
    }

    public void play() {
        isPlaying = true;
        clip.setFramePosition(0);
        clip.start();
        if (clip.isRunning()) {
            isPlaying = false;
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
