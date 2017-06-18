package Innopolis.GUI;/**
 * Created by _red_ on 16.06.17.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientGUI extends Application {
    
    public static void main (String[] args) {
        launch(args);
    }
    
    @Override
    public void start (Stage primaryStage) throws IOException {
        Parent stage = FXMLLoader.load(getClass().getResource("test.fxml"));
        Scene scene = new Scene(stage , 412, 530);
        
        primaryStage.setTitle("FirstFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
