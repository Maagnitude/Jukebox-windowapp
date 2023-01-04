package gr.hua.dit.oop2;

import java.io.IOException;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) throws IOException {
        org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Starting the program");
        MusicPlayerModel model = new MusicPlayerModel();
        MusicPlayerController controller = new MusicPlayerController(model);
        MusicPlayerView view = new MusicPlayerView(controller);
        view.setVisible(true);
    }
}