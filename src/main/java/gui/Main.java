package gui;

import java.io.IOException;

import alan.Alan;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Alan alan = Alan.getInstance();

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Monkey See Monkey Do");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBot(alan);
            fxmlLoader.<MainWindow>getController().sendIntro();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}