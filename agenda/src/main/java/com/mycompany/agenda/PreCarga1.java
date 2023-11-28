package com.mycompany.agenda;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
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
                    FXMLLoader internalLoader = null;
                    try {
                        //root = FXMLLoader.load(getClass().getResource("LoginUser.fxml"));
                        internalLoader = new FXMLLoader(App.class.getResource("LoginUser"+".fxml"));
                        root = internalLoader.load();
                    }catch (IOException e) {
                    }
                    //scene = new Scene(root, 900, 720);
                    scene = new Scene(root, 900, 700); //cuando le habilitemos el login descomentamos esto y borrarmos la de arriba
                    stage = new Stage();
                    scene.getStylesheets().add("estilo.css");
                    stage.setTitle("ContactYou");
                    stage.getIcons().add(new Image(icon) );
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                    body.getScene().getWindow().hide();
                    Login login = internalLoader.getController();
                    login.cargarStage(stage);
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
