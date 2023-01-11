package gr.hua.dit.oop2;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import gr.hua.dit.oop2.musicplayer.*;
import jdk.jfr.Timespan;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;

public class MusicPlayerModel {
    private final Player player;

    private int currentSongIndex = 0;

    private int suffledSongIndex = 0;

    private boolean isRandom = false;


    private ArrayList<String> songs;

    private InputStream file;

    private final ArrayList<String> oldArray = new ArrayList<>();

    public MusicPlayerModel() {
        player = PlayerFactory.getPlayer();
        this.songs = MusicPlayerController.songs;
    }


    public void playSong(ArrayList<String> songs) throws PlayerException, IOException, InterruptedException, InvalidDataException, UnsupportedTagException {
        String song = "";
        if(isRandom){
            song =  oldArray.get(suffledSongIndex);
        }
        else{
             song = songs.get(currentSongIndex);
        }
        mp3MetaData(song);
        InputStream file = new FileInputStream(song);
        if (player.getStatus() != Player.Status.PLAYING) {
            // If the player was paused, resume playback
            if (player.getStatus() == Player.Status.PAUSED) {
                player.resume();
                //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Song resumed");
            } else {
                player.startPlaying(file);
                System.out.println(player.getStatus());
                player.addPlayerListener(new PlayerListener() {
                    public void statusUpdated(PlayerEvent e) {
                        if (e.getStatus() == Player.Status.IDLE) {
                            System.out.println("TESTING HELLO");
                            try {
                                System.out.println(currentSongIndex);
                                if(isRandom){
                                    next(oldArray);
                                }
                                else{
                                    next(songs);
                                }
                            } catch (PlayerException ex) {
                                throw new RuntimeException(ex);
                            } catch (FileNotFoundException ex) {
                                throw new RuntimeException(ex);
                            } catch (InvalidDataException ex) {
                                throw new RuntimeException(ex);
                            } catch (UnsupportedTagException ex) {
                                throw new RuntimeException(ex);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            } catch (InterruptedException ex) {
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
            //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Song paused");
        }
    }
    public void next(ArrayList<String> songs) throws PlayerException, IOException, InterruptedException, InvalidDataException, UnsupportedTagException {
        System.out.println(player.getStatus());
        stop();
        //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Song stopped");
        if(isRandom){
            this.suffledSongIndex = (suffledSongIndex + 1) % oldArray.size();
            playSong(oldArray);
        }else{
            this.currentSongIndex = (currentSongIndex + 1) % songs.size();
            playSong(songs);
        }
            Thread.sleep(1000);
        //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Thread slept");
    }
    public void prev(ArrayList<String> songs) throws InterruptedException, PlayerException, IOException, InvalidDataException, UnsupportedTagException {
        stop();
        //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Song stopped");
        if(isRandom){
            this.suffledSongIndex = (suffledSongIndex - 1);
            if (this.suffledSongIndex == -1)
                this.suffledSongIndex = oldArray.size() - 1;
            Thread.sleep(1000);
            playSong(oldArray);
        }else{
            this.currentSongIndex = (currentSongIndex - 1);
            if (this.currentSongIndex == -1)
                this.currentSongIndex = songs.size() - 1;
            Thread.sleep(1000);
            playSong(songs);
        }
        //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Thread slept");
    }
    public void stop() {
        if (player.getStatus() == Player.Status.PLAYING || player.getStatus() == Player.Status.PAUSED) {
            player.stop();
            //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Song stopped");
        }
    }
//    public void clickedPlay(ArrayList<String> songs, int index) throws InterruptedException, PlayerException, IOException, InvalidDataException, UnsupportedTagException {
//        if(isRandom){
//            this.suffledSongIndex = index;
//            if (player.getStatus() == Player.Status.IDLE) {
//                playSong(oldArray);
//            } else {
//                player.stop();
//                //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Song stopped");
//                Thread.sleep(1000);
//                //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Thread slept");
//                playSong(oldArray);
//            }
//        }else{
//            this.currentSongIndex = index;
//            if (player.getStatus() == Player.Status.IDLE) {
//                playSong(songs);
//            } else {
//                player.stop();
//                //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Song stopped");
//                Thread.sleep(1000);
//                //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Thread slept");
//                playSong(songs);
//            }
//        }
//    }

    public void loop(ArrayList<String> songs) throws PlayerException, InvalidDataException, UnsupportedTagException, IOException, InterruptedException {
        String song = "";
        while(true){
            if(isRandom){
                song = oldArray.get(suffledSongIndex);
                mp3MetaData(song);
                InputStream file = new FileInputStream(song);
                player.startPlaying(file);
            }else{
                song = songs.get(currentSongIndex);
                mp3MetaData(song);
                InputStream file = new FileInputStream(song);
                player.startPlaying(file);
            }
        }
    }

    public void random(ArrayList<String> songs) throws PlayerException, IOException, InvalidDataException, UnsupportedTagException, InterruptedException {
        oldArray.addAll(songs);  //για να κρατήοσουμε και την παλιά playlist
        Collections.shuffle(oldArray);
        isRandom = true;
        playSong(oldArray);
        isRandom = false;
    }

    public void order(ArrayList<String> songs) throws PlayerException, IOException, InvalidDataException, UnsupportedTagException, InterruptedException {
        int z = 0;
        for(String song:songs){
            this.currentSongIndex = z;
            playSong(songs);
            z++;
        }
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
