package gr.hua.dit.oop2;

import com.mpatric.mp3agic.*;
import gr.hua.dit.oop2.musicplayer.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;

public class MusicPlayerModel {
    private final Player player;
    private static int currentSongIndex = 0;
    private boolean isShuffled = false;
    private boolean isRepeat = false;
    private boolean firstTimeShuffled = true;
    private boolean stopPressed = false;
    private boolean isNextPrevClick = false;
    String song = "";
    private static int currentSongLength;
    private static String currentSong;
    private final ArrayList<String> shuffledArray = new ArrayList<>();

    public MusicPlayerModel() {
        player = PlayerFactory.getPlayer();
    }

    public void playSong(ArrayList<String> songs) throws PlayerException, IOException, InterruptedException, InvalidDataException, UnsupportedTagException {

        // To make a new song-list for the shuffle strategy.
        randomizer(songs);
        // If shuffle button is pressed use the shuffledArray, or else use the songs. (order strategy array)
        if (isShuffled) {
            song =  shuffledArray.get(currentSongIndex);
        } else {
            song = songs.get(currentSongIndex);
        }

        // currentSong variable is for the metadata.
        currentSong = song;
        InputStream file = new FileInputStream(song);
        if (player.getStatus() != Player.Status.PLAYING) {
            // If the player was paused, resume playback.
            if (player.getStatus() == Player.Status.PAUSED) {
                player.resume();
                org.hua.LogHandler.writeToLogNoThread(Level.INFO, song.substring(song.lastIndexOf("/") + 1) + " is resumed");
            } else {
                player.startPlaying(file);
                System.out.println(song.substring(song.lastIndexOf("/") + 1) + " is " + player.getStatus());
                player.addPlayerListener(new PlayerListener() {
                    public void statusUpdated(PlayerEvent e) {
                        // If the player status becomes IDLE, without pressing STOP or NEXT/PREVIOUS buttons, play the next song. (ORDER/RANDOM)
                        if (e.getStatus() == Player.Status.IDLE && !(stopPressed) && !(isNextPrevClick)) {
                            try {
                                currentSong = songs.get(currentSongIndex);  // for the metadata.
                                next(songs);
                            } catch (PlayerException | InvalidDataException | UnsupportedTagException | IOException |
                                     InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                });
            }
        }
    }

    // To pause the song. (with logger)
    public void pause() {
        if (player.getStatus() == Player.Status.PLAYING) {
            player.pause();
            org.hua.LogHandler.writeToLogNoThread(Level.INFO, song.substring(song.lastIndexOf("/") + 1) + " is paused");
        }
    }

    // To play the next song. (with logger)
    public void next(ArrayList<String> songs) throws PlayerException, IOException, InterruptedException, InvalidDataException, UnsupportedTagException {
        player.stop();
        Thread.sleep(1000);
        if (isRepeat) {
            playSong(songs);
        } else {
            org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Next song");
            currentSongIndex = (currentSongIndex + 1) % songs.size();
            playSong(songs);
        }
        isNextPrevClick = false;
    }

    // To play the previous song. (with logger)
    public void prev(ArrayList<String> songs) throws InterruptedException, PlayerException, IOException, InvalidDataException, UnsupportedTagException {
        player.stop();
        Thread.sleep(1000);
        if (isRepeat) {
            playSong(songs);
        } else {
            org.hua.LogHandler.writeToLogNoThread(Level.INFO, "Previous song");
            currentSongIndex = (currentSongIndex - 1);
            if (currentSongIndex == -1) {
                currentSongIndex = songs.size() - 1;
            }
            playSong(songs);
        }
        isNextPrevClick = false;
    }

    // To stop the player. (with logger)
    public void stop() {
        if (player.getStatus() == Player.Status.PLAYING || player.getStatus() == Player.Status.PAUSED) {
            player.stop();
            org.hua.LogHandler.writeToLogNoThread(Level.INFO, song.substring(song.lastIndexOf("/") + 1) + " is stopped");
        }
    }

    // To set the boolean variable if the stop is pressed. (so that it won't trigger the listener of the order strategy)
    public void setStopPressed (boolean stopPressed) {
        this.stopPressed = stopPressed;
    }

    // To play the double-clicked song. (with logger)
    public void clickedPlay(ArrayList<String> songs) throws InterruptedException, PlayerException, IOException, InvalidDataException, UnsupportedTagException {
        player.stop();
        org.hua.LogHandler.writeToLogNoThread(Level.INFO, song.substring(song.lastIndexOf("/") + 1) + " is playing, by double-click option");
        Thread.sleep(1000);
        playSong(songs);
    }

    // To shuffle the song-list. It is used only once.
    public void randomizer (ArrayList <String> songs) {
        if (firstTimeShuffled) {
            shuffledArray.addAll(songs);  // To keep the original song list.
            Collections.shuffle(shuffledArray);
            firstTimeShuffled = false;
        }
    }

    // To set the index of the current song.
    public void setCurrentSongIndex (int currentSongIndex) {
        MusicPlayerModel.currentSongIndex = currentSongIndex;
    }

    // To set the boolean variable for when the NEXT/PREVIOUS buttons are pressed, or a song has been double-clicked.
    public void setNextPrevClick (boolean isNextPrevClick) {
        this.isNextPrevClick = isNextPrevClick;
    }

    // To set the boolean variable when the shuffle button is pressed.
    public void setShuffled (boolean isShuffled) {
        this.isShuffled = isShuffled;
    }

    // To check if the strategy is shuffle. (getter)
    public boolean isShuffled () {
        return isShuffled;
    }

    // To set the boolean variable when the loop button is pressed.
    public void setRepeat (boolean isRepeat) {
        this.isRepeat = isRepeat;
    }

    // To check if the strategy is repeat. (getter)
    public boolean isRepeat () {
        return isRepeat;
    }

    // To get the current song.
    public String getCurrentSong() {
        return MusicPlayerModel.currentSong;
    }

    // To check if the stop is pressed.
    public boolean getStopPressed() {
        return stopPressed;
    }

    // To check if the NEXT/PREV buttons are pressed, or a song has been double-clicked.
    public boolean getNextPrevClick() {
        return isNextPrevClick;
    }

    // To get the player object.
    public Player getPlayer() {
        return player;
    }

    // To get the metadata of a song. (artist, album, length etc.)
    public String mp3MetaData(String song) throws InvalidDataException, UnsupportedTagException, IOException {
        String results = "Title: -\t\tArtist: -\t\tTitle: -";
        String artist, album;
        Mp3File mp3file = new Mp3File(song);
        if (mp3file.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            currentSongLength = (int) mp3file.getLengthInSeconds();         // Getting the song length in seconds.
            if (id3v2Tag.getArtist() == null) {
                artist = "Unknown";
            } else {
                artist = id3v2Tag.getArtist();
            }
            if (id3v2Tag.getAlbum() == null) {
                album = "Unknown";
            } else {
                album = id3v2Tag.getAlbum();
            }
            results = "Now Playing...   Title: " + song.substring(song.lastIndexOf("/") + 1) + " \tArtist: "
                    + artist + " \tAlbum: " + album;
        }
        return results;
    }

    // To get the length of the current song.
    public int getCurrentSongLength() {
        return currentSongLength;
    }
}