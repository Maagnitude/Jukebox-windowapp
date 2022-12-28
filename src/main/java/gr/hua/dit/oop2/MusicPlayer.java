//package gr.hua.dit.oop2;
//
//import gr.hua.dit.oop2.musicplayer.Player;
//import gr.hua.dit.oop2.musicplayer.PlayerException;
//import java.io.FileInputStream;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.logging.Level;
//
//public class MusicPlayer {
//
//    public static final String ANSI_RED = "\u001B[31m";                 // To use red text color.
//    public static final String ANSI_GREEN = "\u001B[32m";               // To use green text color.
//    public static final String ANSI_RESET = "\u001B[37m";               // To reset to the default text color.
//    private final ArrayList<String> songs=new ArrayList<>();            // Song playlist.
//    private final ArrayList<String> oldArray = new ArrayList<>();       // Copy of the song playlist (before the shuffling).
//    public ArrayList<String> music = new ArrayList<>();                 // Song playlist.
//    public ArrayList<String> notmp3 = new ArrayList<>();                // list of non-mp3 files.
//    private InputStream file;
//
//    public CommandLine() {}
//    public void loop (Player player, String song) throws PlayerException, FileNotFoundException {
//        String songname;
//        songname = song.substring(song.lastIndexOf("/") + 1);           // to get the filename, from the path.
//        System.out.println("\nNow playing: " + ANSI_GREEN + songname + ANSI_RESET + " (on Repeat one)\n\nYou can always " +
//                "quit, by entering " + ANSI_RED + "Ctrl+C" + ANSI_RESET + " or "  + ANSI_RED + "Ctrl+Z\n" + ANSI_RESET);
//        while (true){
//            file = new FileInputStream(song);
//            player.play(file);
//        }
//    }
//    public void order(Player player, String song, String choice, String path) throws PlayerException, FileNotFoundException{
//
//        int counter1 = 0;
//        System.out.println("\nYou can always quit, by entering " + ANSI_RED + "Ctrl+C" + ANSI_RESET + " or "  +
//                ANSI_RED + "Ctrl+Z\n" + ANSI_RESET);
//        if (choice.equals("order") || choice.equals("mp3Order")) {          // Playlist is played in order.
//            for (String item : songs) {
//                file = new FileInputStream(item);
//                System.out.println("\nNow Playing: " + ANSI_GREEN + item.substring(item.lastIndexOf("/") + 1) + ANSI_RESET);
//                if (counter1 == songs.size() - 1) {             // To print the end of the playlist.
//                    System.out.println("You reached " + ANSI_RED + "the end of the playlist." + ANSI_RESET + "\nHope you enjoyed it!\n");
//                    player.play(file);
//                    System.out.println("Thanks for using our media player! " + ANSI_GREEN +  "Have a great day!\n" + ANSI_RESET);
//                    try {
//                        Thread.sleep(1000);                  // To wait a second before exiting, making it more realistic.
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    return;
//                }
//                counter1++;
//                System.out.println("Next song: " + songs.get(counter1).substring(songs.get(counter1).lastIndexOf("/") + 1));    // To show the song's name, extracted from the path.
//                player.play(file);
//                System.out.println("Next song loading...");
//                try {
//                    Thread.sleep(1000);                     // To wait a second before playing the next one, making it more realistic.
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        } else if (choice.equals("m3uOrder") || song.endsWith(".m3u")) {        // To play songs from m3u file, in order.
//
//            for (String loop : music) {
//                if (loop.startsWith("/") || loop.startsWith("..")) {            // To play the m3u file, given with a path
//                    file = new FileInputStream(loop);
//                } else {
//                    file = new FileInputStream( path + loop);             // To play it, without a path given
//                }
//                System.out.println("\nNow Playing: " + ANSI_GREEN + loop.substring(loop.lastIndexOf("/") + 1) + ANSI_RESET);
//                if (counter1 == music.size() - 1) {
//                    System.out.println("You reached " + ANSI_RED + "the end of the playlist." + ANSI_RESET + "\nHope you enjoyed it!\n");
//                    player.play(file);
//                    System.out.println("Thanks for using our media player! " + ANSI_GREEN +  "Have a great day!\n" + ANSI_RESET);
//                    try {
//                        Thread.sleep(1000);                 // To wait a second before exiting, making it more realistic.
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    return;
//                }
//                counter1++;
//                System.out.println("Next song: " + music.get(counter1).substring(music.get(counter1).lastIndexOf("/") + 1));
//                player.play(file);
//                System.out.println("Next song loading...");
//                try {
//                    Thread.sleep(1000);                     // To wait a second before playing the next one, making it more realistic.
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//    }
//    public void random (Player player, String path) throws PlayerException, FileNotFoundException {
//        oldArray.addAll(music);  //για να κρατήοσουμε και την παλιά playlist
//        Collections.shuffle(music);
//        System.out.println("\nYou can always quit, by entering " + ANSI_RED + "Ctrl+C" + ANSI_RESET + " or "  +
//                ANSI_RED + "Ctrl+Z\n" + ANSI_RESET);
//        for (int z = 0; z < music.size(); z++) {
//            if (music.get(z).startsWith("/")) {
//                System.out.println("Now Playing: " + ANSI_GREEN + music.get(z).substring(music.get(z).lastIndexOf("/") + 1) + ANSI_RESET + " (on Shuffle)");
//                file = new FileInputStream(music.get(z));
//            } else {
//                System.out.println("Now Playing: " + ANSI_GREEN + music.get(z) + ANSI_RESET + " (on Shuffle)");
//                file = new FileInputStream(path + music.get(z));
//            }
//            if (z < music.size() - 1) {
//                System.out.println("Next song in playlist: " + music.get(z + 1).substring(music.get(z + 1).lastIndexOf("/") + 1) + "\n");
//            } else {
//                System.out.println("You reached " + ANSI_RED +  "the end of the playlist" + ANSI_RESET + ". Hope you enjoyed it!");
//            }
//            player.play(file);
//        }
//        System.out.println("Thanks for using our media player! " + ANSI_GREEN +  "Have a great day!\n" + ANSI_RESET);
//        player.close();
//    }
//
//
//    public void choice (String song, String choice, Player player, String path) throws PlayerException, IOException {
//        switch (choice) {
//            case "order":
//                FileHandling fileHandling = new FileHandling();
//                if (song.endsWith(".m3u")) {        // m3u file
//                    fileHandling.openerM3u(song, music);
//                    choice = "m3uOrder";
//                    order(player, song, choice, path);   // played in order.
//                } else {
//                    final File folder = new File(song);
//                    fileHandling.opener(folder, songs, notmp3);//βαζω το path του φακελου και οχι το τραγουδι
//                    order(player, song, choice, path);
//                    file.close();
//                }
//                break;
//            case "loop":
//                loop(player, song);
//                break;
//            case "random":              // randomly played songs in m3u file
//                fileHandling = new FileHandling();
//                fileHandling.openerM3u(song, music);
//                random(player, path);
//                break;
//            case "mp3Order":
//                if (song.endsWith(".mp3")) {    // mp3 file
//                    file = new FileInputStream(song);
//                    System.out.println("\nNow Playing: " + ANSI_GREEN + song.substring(song.lastIndexOf("/") + 1) +
//                            ANSI_RESET + " (to the end)\n\nYou can always quit, by entering " + ANSI_RED + "Ctrl+C" +
//                            ANSI_RESET + " or " + ANSI_RED + "Ctrl+Z\n" + ANSI_RESET);
//                    player.play(file);          // played once.
//                } else {                        // directory
//                    fileHandling = new FileHandling();
//                    final File folder = new File(song);
//                    fileHandling.opener(folder, songs, notmp3);
//                    order(player, song, choice, path);     // played in order.
//                    file.close();
//                }
//                break;
//            case "m3uOrder":
//                fileHandling = new FileHandling();
//                fileHandling.openerM3u(song, music);
//                order(player, song, choice, path);
//                break;
//            default:
//                System.out.println("Please check the given arguments.\n");
//                System.exit(1);
//        }
//    }
//}
