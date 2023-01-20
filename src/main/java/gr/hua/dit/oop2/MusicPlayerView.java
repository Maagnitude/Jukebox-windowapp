package gr.hua.dit.oop2;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import gr.hua.dit.oop2.musicplayer.PlayerException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

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
    private boolean isShuffled = false;
    private boolean isRepeat = false;
    private boolean isPlaying = false;



    private  int i =0;

    public MusicPlayerView(MusicPlayerController controller) throws IOException, InvalidDataException, UnsupportedTagException {

        setTitle("Music Player");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        // Create the media buttons
        playButton = new JButton("");
        pauseButton = new JButton("");
        nextButton = new JButton("");
        prevButton = new JButton("");
        stopButton = new JButton("");
        shuffleButton = new JButton("");
        repeatButton = new JButton("");
        songList = new JList<String>();

        ImageIcon playIcon = new ImageIcon("src/main/resources/play.png");
        Image image = playIcon.getImage();
        image = image.getScaledInstance(105, 105, Image.SCALE_SMOOTH);
        playIcon = new ImageIcon(image);
        playButton.setIcon(playIcon);
        playButton.setPreferredSize(new Dimension(90,90));
        playButton.setHorizontalTextPosition(SwingConstants.CENTER);
        playButton.setVerticalTextPosition(SwingConstants.CENTER);

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
            }
        });

        ImageIcon pauseIcon = new ImageIcon("src/main/resources/pause.png");
        Image image1 = pauseIcon.getImage();
        image1 = image1.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
        pauseIcon = new ImageIcon(image1);
        pauseButton.setIcon(pauseIcon);
        pauseButton.setPreferredSize(new Dimension(80,80));
        pauseButton.setHorizontalTextPosition(SwingConstants.CENTER);
        pauseButton.setVerticalTextPosition(SwingConstants.CENTER);

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
            }
        });

        ImageIcon prevIcon = new ImageIcon("src/main/resources/prev.png");
        Image image3 = prevIcon.getImage();
        image3 = image3.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
        prevIcon = new ImageIcon(image3);
        prevButton.setIcon(prevIcon);
        prevButton.setPreferredSize(new Dimension(80,80));
        prevButton.setHorizontalTextPosition(SwingConstants.CENTER);
        prevButton.setVerticalTextPosition(SwingConstants.CENTER);

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
            }
        });

        ImageIcon stopIcon = new ImageIcon("src/main/resources/stop.png");
        Image image4 = stopIcon.getImage();
        image4 = image4.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
        stopIcon = new ImageIcon(image4);
        stopButton.setIcon(stopIcon);
        stopButton.setPreferredSize(new Dimension(80,80));
        stopButton.setHorizontalTextPosition(SwingConstants.CENTER);
        stopButton.setVerticalTextPosition(SwingConstants.CENTER);

        ImageIcon shuffleIcon = new ImageIcon("src/main/resources/shuffle.png");
        Image image5 = shuffleIcon.getImage();
        image5 = image5.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        shuffleIcon = new ImageIcon(image5);
        shuffleButton.setIcon(shuffleIcon);
        shuffleButton.setPreferredSize(new Dimension(50, 50));
        shuffleButton.setHorizontalTextPosition(SwingConstants.CENTER);
        shuffleButton.setVerticalTextPosition(SwingConstants.CENTER);

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

        ImageIcon repeatIcon = new ImageIcon("src/main/resources/repeat.png");
        Image image7 = repeatIcon.getImage();
        image7 = image7.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        repeatIcon = new ImageIcon(image7);
        repeatButton.setIcon(repeatIcon);
        repeatButton.setPreferredSize(new Dimension(50,50));
        repeatButton.setHorizontalTextPosition(SwingConstants.CENTER);
        repeatButton.setVerticalTextPosition(SwingConstants.CENTER);

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

//        int minimum = 0;
//        int maximum = 100;
//        JProgressBar jProgressBar = new JProgressBar(minimum, maximum);
//        jProgressBar.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent evt) {
//                JProgressBar comp = (JProgressBar) evt.getSource();
//                int value = comp.getValue();
//                int min = comp.getMinimum();
//                int max = comp.getMaximum();
//            }
//        });


        Image backgroundImage = ImageIO.read(new File("src/main/resources/background.jpg"));

        this.setContentPane(new JLabel(new ImageIcon(backgroundImage)));

        setLocationRelativeTo(null);
        setVisible(true);

        JPanel contentPanel = new JPanel(new BorderLayout());

        contentPanel.setOpaque(false);
        contentPanel.add(new JLabel(new ImageIcon(backgroundImage)), BorderLayout.NORTH);

        JScrollPane songListPane = new JScrollPane(songList);
        //JLabel text = new JLabel();

        songListPane.setPreferredSize(new Dimension(200,500));

        // Add the buttons to a panel
        JPanel buttonPanel = new JPanel();
        JPanel listPanel = new JPanel();
        JTextField myOutput = new JTextField(900);
        //JPanel textPanel = new JPanel();
        //JPanel barPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(shuffleButton);
        buttonPanel.add(prevButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(playButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(repeatButton);
        listPanel.add(songListPane);
        //textPanel.add(text);

        songList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                MusicPlayerModel model = new MusicPlayerModel();
                model.setNextPrevClick(true);

                if (e.getClickCount() == 2) {
                    i = (i%songs.size());
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
                        //text.setText(model.mp3MetaData(songs.get(songList.locationToIndex(e.getPoint()))));
                        myOutput.setText(model.mp3MetaData(songs.get(songList.locationToIndex(e.getPoint()))));
                    } catch (InvalidDataException ex) {
                        throw new RuntimeException(ex);
                    } catch (UnsupportedTagException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
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

        final JFileChooser fc = new JFileChooser("~");
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        // Ask user to input folder path
        int folder = fc.showOpenDialog(null);
        if(folder == JFileChooser.APPROVE_OPTION) {
            File directory = fc.getSelectedFile();
            controller.setSongsInFolder(String.valueOf(directory), this);
        }

        // Add the panel to the frame
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.SOUTH);
        add(listPanel, BorderLayout.WEST);
        //add(text,BorderLayout.CENTER);
        add(myOutput,BorderLayout.NORTH);
        //add(textPanel,BorderLayout.CENTER);
        //add(contentPanel, BorderLayout.CENTER);

        // Set the controller as the action listener for the buttons
        controller.setView(this);

    }

    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public void setShuffleOn (boolean isShuffled) {
        this.isShuffled = isShuffled;
    }

    public void setRepeatOn (boolean isRepeat) {
        this.isRepeat = isRepeat;
    }

    public JButton getPlayButton() {
        return playButton;
    }

    public JButton getPauseButton() {
        return pauseButton;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public JButton getPrevButton() {
        return prevButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public JButton getShuffleButton() {
        return shuffleButton;
    }

    public JButton getRepeatButton() {
        return repeatButton;
    }

    public JList<String> getSongList() {
        return songList;
    }
}
