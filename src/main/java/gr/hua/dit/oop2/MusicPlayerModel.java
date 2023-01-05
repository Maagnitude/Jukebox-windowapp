package gr.hua.dit.oop2;

import gr.hua.dit.oop2.musicplayer.*;

import java.io.*;
import java.util.ArrayList;
public class MusicPlayerModel {
    private final Player player;

    private int currentSongIndex = 0;
    private ArrayList<String> songs;

    public MusicPlayerModel() {
        player = PlayerFactory.getPlayer();
        this.songs = MusicPlayerController.songs;
    }
    public void playSong(ArrayList<String> songs) throws PlayerException, FileNotFoundException, InterruptedException {

        String song = songs.get(currentSongIndex);
        InputStream file = new FileInputStream(song);

        if (player.getStatus() != Player.Status.PLAYING) {
            // If the player was paused, resume playback
            if (player.getStatus() == Player.Status.PAUSED) {
                player.resume();
            } else {
                player.startPlaying(file);
            }
        }
    }
    public void pause() {
        if (player.getStatus() == Player.Status.PLAYING) {
            player.pause();
        }
    }
    public void next(ArrayList<String> songs) throws PlayerException, FileNotFoundException, InterruptedException {
            player.stop();
            this.currentSongIndex = (currentSongIndex + 1) % songs.size();
            Thread.sleep(1000);
            playSong(songs);
    }
    public void prev(ArrayList<String> songs) throws InterruptedException, PlayerException, FileNotFoundException {
        player.stop();
        this.currentSongIndex = (currentSongIndex - 1);
        if (this.currentSongIndex == -1)
            this.currentSongIndex = songs.size() - 1;
        Thread.sleep(1000);
        playSong(songs);
    }
    public void stop() {
        if (player.getStatus() == Player.Status.PLAYING || player.getStatus() == Player.Status.PAUSED) {
            player.stop();
        }
    }
    public void clickedPlay(ArrayList<String> songs, int index) throws InterruptedException, PlayerException, FileNotFoundException {
        this.currentSongIndex = index;
        if (player.getStatus() == Player.Status.IDLE) {
            playSong(songs);
        } else {
            player.stop();
            Thread.sleep(1000);
            playSong(songs);
        }
    }
}
