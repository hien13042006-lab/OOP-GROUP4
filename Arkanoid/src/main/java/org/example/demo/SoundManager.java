package org.example.demo;

import javafx.scene.effect.Effect;
import javafx.scene.media.AudioClip;

import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    private static Map<String, AudioClip> effect = new HashMap<>();

    static {
            AudioClip paddleAndBall = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/PaddleAndBall.wav").toExternalForm());
            AudioClip BrickAndBall = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/BrickAndBall.wav").toExternalForm());
            AudioClip BrickDestroyed = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/BrickDestroyed.wav").toExternalForm());
            AudioClip PowerUpApply = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/PowerUpApply.wav").toExternalForm());
            AudioClip BackgroundSoundtrack = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/BackgroundSoundtrack.mp3").toExternalForm());
            BackgroundSoundtrack.setCycleCount(AudioClip.INDEFINITE);

            effect.put("paddleAndBall", paddleAndBall);
            effect.put("BrickAndBall", BrickAndBall);
            effect.put("BrickDestroyed", BrickDestroyed);
            effect.put("PowerUpApply", PowerUpApply);
            effect.put("BackgroundSoundtrack", BackgroundSoundtrack);
    }

    public static void playSoundEffect(String Key) {

        effect.get(Key).play();
    }

    public static void stopSoundEffect(String Key) {
        effect.get(Key).stop();
    }
}
