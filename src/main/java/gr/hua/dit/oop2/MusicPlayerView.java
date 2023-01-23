package gr.hua.dit.oop2;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import gr.hua.dit.oop2.musicplayer.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import static gr.hua.dit.oop2.MusicPlayerController.songs;

public class MusicPlayerView extends JFrame {

    private final JButton playButton;
    private final JButton pauseButton;
    private final JButton nextButton;
    private final JButton prevButton;
    private final JButton stopButton;
    private final JButton shuffleButton;
    private final JButton repeatButton;
    private final JList<String> songList;
    private static JProgressBar progressBar;
    private boolean isShuffled = false;
    private boolean isRepeat = false;
    private boolean isPlaying = false;

    public MusicPlayerView(MusicPlayerController controller) throws IOException {

        progressBar = new JProgressBar();

        // Setting the window.
        setTitle("Music Player");
        setSize(1300, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setting the metadata appearance.
        JTextField myOutput = new JTextField(100);
        myOutput.setFont(new Font("Serif", Font.ITALIC|Font.BOLD, 15));
        myOutput.setText("Title: \t\tArtist: \t\tAlbum: ");
        myOutput.setSelectedTextColor(Color.RED);
        myOutput.setPreferredSize(new Dimension(5,40));

        MusicPlayerModel model = new MusicPlayerModel();

        // Creating the media buttons.
        playButton = new JButton("");
        pauseButton = new JButton("");
        nextButton = new JButton("");
        prevButton = new JButton("");
        stopButton = new JButton("");
        shuffleButton = new JButton("");
        repeatButton = new JButton("");
        songList = new JList<String>();

        // Setting play button appearance.
        ImageIcon playIcon = new ImageIcon("src/main/resources/play.png");
        Image image = playIcon.getImage();
        image = image.getScaledInstance(105, 105, Image.SCALE_SMOOTH);
        playIcon = new ImageIcon(image);
        playButton.setIcon(playIcon);
        playButton.setPreferredSize(new Dimension(90,90));
        playButton.setHorizontalTextPosition(SwingConstants.CENTER);
        playButton.setVerticalTextPosition(SwingConstants.CENTER);

        // Setting a listener for the play button appearance, song metadata & progress bar.
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (isPlaying) {
                    ImageIcon playOnIcon = new ImageIcon("src/main/resources/playon.png");
                    Image image = playOnIcon.getImage();
                    image = image.getScaledInstance(105, 105, Image.SCALE_SMOOTH);
                    playOnIcon = new ImageIcon(image);
                    playButton.setIcon(playOnIcon);
                    playButton.setPreferredSize(new Dimension(90,90));
                    playButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    playButton.setVerticalTextPosition(SwingConstants.CENTER);
                    ImageIcon pauseIcon = new ImageIcon("src/main/resources/pause.png");
                    Image image1 = pauseIcon.getImage();
                    image1 = image1.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
                    pauseIcon = new ImageIcon(image1);
                    pauseButton.setIcon(pauseIcon);
                    pauseButton.setPreferredSize(new Dimension(80,80));
                    pauseButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    pauseButton.setVerticalTextPosition(SwingConstants.CENTER);
                    ImageIcon stopIcon = new ImageIcon("src/main/resources/stop.png");
                    Image image4 = stopIcon.getImage();
                    image4 = image4.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
                    stopIcon = new ImageIcon(image4);
                    stopButton.setIcon(stopIcon);
                    stopButton.setPreferredSize(new Dimension(80,80));
                    stopButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    stopButton.setVerticalTextPosition(SwingConstants.CENTER);
                }
                try {
                    myOutput.setText(model.mp3MetaData(model.getCurrentSong()));
                    progressBar.setMinimum(0);
                    progressBar.setMaximum(model.getCurrentSongLength());
                } catch (InvalidDataException | UnsupportedTagException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Setting the pause button appearance.
        ImageIcon pauseIcon = new ImageIcon("src/main/resources/pause.png");
        Image image1 = pauseIcon.getImage();
        image1 = image1.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
        pauseIcon = new ImageIcon(image1);
        pauseButton.setIcon(pauseIcon);
        pauseButton.setPreferredSize(new Dimension(80,80));
        pauseButton.setHorizontalTextPosition(SwingConstants.CENTER);
        pauseButton.setVerticalTextPosition(SwingConstants.CENTER);

        // Setting a listener for the pause button appearance.
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!isPlaying) {
                    ImageIcon pauseOnIcon = new ImageIcon("src/main/resources/pauseon.png");
                    Image image1 = pauseOnIcon.getImage();
                    image1 = image1.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
                    pauseOnIcon = new ImageIcon(image1);
                    pauseButton.setIcon(pauseOnIcon);
                    pauseButton.setPreferredSize(new Dimension(80,80));
                    pauseButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    pauseButton.setVerticalTextPosition(SwingConstants.CENTER);
                    ImageIcon playIcon = new ImageIcon("src/main/resources/play.png");
                    Image image = playIcon.getImage();
                    image = image.getScaledInstance(105, 105, Image.SCALE_SMOOTH);
                    playIcon = new ImageIcon(image);
                    playButton.setIcon(playIcon);
                    playButton.setPreferredSize(new Dimension(90,90));
                    playButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    playButton.setVerticalTextPosition(SwingConstants.CENTER);
                    ImageIcon stopIcon = new ImageIcon("src/main/resources/stop.png");
                    Image image4 = stopIcon.getImage();
                    image4 = image4.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
                    stopIcon = new ImageIcon(image4);
                    stopButton.setIcon(stopIcon);
                    stopButton.setPreferredSize(new Dimension(80,80));
                    stopButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    stopButton.setVerticalTextPosition(SwingConstants.CENTER);
                }
            }
        });

        // Setting the stop button appearance.
        ImageIcon stopIcon = new ImageIcon("src/main/resources/stop.png");
        Image image4 = stopIcon.getImage();
        image4 = image4.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
        stopIcon = new ImageIcon(image4);
        stopButton.setIcon(stopIcon);
        stopButton.setPreferredSize(new Dimension(80,80));
        stopButton.setHorizontalTextPosition(SwingConstants.CENTER);
        stopButton.setVerticalTextPosition(SwingConstants.CENTER);

        // Setting a listener for the stop button appearance.
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!isPlaying) {
                    ImageIcon stopOnIcon = new ImageIcon("src/main/resources/stopon.png");
                    Image image4 = stopOnIcon.getImage();
                    image4 = image4.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
                    stopOnIcon = new ImageIcon(image4);
                    stopButton.setIcon(stopOnIcon);
                    stopButton.setPreferredSize(new Dimension(80,80));
                    stopButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    stopButton.setVerticalTextPosition(SwingConstants.CENTER);
                    ImageIcon playIcon = new ImageIcon("src/main/resources/play.png");
                    Image image = playIcon.getImage();
                    image = image.getScaledInstance(105, 105, Image.SCALE_SMOOTH);
                    playIcon = new ImageIcon(image);
                    playButton.setIcon(playIcon);
                    playButton.setPreferredSize(new Dimension(90,90));
                    playButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    playButton.setVerticalTextPosition(SwingConstants.CENTER);
                    ImageIcon pauseIcon = new ImageIcon("src/main/resources/pause.png");
                    Image image1 = pauseIcon.getImage();
                    image1 = image1.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
                    pauseIcon = new ImageIcon(image1);
                    pauseButton.setIcon(pauseIcon);
                    pauseButton.setPreferredSize(new Dimension(80,80));
                    pauseButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    pauseButton.setVerticalTextPosition(SwingConstants.CENTER);
                }
            }
        });

        // Setting the next-song button appearance.
        ImageIcon nextIcon = new ImageIcon("src/main/resources/next.png");
        Image image2 = nextIcon.getImage();
        image2 = image2.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
        nextIcon = new ImageIcon(image2);
        nextButton.setIcon(nextIcon);
        nextButton.setPreferredSize(new Dimension(80,80));
        nextButton.setHorizontalTextPosition(SwingConstants.CENTER);
        nextButton.setVerticalTextPosition(SwingConstants.CENTER);
        nextButton.setHorizontalTextPosition(SwingConstants.CENTER);
        nextButton.setVerticalTextPosition(SwingConstants.CENTER);

        // Setting a listener for the next-song button appearance, song metadata & progress bar.
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!isPlaying) {
                    ImageIcon playOnIcon = new ImageIcon("src/main/resources/playon.png");
                    Image image = playOnIcon.getImage();
                    image = image.getScaledInstance(105, 105, Image.SCALE_SMOOTH);
                    playOnIcon = new ImageIcon(image);
                    playButton.setIcon(playOnIcon);
                    playButton.setPreferredSize(new Dimension(90,90));
                    playButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    playButton.setVerticalTextPosition(SwingConstants.CENTER);
                    ImageIcon pauseIcon = new ImageIcon("src/main/resources/pause.png");
                    Image image1 = pauseIcon.getImage();
                    image1 = image1.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
                    pauseIcon = new ImageIcon(image1);
                    pauseButton.setIcon(pauseIcon);
                    pauseButton.setPreferredSize(new Dimension(80,80));
                    pauseButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    pauseButton.setVerticalTextPosition(SwingConstants.CENTER);
                    ImageIcon stopIcon = new ImageIcon("src/main/resources/stop.png");
                    Image image4 = stopIcon.getImage();
                    image4 = image4.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
                    stopIcon = new ImageIcon(image4);
                    stopButton.setIcon(stopIcon);
                    stopButton.setPreferredSize(new Dimension(80,80));
                    stopButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    stopButton.setVerticalTextPosition(SwingConstants.CENTER);
                }
                try {
                    myOutput.setText(model.mp3MetaData(model.getCurrentSong()));
                    progressBar.setMinimum(0);
                    progressBar.setMaximum(model.getCurrentSongLength());
                } catch (InvalidDataException | UnsupportedTagException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Setting the previous-song button appearance.
        ImageIcon prevIcon = new ImageIcon("src/main/resources/prev.png");
        Image image3 = prevIcon.getImage();
        image3 = image3.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
        prevIcon = new ImageIcon(image3);
        prevButton.setIcon(prevIcon);
        prevButton.setPreferredSize(new Dimension(80,80));
        prevButton.setHorizontalTextPosition(SwingConstants.CENTER);
        prevButton.setVerticalTextPosition(SwingConstants.CENTER);

        // Setting a listener for the previous-song button appearance, song metadata & progress bar.
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!isPlaying) {
                    ImageIcon playOnIcon = new ImageIcon("src/main/resources/playon.png");
                    Image image = playOnIcon.getImage();
                    image = image.getScaledInstance(105, 105, Image.SCALE_SMOOTH);
                    playOnIcon = new ImageIcon(image);
                    playButton.setIcon(playOnIcon);
                    playButton.setPreferredSize(new Dimension(90,90));
                    playButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    playButton.setVerticalTextPosition(SwingConstants.CENTER);
                    ImageIcon pauseIcon = new ImageIcon("src/main/resources/pause.png");
                    Image image1 = pauseIcon.getImage();
                    image1 = image1.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
                    pauseIcon = new ImageIcon(image1);
                    pauseButton.setIcon(pauseIcon);
                    pauseButton.setPreferredSize(new Dimension(80,80));
                    pauseButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    pauseButton.setVerticalTextPosition(SwingConstants.CENTER);
                    ImageIcon stopIcon = new ImageIcon("src/main/resources/stop.png");
                    Image image4 = stopIcon.getImage();
                    image4 = image4.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
                    stopIcon = new ImageIcon(image4);
                    stopButton.setIcon(stopIcon);
                    stopButton.setPreferredSize(new Dimension(80,80));
                    stopButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    stopButton.setVerticalTextPosition(SwingConstants.CENTER);
                }
                try {
                    myOutput.setText(model.mp3MetaData(model.getCurrentSong()));
                    progressBar.setMinimum(0);
                    progressBar.setMaximum(model.getCurrentSongLength());
                } catch (InvalidDataException | UnsupportedTagException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Setting the shuffle button appearance.
        ImageIcon shuffleIcon = new ImageIcon("src/main/resources/shuffle.png");
        Image image5 = shuffleIcon.getImage();
        image5 = image5.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        shuffleIcon = new ImageIcon(image5);
        shuffleButton.setIcon(shuffleIcon);
        shuffleButton.setPreferredSize(new Dimension(50, 50));
        shuffleButton.setHorizontalTextPosition(SwingConstants.CENTER);
        shuffleButton.setVerticalTextPosition(SwingConstants.CENTER);

        // Setting a listener for the shuffle button appearance.
        shuffleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!isShuffled) {
                    ImageIcon shuffleIcon = new ImageIcon("src/main/resources/shuffle.png");
                    Image image5 = shuffleIcon.getImage();
                    image5 = image5.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
                    shuffleIcon = new ImageIcon(image5);
                    shuffleButton.setIcon(shuffleIcon);
                    shuffleButton.setPreferredSize(new Dimension(50, 50));
                    shuffleButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    shuffleButton.setVerticalTextPosition(SwingConstants.CENTER);
                } else {
                    ImageIcon shuffleOnIcon = new ImageIcon("src/main/resources/shuffleon.png");
                    Image image6 = shuffleOnIcon.getImage();
                    image6 = image6.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
                    shuffleOnIcon = new ImageIcon(image6);
                    shuffleButton.setIcon(shuffleOnIcon);
                    shuffleButton.setPreferredSize(new Dimension(50, 50));
                    shuffleButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    shuffleButton.setVerticalTextPosition(SwingConstants.CENTER);
                }
            }
        });

        // Setting the repeat button appearance.
        ImageIcon repeatIcon = new ImageIcon("src/main/resources/repeat.png");
        Image image7 = repeatIcon.getImage();
        image7 = image7.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        repeatIcon = new ImageIcon(image7);
        repeatButton.setIcon(repeatIcon);
        repeatButton.setPreferredSize(new Dimension(50,50));
        repeatButton.setHorizontalTextPosition(SwingConstants.CENTER);
        repeatButton.setVerticalTextPosition(SwingConstants.CENTER);

        // Setting a listener for the repeat button appearance.
        repeatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!isRepeat) {
                    ImageIcon repeatIcon = new ImageIcon("src/main/resources/repeat.png");
                    Image image7 = repeatIcon.getImage();
                    image7 = image7.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
                    repeatIcon = new ImageIcon(image7);
                    repeatButton.setIcon(repeatIcon);
                    repeatButton.setPreferredSize(new Dimension(50,50));
                    repeatButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    repeatButton.setVerticalTextPosition(SwingConstants.CENTER);
                } else {
                    ImageIcon repeatOnIcon = new ImageIcon("src/main/resources/repeaton.png");
                    Image image8 = repeatOnIcon.getImage();
                    image8 = image8.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
                    repeatOnIcon = new ImageIcon(image8);
                    repeatButton.setIcon(repeatOnIcon);
                    repeatButton.setPreferredSize(new Dimension(50,50));
                    repeatButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    repeatButton.setVerticalTextPosition(SwingConstants.CENTER);
                }
            }
        });

        // Setting the background image.
        Image backgroundImage = ImageIO.read(new File("src/main/resources/background.jpg"));
        this.setContentPane(new JLabel(new ImageIcon(backgroundImage)));
        setLocationRelativeTo(null);
        setVisible(true);

