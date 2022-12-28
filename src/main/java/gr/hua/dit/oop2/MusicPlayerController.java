package gr.hua.dit.oop2;

import gr.hua.dit.oop2.musicplayer.PlayerException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
public class MusicPlayerController implements ActionListener {
    static ArrayList<String> songs = new ArrayList<String>();
    private final MusicPlayerModel model;
    private MusicPlayerView view;
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
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == view.getPauseButton()) {
            model.pause();
        } else if (e.getSource() == view.getStopButton()) {
            model.stop();
        } else if (e.getSource() == view.getNextButton()) {
            try {
                model.next(songs);
            } catch (PlayerException | InterruptedException | FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == view.getPrevButton()) {
            try {
                model.prev(songs);
            } catch (InterruptedException | PlayerException | FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == view.getShuffleButton()) {
            // TODO
            // model.shuffle();
        } else if (e.getSource() == view.getRepeatButton()) {
            // TODO
            // model.repeat();
        }
    }
}

