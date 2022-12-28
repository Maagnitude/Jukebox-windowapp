package gr.hua.dit.oop2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        MusicPlayerModel model = new MusicPlayerModel();
        MusicPlayerController controller = new MusicPlayerController(model);
        MusicPlayerView view = new MusicPlayerView(controller);
        view.setVisible(true);
    }
}