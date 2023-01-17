package gr.hua.dit.oop2;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
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
    private final ArrayList<String> shuffledArray = new ArrayList<>();

    public MusicPlayerModel() {
        player = PlayerFactory.getPlayer();
    }

    public void playSong(ArrayList<String> songs) throws PlayerException, IOException, InterruptedException, InvalidDataException, UnsupportedTagException {

        randomizer(songs);
        if (isShuffled) {
            song =  shuffledArray.get(currentSongIndex);
        } else {
            System.out.println("currentSongIndex in playSong is: " + currentSongIndex);
            song = songs.get(currentSongIndex);
        }
//        player.addProgressListener(new ProgressListener() {
//            @Override
//            public void progress(ProgressEvent progressEvent) {
//                System.out.println("Seconds: " + progressEvent.getMicroseconds()/1000000.0);
//                view.setProgressBar(progressEvent.getMicroseconds()/1000000.0);
//            }
//        });
        mp3MetaData(song);
        InputStream file = new FileInputStream(song);
        if (player.getStatus() != Player.Status.PLAYING) {
            // If the player was paused, resume playback
            if (player.getStatus() == Player.Status.PAUSED) {
                player.resume();
                org.hua.LogHandler.writeToLogNoThread(Level.INFO, song.substring(song.lastIndexOf("/") + 1) + " is resumed");
            } else {
                player.startPlaying(file);
                System.out.println(song.substring(song.lastIndexOf("/") + 1) + " is " + player.getStatus());
                player.addPlayerListener(new PlayerListener() {
                    public void statusUpdated(PlayerEvent e) {
                        if (e.getStatus() == Player.Status.IDLE && !(stopPressed) && !(isNextPrevClick)) {
                            try {
                                System.out.println(currentSongIndex);
                                System.out.println("stopPressed is: " + stopPressed + " and isNextPrevClick: " + isNextPrevClick);
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

    public void pause() {
        if (player.getStatus() == Player.Status.PLAYING) {
            player.pause();
            org.hua.LogHandler.writeToLogNoThread(Level.INFO, song.substring(song.lastIndexOf("/") + 1) + " is paused");
        }
    }

    public void next(ArrayList<String> songs) throws PlayerException, IOException, InterruptedException, InvalidDataException, UnsupportedTagException {
        player.stop();
        Thread.sleep(1000);
        if (isRepeat) {
            playSong(songs);
        } else {
            org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Next song");
            currentSongIndex = (currentSongIndex + 1) % songs.size();
            System.out.println("currentSongIndex in next is: " + currentSongIndex);
            playSong(songs);
        }
        isNextPrevClick = false;
    }

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

    public void stop() {
        if (player.getStatus() == Player.Status.PLAYING || player.getStatus() == Player.Status.PAUSED) {
            player.stop();
            org.hua.LogHandler.writeToLogNoThread(Level.INFO, song.substring(song.lastIndexOf("/") + 1) + " is stopped");
        }
    }

    public void setStopPressed (boolean stopPressed) {
        this.stopPressed = stopPressed;
    }

    public void clickedPlay(ArrayList<String> songs) throws InterruptedException, PlayerException, IOException, InvalidDataException, UnsupportedTagException {

        System.out.println("currentSongIndex in clicked is: " + currentSongIndex);
        player.stop();
        //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Song stopped");
        Thread.sleep(1000);
        //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Thread slept");
        playSong(songs);
    }

    public void randomizer (ArrayList <String> songs) {
        if (firstTimeShuffled) {
            shuffledArray.addAll(songs);  //για να κρατήοσουμε και την παλιά playlist
            Collections.shuffle(shuffledArray);
            firstTimeShuffled = false;
        }
    }

    public void setCurrentSongIndex (int currentSongIndex) {
        MusicPlayerModel.currentSongIndex = currentSongIndex;
    }

    public void setNextPrevClick (boolean isNextPrevClick) {
        this.isNextPrevClick = isNextPrevClick;
    }
    public void setShuffled (boolean isShuffled) {
        this.isShuffled = isShuffled;
    }

    public boolean isShuffled () {
        return isShuffled;
    }

    public void setRepeat (boolean isRepeat) {
        this.isRepeat = isRepeat;
    }

    public boolean isRepeat () {
        return isRepeat;
    }
    public void mp3MetaData(String song) throws InvalidDataException, UnsupportedTagException, IOException {
        Mp3File mp3file = new Mp3File(song);
        if (mp3file.hasId3v1Tag()) {
            ID3v1 id3v1Tag = mp3file.getId3v1Tag();
            System.out.println("Track: " + id3v1Tag.getTrack());
            System.out.println("Artist: " + id3v1Tag.getArtist());
            System.out.println("Title: " + id3v1Tag.getTitle());
            System.out.println("Album: " + id3v1Tag.getAlbum());
            System.out.println("Year: " + id3v1Tag.getYear());
            System.out.println("Genre: " + id3v1Tag.getGenre() + " (" + id3v1Tag.getGenreDescription() + ")");
            System.out.println("Comment: " + id3v1Tag.getComment());
        }
    }
}
