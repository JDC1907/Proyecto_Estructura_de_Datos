package com.mycompany.agenda;

import agenda.Contacto;
import agenda.Persona;
import agenda.Sistema;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;
import tda.LinkedList;
import tda.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static HashSet tags;
    //public static List<Contacto> contactos = new LinkedList();
    private static Stage stage;
    private static final String icon = "img/icono2.png";

    @Override
    public void start(Stage stage) throws IOException {
//       cargarContactos(contactos);
        scene = new Scene(loadFXML("precarga1"), 600, 379);
        stage.setScene(scene);
        stage.setTitle("ContactYou");
        stage.getIcons().add(new Image(icon));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        Sistema.inicializarSistema();
        launch();
    }
    
//    public static void cargarContactos(List<Contacto> contactos){
//        contactos.addLast(new Persona("Nombre1", "123456"));
//        contactos.addLast(new Persona("Nombre2", "087555"));
//        contactos.addLast(new Persona("Nombre3", "023456"));
//        contactos.addLast(new Persona("Nombre4", "123456"));
//        contactos.addLast(new Persona("Nombre5", "123456"));
//        contactos.addLast(new Persona("Nombre6", "123456"));
//        contactos.addLast(new Persona("Nombre7", "123456"));
//        contactos.addLast(new Persona("Nombre8", "123456"));
//        contactos.addLast(new Persona("Nombre9", "123456"));
//        contactos.addLast(new Persona("Nombre10", "123456"));
//        contactos.addLast(new Persona("Nombre11", "123456"));
//        Persona p = new Persona("Juan Carlos", "095555555");
//        p.setAtributte("Usuario twitter", "@Twitter");
//        p.setAtributte("Usuario fb", "@Facebook");
//        p.setAtributte("Cumpleaños", "12/15/15");
//        p.addPhoto("/img/persona7.jpg");
//        contactos.addLast(p);
//    }

}