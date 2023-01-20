package gr.hua.dit.oop2;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.IOException;
import java.util.logging.Level;

public class Main {

    public static void main(String[] args) throws IOException, InvalidDataException, UnsupportedTagException {
        org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Opening Mp3Player app...");
        MusicPlayerModel model = new MusicPlayerModel();
        MusicPlayerController controller = new MusicPlayerController(model);
        MusicPlayerView view = new MusicPlayerView(controller);
        view.setVisible(true);
    }
}