package PONG_GAME;
import java.io.*;
import javax.sound.sampled.*;

public class Sound implements Runnable {

    private String soundFile;
    private Thread soundThread;

    public Sound(String filename) {
        soundFile = filename;
    }

    public void playSound() {
        try {
            // Open an audio input stream.
            File sound = new File(soundFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);

            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();

            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);

            // Play the sound clip.
            clip.start();

            // Wait for the clip to finish playing.
            while (clip.isRunning())
                Thread.sleep(10);

            // Clean up resources.
            clip.close();
            audioIn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        soundThread = Thread.currentThread();
        playSound();
    }

    public void stop() {
        if (soundThread != null) {
            soundThread.interrupt();
            soundThread = null;
        }
    }
}


