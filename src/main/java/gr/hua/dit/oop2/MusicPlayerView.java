package gr.hua.dit.oop2;

import gr.hua.dit.oop2.musicplayer.PlayerException;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
// import java.io.Serial;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MusicPlayerView extends JFrame {
//     @Serial
//     private static final long serialVersionUID = 1L;
    private final JButton playButton;
    private final JButton pauseButton;
    private final JButton nextButton;
    private final JButton prevButton;
    private final JButton stopButton;
    private final JButton shuffleButton;
    private final JButton repeatButton;
    private final JList<String> songList;

    public MusicPlayerView(MusicPlayerController controller) throws IOException {
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

        ImageIcon pauseIcon = new ImageIcon("src/main/resources/pause.png");
        Image image1 = pauseIcon.getImage();
        image1 = image1.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
        pauseIcon = new ImageIcon(image1);
        pauseButton.setIcon(pauseIcon);
        pauseButton.setPreferredSize(new Dimension(80,80));
        pauseButton.setHorizontalTextPosition(SwingConstants.CENTER);
        pauseButton.setVerticalTextPosition(SwingConstants.CENTER);

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


        ImageIcon prevIcon = new ImageIcon("src/main/resources/prev.png");
        Image image3 = prevIcon.getImage();
        image3 = image3.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
        prevIcon = new ImageIcon(image3);
        prevButton.setIcon(prevIcon);
        prevButton.setPreferredSize(new Dimension(80,80));
        prevButton.setHorizontalTextPosition(SwingConstants.CENTER);
        prevButton.setVerticalTextPosition(SwingConstants.CENTER);

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
        shuffleButton.setPreferredSize(new Dimension(50,50));
        shuffleButton.setHorizontalTextPosition(SwingConstants.CENTER);
        shuffleButton.setVerticalTextPosition(SwingConstants.CENTER);

        ImageIcon repeatIcon = new ImageIcon("src/main/resources/repeat.png");
        Image image6 = repeatIcon.getImage();
        image6 = image6.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        repeatIcon = new ImageIcon(image6);
        repeatButton.setIcon(repeatIcon);
        repeatButton.setPreferredSize(new Dimension(50,50));
        repeatButton.setHorizontalTextPosition(SwingConstants.CENTER);
        repeatButton.setVerticalTextPosition(SwingConstants.CENTER);

        Image backgroundImage = ImageIO.read(new File("src/main/resources/background.jpg"));

        this.setContentPane(new JLabel(new ImageIcon(backgroundImage)));

        setLocationRelativeTo(null);
        setVisible(true);

        JPanel contentPanel = new JPanel(new BorderLayout());

        contentPanel.setOpaque(false);
        contentPanel.add(new JLabel(new ImageIcon(backgroundImage)), BorderLayout.NORTH);


        JScrollPane songListPane = new JScrollPane(songList);

        songListPane.setPreferredSize(new Dimension(200,700));

        // Add the buttons to a panel
        JPanel buttonPanel = new JPanel();
        JPanel listPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(shuffleButton);
        buttonPanel.add(prevButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(playButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(repeatButton);
        listPanel.add(songListPane);

        songList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MusicPlayerModel model = new MusicPlayerModel();
                if (e.getClickCount() == 2) {
                    int index = songList.locationToIndex(e.getPoint());
                    try {
                        model.clickedPlay(controller.songs, index);
                    } catch (InterruptedException | FileNotFoundException | PlayerException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        // Ask user to input folder path
        String folder = JOptionPane.showInputDialog(this, "Please enter the folder name with its path: ");
        controller.setSongsInFolder(folder,this);

        // Add the panel to the frame
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.SOUTH);
        add(listPanel, BorderLayout.WEST);

        // Set the controller as the action listener for the buttons
        controller.setView(this);
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
