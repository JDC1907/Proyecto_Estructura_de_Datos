package com.mycompany.agenda;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PreCarga1 implements Initializable {
    private static Stage stage;
    private  static Scene scene;
    private static final String icon = "img/icono2.png";
    @FXML
    private Pane body;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new preloader().start();
    }
    class preloader extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep(3000);
                Platform.runLater(() -> {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("EstiloGestorContacto.fxml"));
                    }catch (IOException e) {
                    }
                    scene = new Scene(root, 900, 720);
                    stage = new Stage();
                    stage.setTitle("ContactYou");
                    stage.getIcons().add(new Image(icon) );
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                    
                    body.getScene().getWindow().hide();
                });
            } catch (InterruptedException e) {
            }
        }
    }

    public static Stage getStage() {
        return stage;
    }

    public static Scene getScene() {
        return scene;
    }

}
