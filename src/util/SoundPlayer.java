package src.util;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 * Simple and safe sound player that loads WAV files from classpath (resources/sounds).
 * Uses a cache of AudioInputStream/Clip info so short clips can be replayed quickly.
 *
 * Usage: SoundPlayer.play("beep.wav");
 */
public final class SoundPlayer {
    private static final String SOUNDS_PATH = "/sounds/"; // path inside resources root
    private static final Map<String, byte[]> cache = new ConcurrentHashMap<>();

    private SoundPlayer() { /* util */ }


    /**
     * Play a sound resource (non-blocking). The resource must be on the classpath under resources/sounds/
     * @param fileName example: "beep.wav"
     */
    public static void play(String fileName) {
        System.out.println(SoundPlayer.class.getResource("/sounds/beep.wav"));

        new Thread(() -> {
            try {
                // Load bytes from cache or resource
                byte[] audioBytes = cache.computeIfAbsent(fileName, f -> {
                    try {
                        URL url = SoundPlayer.class.getResource(SOUNDS_PATH + f);
                        if (url == null) return null;
                        try (AudioInputStream ais = AudioSystem.getAudioInputStream(url)) {
                            return ais.readAllBytes();
                        }
                    } catch (UnsupportedAudioFileException | IOException e) {
                        System.err.println("No s'ha pogut carregar el so: " + f + " -> " + e.getMessage());
                        return null;
                    }
                });

                if (audioBytes == null) return;

                // Create a clip from the bytes
                try (AudioInputStream ais = AudioSystem.getAudioInputStream(new java.io.ByteArrayInputStream(audioBytes))) {
                    AudioFormat baseFormat = ais.getFormat();
                    AudioFormat decodedFormat = new AudioFormat(
                            AudioFormat.Encoding.PCM_SIGNED,
                            baseFormat.getSampleRate(),
                            16,
                            baseFormat.getChannels(),
                            baseFormat.getChannels() * 2,
                            baseFormat.getSampleRate(),
                            false
                    );
                    try (AudioInputStream dais = AudioSystem.getAudioInputStream(decodedFormat, ais)) {
                        DataLine.Info info = new DataLine.Info(Clip.class, decodedFormat);
                        Clip clip = (Clip) AudioSystem.getLine(info);
                        clip.open(dais);
                        clip.start();

                        // Close clip after playback finishes
                        clip.addLineListener(event -> {
                            if (event.getType() == LineEvent.Type.STOP) {
                                clip.close();
                            }
                        });
                    }
                }
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                System.err.println("Error reproduint so '" + fileName + "': " + e.getMessage());
            }
        }).start();
    }
}