//        JPanel contentPanel = new JPanel(new BorderLayout());
//        contentPanel.add(new JLabel(new ImageIcon(backgroundImage)), BorderLayout.NORTH);
//        contentPanel.setOpaque(false);

        // Setting the song-list panel, the list (size, fonts etc.), and adding the song-list to the panel.
        JScrollPane songListPane = new JScrollPane(songList);
        songListPane.setPreferredSize(new Dimension(300,550));
        songList.setFont(new Font("Serif", Font.BOLD|Font.ITALIC, 17));
        songList.setPreferredSize(new Dimension(250,540));
        JPanel listPanel = new JPanel();
        listPanel.add(songListPane);

        // Setting the button panel, and adding the buttons to the panel.
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(shuffleButton);
        buttonPanel.add(prevButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(playButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(repeatButton);
        buttonPanel.setOpaque(false);

        // Setting a listener for the double-click song selection, buttons' appearance, song metadata & progress bar.
        songList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                model.setNextPrevClick(true);
                if (e.getClickCount() == 2) {
                    ImageIcon playOnIcon = new ImageIcon("src/main/resources/playon.png");
                    Image image = playOnIcon.getImage();
                    image = image.getScaledInstance(105, 105, Image.SCALE_SMOOTH);
                    playOnIcon = new ImageIcon(image);
                    playButton.setIcon(playOnIcon);
                    playButton.setPreferredSize(new Dimension(90,90));
                    playButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    playButton.setVerticalTextPosition(SwingConstants.CENTER);
                    ImageIcon pauseIcon = new ImageIcon("src/main/resources/pause.png");
                    Image image1 = pauseIcon.getImage();
                    image1 = image1.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
                    pauseIcon = new ImageIcon(image1);
                    pauseButton.setIcon(pauseIcon);
                    pauseButton.setPreferredSize(new Dimension(80,80));
                    pauseButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    pauseButton.setVerticalTextPosition(SwingConstants.CENTER);
                    ImageIcon stopIcon = new ImageIcon("src/main/resources/stop.png");
                    Image image4 = stopIcon.getImage();
                    image4 = image4.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
                    stopIcon = new ImageIcon(image4);
                    stopButton.setIcon(stopIcon);
                    stopButton.setPreferredSize(new Dimension(80,80));
                    stopButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    stopButton.setVerticalTextPosition(SwingConstants.CENTER);

                    model.setCurrentSongIndex(songList.locationToIndex(e.getPoint()));
                    try {
                        myOutput.setText(model.mp3MetaData(songs.get(songList.locationToIndex(e.getPoint()))));
                        progressBar.setMinimum(0);
                        progressBar.setMaximum(model.getCurrentSongLength());
                    } catch (InvalidDataException | UnsupportedTagException | IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        model.clickedPlay(songs);
                    } catch (InterruptedException | PlayerException | IOException | InvalidDataException |
                             UnsupportedTagException ex) {
                        //org.hua.LogHandler.writeToLogNoThread(Level.SEVERE,"RuntimeException");
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        // Setting the file chooser, for the user to select a directory/file
        final JFileChooser fc = new JFileChooser("~"); // It starts from the /home/'user'
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);    // User can choose either a file or a directory.
        int folder = fc.showOpenDialog(null);
        if(folder == JFileChooser.APPROVE_OPTION) {
            File directory = fc.getSelectedFile();
            controller.setSongsInFolder(String.valueOf(directory), this);   // Making the song-list.
        }

        // Setting a listener to fix a metadata bug.
        model.getPlayer().addPlayerListener(new PlayerListener() {
            @Override
            public void statusUpdated(PlayerEvent playerEvent) {
                if (playerEvent.getStatus() == Player.Status.IDLE && !model.getStopPressed() && !model.getNextPrevClick()) {
                    try {
                        myOutput.setText(model.mp3MetaData(model.getCurrentSong()));
                        progressBar.setMinimum(0);
                        progressBar.setMaximum(model.getCurrentSongLength());
                    } catch (InvalidDataException | UnsupportedTagException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        // Setting a listener for the progress bar.
        model.getPlayer().addProgressListener(new ProgressListener() {
            @Override
            public void progress(ProgressEvent progressEvent) {
                progressBar.setValue((int) progressEvent.getMicroseconds()/1000000);
            }
        });

        // Setting a layout, adding the progress bar, metadata and button panel to the southPanel.
        setLayout(new BorderLayout());
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.add(progressBar);
        southPanel.add(myOutput);
        southPanel.add(buttonPanel);
        southPanel.setOpaque(false);

        // Adding the south panel and the song-list panel to the Layout.
        add(southPanel, BorderLayout.SOUTH);
        add(listPanel, BorderLayout.WEST);

        // Setting the controller's view to this view.
        controller.setView(this);
    }

    // To set the boolean variable, if a song is being played. (for if-statements-use)
    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    // To set the boolean variable, if the shuffle button is pressed. (for if-statements-use)
    public void setShuffleOn (boolean isShuffled) {
        this.isShuffled = isShuffled;
    }

    // To set the boolean variable, if the repeat button is pressed. (for if-statements-use)
    public void setRepeatOn (boolean isRepeat) {
        this.isRepeat = isRepeat;
    }

    // Play button getter.
    public JButton getPlayButton() {
        return playButton;
    }

    // Pause button getter.
    public JButton getPauseButton() {
        return pauseButton;
    }

    // Next-song button getter.
    public JButton getNextButton() {
        return nextButton;
    }

    // Previous-song button getter.
    public JButton getPrevButton() {
        return prevButton;
    }

    // Stop button getter.
    public JButton getStopButton() {
        return stopButton;
    }

    // Shuffle button getter.
    public JButton getShuffleButton() {
        return shuffleButton;
    }

    // Repeat button getter.
    public JButton getRepeatButton() {
        return repeatButton;
    }

    // Song list getter.
    public JList<String> getSongList() {
        return songList;
    }
}