package org.example.demo;


import javafx.scene.layout.Background;
import javafx.scene.media.AudioClip;

import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    private static Map<String, AudioClip> effect = new HashMap<>();

    static {
        AudioClip BackGround = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/BackGround.mp3").toExternalForm());
        AudioClip BackGround2 = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/BackGround2.mp3").toExternalForm());
        AudioClip BackGround3 = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/BackGround3.mp3").toExternalForm());
        AudioClip BackGround4 = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/BackGround4.mp3").toExternalForm());
        AudioClip BallHitPaddle = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/BallHitPaddle.wav").toExternalForm());
        AudioClip BallWhenDie = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/BallWhenDie.wav").toExternalForm());
        AudioClip BallHitWall = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/BallHitWall.wav").toExternalForm());
        AudioClip BrickDestroyed = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/BrickDestroyed.wav").toExternalForm());
        AudioClip BallHitBrick = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/BallHitBrick.wav").toExternalForm());
        AudioClip PowerUpApply = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/PowerUpApply.wav").toExternalForm());
        AudioClip SoundClick = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/SoundClick.mp3").toExternalForm());
        BackGround.setCycleCount(AudioClip.INDEFINITE);

        effect.put("BackGround", BackGround);
        effect.put("BackGround2", BackGround2);
        effect.put("BackGround3", BackGround3);
        effect.put("BackGround4", BackGround4);
        effect.put("BallHitPaddle", BallHitPaddle);
        effect.put("BallHitWall", BallHitWall);
        effect.put("BallWhenDie", BallWhenDie);
        effect.put("BrickDestroyed", BrickDestroyed);
        effect.put("BallHitBrick", BallHitBrick);
        effect.put("PowerUpApply", PowerUpApply);
        effect.put("SoundClick", SoundClick);
    }



    public static void playSoundEffect(String Key) {

        effect.get(Key).play();
    }

    public static void stopSoundEffect(String Key) {
        effect.get(Key).stop();
    }
}
