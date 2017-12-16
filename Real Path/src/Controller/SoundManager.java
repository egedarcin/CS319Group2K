package Controller;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class SoundManager {


    String musicFile1 = "/kick.mp3";     // For example

    Media sound = new Media(new File(musicFile1).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);
}
