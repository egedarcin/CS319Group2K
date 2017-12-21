package Controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundManager {
    public void playSound(String url) {
        try{
        AudioInputStream stream = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream(url));
        Clip clip = AudioSystem.getClip();
        clip.open(stream);
        clip.start();
    } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void playKick(){
        playSound("ballKick.wav");
    }

    public void playCheering(){
        playSound("cheering1.wav");
    }
    public void playGameSound(){
        playSound("gameSound.wav");
    }

}
