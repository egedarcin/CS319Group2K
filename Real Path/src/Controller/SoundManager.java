package Controller;

import javafx.scene.media.AudioClip;

public class SoundManager {

<<<<<<< HEAD
    public static void kickSound(){
        AudioClip kickSound = new AudioClip("/ballKick.mp3");
        kickSound.play();
    }
    public static void playGameSound(){
        AudioClip gameSound = new AudioClip("/gameSound.mp3");
        gameSound.play();
    }
    public static void goalVoice(){
        AudioClip goalSound = new AudioClip("/cheering.wav");
        goalSound.play();
    }

}

