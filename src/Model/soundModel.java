package Model;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.TargetDataLine;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class soundModel {
    public Clip clip;
    public soundModel(String path, boolean loop){
        try {
            File file = new File(path);
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(stream);
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }

            stream.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void start(){
        clip.start();
    }
    public void stop(){
        clip.stop();
    }

    }

