package gr.hua.dit.oop2;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import gr.hua.dit.oop2.musicplayer.PlayerException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;

public class MusicPlayerController implements ActionListener {
    static ArrayList<String> songs = new ArrayList<String>();
    private final MusicPlayerModel model;
    private MusicPlayerView view;

    private int change_mod_loop = 1;

    private int change_mod_shuffle = 1;
    private final JFileChooser fileChooser;
    public MusicPlayerController(MusicPlayerModel model) {
        this.model = model;
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new MP3FileFilter());
    }
    public void setSongsInFolder(String folder, MusicPlayerView view) {
        File dir = new File(folder);
        File[] files = dir.listFiles();
        DefaultListModel<String> model = new DefaultListModel<>();
        for (File file : files) {
            if (file.toString().endsWith(".mp3")) {
                songs.add(file.toString());
                model.addElement(file.getName());
            }
        }
        view.getSongList().setModel(model);
    }
    public void setView(MusicPlayerView view) {
        this.view = view;
        view.getPlayButton().addActionListener(this);
        view.getPauseButton().addActionListener(this);
        view.getNextButton().addActionListener(this);
        view.getPrevButton().addActionListener(this);
        view.getStopButton().addActionListener(this);
        view.getShuffleButton().addActionListener(this);
        view.getRepeatButton().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == view.getPlayButton()) {
            try {
                model.playSong(songs);
            } catch (PlayerException | FileNotFoundException | InterruptedException ex) {
                //org.hua.LogHandler.writeToLogNoThread(Level.SEVERE,"RuntimeException");
                throw new RuntimeException(ex);
            } catch (InvalidDataException | IOException | UnsupportedTagException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == view.getPauseButton()) {
            model.pause();
            //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Model paused");
        } else if (e.getSource() == view.getStopButton()) {
            model.stop();
            //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Model stopped");
        } else if (e.getSource() == view.getNextButton()) {
            try {
                model.next(songs);
            } catch (PlayerException | InterruptedException | FileNotFoundException ex) {
                org.hua.LogHandler.writeToLogNoThread(Level.SEVERE,"RuntimeException");
                throw new RuntimeException(ex);
            } catch (InvalidDataException | UnsupportedTagException | IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == view.getPrevButton()) {
            try {
                model.prev(songs);
            } catch (InterruptedException | PlayerException | IOException | InvalidDataException |
                     UnsupportedTagException ex) {
                //org.hua.LogHandler.writeToLogNoThread(Level.SEVERE,"RuntimeException");
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == view.getShuffleButton()) {
            // TODO
            try {
                if(change_mod_shuffle%2==0){
                    model.order(songs);
                    change_mod_shuffle = (change_mod_shuffle + 1) % 2;
                }
                else{
                    change_mod_shuffle = change_mod_shuffle + 1;
                }
                model.random(songs);
            } catch (PlayerException | InvalidDataException | UnsupportedTagException | IOException |
                     InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == view.getRepeatButton()) {
            // TODO
            try {
                if(change_mod_loop%2==0){
                    model.order(songs);
                    change_mod_loop = (change_mod_loop + 1) % 2;
                }
                else{
                    change_mod_loop = change_mod_loop + 1;
                }
                model.loop(songs);
            } catch (PlayerException | InvalidDataException | UnsupportedTagException | IOException |
                     InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

