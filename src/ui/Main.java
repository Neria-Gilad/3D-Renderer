package ui;

/**
 * Created by Neria Tzidkani on 13/06/2017.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.*;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ui.fxml"));
        primaryStage.setTitle("Neria & Gilad");
		primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 700, 800));
        primaryStage.show();
    }
}
