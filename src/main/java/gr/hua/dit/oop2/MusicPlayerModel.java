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

    private int currentSongIndex = 0;


    private ArrayList<String> songs;

    private InputStream file;

    private final ArrayList<String> oldArray = new ArrayList<>();

//    private final PlayerListener playerListener = new PlayerListener() {
//        @Override
//        public void statusUpdated(PlayerEvent playerEvent) {
//
//        }
//    };

    public MusicPlayerModel() {
        player = PlayerFactory.getPlayer();
        this.songs = MusicPlayerController.songs;
    }
    public void playSong(ArrayList<String> songs) throws PlayerException, IOException, InterruptedException, InvalidDataException, UnsupportedTagException {
//        this.songs = songs;
        String song = songs.get(currentSongIndex);
        mp3MetaData(song);
        InputStream file = new FileInputStream(song);
//        player.addPlayerListener(new PlayerListener() {
//            public void statusUpdated(PlayerEvent event) {
//                System.out.println("Status is "+ event.getStatus());
//                if (event.getStatus() == Player.Status.IDLE) {
//                    try {
//                        next(songs);
//                    } catch (PlayerException | FileNotFoundException | InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//        });
        if (player.getStatus() != Player.Status.PLAYING) {
            // If the player was paused, resume playback
            if (player.getStatus() == Player.Status.PAUSED) {
                player.resume();
                //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Song resumed");
            } else {
                player.startPlaying(file);
                System.out.println(player.getStatus());
                //player.startPlaying(file);
                player.addPlayerListener(new PlayerListener() {
                    public void statusUpdated(PlayerEvent e) {
                        if (e.getStatus() == Player.Status.IDLE) {
                            System.out.println("TESTING HELLO");
                            try {
                                System.out.println(currentSongIndex);
                                currentSongIndex++;
                                System.out.println(currentSongIndex);
                                String song = songs.get(currentSongIndex);
                                //mp3MetaData(song);
                                System.out.println(song);
                                InputStream file = new FileInputStream(song);
                                System.out.println(file);
                                player.startPlaying(file);
                                System.out.println("hello");
                            } catch (PlayerException ex) {
                                throw new RuntimeException(ex);
                            } catch (FileNotFoundException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                });
                //while (player.getStatus()==Player.Status.PLAYING){ System.out.println(player.getStatus());};
                //System.out.println(player.getStatus());
                //next(songs);
               // /home/kazakos/Documents/songs

                //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Song started");
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
            stop();
        //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Song stopped");
            this.currentSongIndex = (currentSongIndex + 1) % songs.size();
            Thread.sleep(1000);
        //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Thread slept");
            playSong(songs);
    }
    public void prev(ArrayList<String> songs) throws InterruptedException, PlayerException, IOException, InvalidDataException, UnsupportedTagException {
        stop();
        //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Song stopped");
        this.currentSongIndex = (currentSongIndex - 1);
        if (this.currentSongIndex == -1)
            this.currentSongIndex = songs.size() - 1;
        Thread.sleep(1000);
        //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Thread slept");
        playSong(songs);
    }
    public void stop() {
        if (player.getStatus() == Player.Status.PLAYING || player.getStatus() == Player.Status.PAUSED) {
            player.stop();
            //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Song stopped");
        }
    }
    public void clickedPlay(ArrayList<String> songs, int index) throws InterruptedException, PlayerException, IOException, InvalidDataException, UnsupportedTagException {
        this.currentSongIndex = index;
        if (player.getStatus() == Player.Status.IDLE) {
            playSong(songs);
        } else {
            player.stop();
            //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Song stopped");
            Thread.sleep(1000);
            //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Thread slept");
            playSong(songs);
        }
    }

    public void loop(ArrayList<String> songs) throws PlayerException, InvalidDataException, UnsupportedTagException, IOException, InterruptedException {
        //this.currentSongIndex = currentSongIndex;
        while(true){
            playSong(songs);
            System.out.println(player.getStatus());
        }
    }

    public void playRandomSong(ArrayList<String> suffledSongs, int index) throws InvalidDataException, UnsupportedTagException, IOException, PlayerException, InterruptedException {
        String song = suffledSongs.get(currentSongIndex);
        mp3MetaData(song);
        InputStream file = new FileInputStream(song);
        player.startPlaying(file);
        next(suffledSongs);
    }
    public void random(ArrayList<String> songs) throws PlayerException, IOException, InvalidDataException, UnsupportedTagException, InterruptedException {
        oldArray.addAll(songs);  //για να κρατήοσουμε και την παλιά playlist
        Collections.shuffle(oldArray);
        playRandomSong(oldArray,currentSongIndex);
        player.close();
    }

    public void order(ArrayList<String> songs) throws PlayerException, IOException, InvalidDataException, UnsupportedTagException, InterruptedException {
        int z = 0;
        for(String song:songs){
            this.currentSongIndex = z;
            playSong(songs);
            z++;
        }
    }
// /home/kazakos/Documents/songs
    public void mp3MetaData(String song) throws InvalidDataException, UnsupportedTagException, IOException {
        //String song = songs.get(currentSongIndex);
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
