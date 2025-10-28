package org.example.demo;


import javafx.scene.layout.Background;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.*;

public class SoundManager {
    private static Map<String, AudioClip> effect = new HashMap<>();
    private static final List<Media> backgrounds = new ArrayList<>();
    private static MediaPlayer currentBackground = null;
    private static int currentIndex = 0;

    static {
        AudioClip BallHitPaddle = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/BallHitPaddle.wav").toExternalForm());
        AudioClip BallWhenDie = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/BallWhenDie.wav").toExternalForm());
        AudioClip BallHitWall = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/BallHitWall.wav").toExternalForm());
        AudioClip BrickDestroyed = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/BrickDestroyed.wav").toExternalForm());
        AudioClip BallHitBrick = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/BallHitBrick.wav").toExternalForm());
        AudioClip PowerUpApply = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/PowerUpApply.wav").toExternalForm());
        AudioClip SoundClick = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/SoundClick.mp3").toExternalForm());
        AudioClip GameOver = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/GameOver.mp3").toExternalForm());
        AudioClip GameWin = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/GameWin.mp3").toExternalForm());
        AudioClip NextStage = new AudioClip(SoundManager.class.getResource("/asset/SoundEffect/NextStage.wav").toExternalForm());

        effect.put("BallHitPaddle", BallHitPaddle);
        effect.put("BallHitWall", BallHitWall);
        effect.put("BallWhenDie", BallWhenDie);
        effect.put("BrickDestroyed", BrickDestroyed);
        effect.put("BallHitBrick", BallHitBrick);
        effect.put("PowerUpApply", PowerUpApply);
        effect.put("SoundClick", SoundClick);
        effect.put("GameOver", GameOver);
        effect.put("GameWin", GameWin);
        effect.put("NextStage", NextStage);

        backgrounds.add(new Media(SoundManager.class.getResource("/asset/SoundEffect/BackGround.mp3").toExternalForm()));
        backgrounds.add(new Media(SoundManager.class.getResource("/asset/SoundEffect/BackGround2.mp3").toExternalForm()));
        backgrounds.add(new Media(SoundManager.class.getResource("/asset/SoundEffect/BackGround3.mp3").toExternalForm()));
        backgrounds.add(new Media(SoundManager.class.getResource("/asset/SoundEffect/BackGround4.mp3").toExternalForm()));
    }

    public static void playSoundEffect(String Key) {
        effect.get(Key).play();
    }

    public static void stopSoundEffect(String Key) {
        effect.get(Key).stop();
    }

    //Chỉnh volumn
    public static void setVolume(String key, double volume) {
        AudioClip clip = effect.get(key);
        if (clip != null) {
            clip.setVolume(volume);
        }
    }

    public static void playBackground() {
        stopBackground(); // dừng nhạc hiện tại nếu có

        Media media = backgrounds.get(currentIndex);
        currentBackground = new MediaPlayer(media);
        currentBackground.setVolume(0.3);

        // Khi bài hiện tại kết thúc -> phát bài tiếp theo
        currentBackground.setOnEndOfMedia(() -> {
            currentIndex = (currentIndex + 1) % backgrounds.size(); // quay lại 0 khi hết
            playBackground(); // gọi lại để phát tiếp
        });

        currentBackground.play();
    }

    public static void stopBackground() {
        if (currentBackground != null) {
            currentBackground.stop();
        }
    }
}
