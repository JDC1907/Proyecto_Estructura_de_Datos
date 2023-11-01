
package com.mycompany.agenda;

import agenda.Contacto;
import java.util.Iterator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tda.ArrayList;
import tda.List;

public class ContactosController {
    @FXML private VBox contactosVBox, datosContactoAtributosVBox;
    @FXML private ImageView contactoImg;
    @FXML private Button upContactsButton, downContactsButton;
    @FXML private HBox tagsHBox;
    private int indice = 0;
    @FXML
    private void initialize(){
        cargarContactos(agenda.Sistema.contactos.iterator());
        cargarTags();
    }
    
    private void cargarTags(){
        for(String tag: agenda.Sistema.tags){
            Button buttonTag = new Button(tag);
            buttonTag.setCursor(Cursor.HAND);
            buttonTag.setOnAction((e)->{
                List<Contacto> c = new ArrayList<>();
                for(Contacto contacto: agenda.Sistema.contactos){
                    if(contacto.getTags().contains(tag)){
                        c.addLast(contacto);
                    }
                }
                cargarContactos(c.iterator());
            }
            );
            tagsHBox.getChildren().add(buttonTag);
        }
    }
    private void cargarContactos(Iterator<Contacto> it){
        contactosVBox.getChildren().clear();
        if(agenda.Sistema.contactos.size()>6){
            upContactsButton.setDisable(false);
            downContactsButton.setDisable(false);
        }
        //Iterator<Contacto> it = agenda.Sistema.contactos.iterator();
        int numElementos = 0;
        while(it.hasNext() && numElementos<6){
            Contacto contacto = it.next();
            HBox cajaContacto = new HBox();
            cajaContacto.setCursor(Cursor.HAND);
            cajaContacto.setOnMouseClicked((e)->{
                cargarDatosContacto(contacto);
            });
            String photo = "/img/user.png";
            if(contacto.hasPhotos()){
                photo = contacto.getFirstPhoto();
            }
            cajaContacto.getChildren().add(new ImageView(new Image(photo, 64,64,false,false)));
            VBox datosContacto = new VBox();
            datosContacto.setAlignment(Pos.CENTER);
            datosContacto.getChildren().add(new Label(contacto.getName()));
            datosContacto.getChildren().add(new Label(contacto.getNumber()));
            cajaContacto.getChildren().add(datosContacto);
            contactosVBox.getChildren().add(cajaContacto);
            numElementos++;
        }
    }
    
    private void cargarDatosContacto(Contacto contacto){
        datosContactoAtributosVBox.getChildren().clear();
        String photo = "/img/user.png";
        if(contacto.hasPhotos()){
            photo = contacto.getFirstPhoto();
        }
        contactoImg.setImage(new Image(photo, 64,64,false,false));
        for(String key: contacto.getKeysAtributtes()){
            HBox datosContacto = new HBox();
            datosContacto.setAlignment(Pos.CENTER);
            datosContacto.setSpacing(10);
            datosContacto.getChildren().add(new Label(key));
            TextField campo = new TextField(contacto.getValor(key));
            campo.setEditable(false);
            datosContacto.getChildren().add(campo);
            datosContactoAtributosVBox.getChildren().add(datosContacto);
        }
    }
    
    @FXML
    private void retrocederContactos(){
        indice--;
        mostrarSeisContactos(indice);
        if(indice<0){
            indice=agenda.Sistema.contactos.size()-1;
        }
        
    }
    
    @FXML
    private void avanzarContactos(){
        indice++;
        mostrarSeisContactos(indice);
        if(indice>=agenda.Sistema.contactos.size()){
            indice=0;
        }
    }
    
    private void mostrarSeisContactos(int indice){
        contactosVBox.getChildren().clear();
        int numElementos = 0;
        while( numElementos<6){
            if(indice<0){
                indice=agenda.Sistema.contactos.size()-1;
                
            }else if(indice>=agenda.Sistema.contactos.size()){
                indice=0;
            }
            Contacto contacto = agenda.Sistema.contactos.get(indice);
            HBox cajaContacto = new HBox();
            cajaContacto.setCursor(Cursor.HAND);
            cajaContacto.setOnMouseClicked((e)->{
                cargarDatosContacto(contacto);
            });
            String photo = "/img/user.png";
            if(contacto.hasPhotos()){
                photo = contacto.getFirstPhoto();
            }
            cajaContacto.getChildren().add(new ImageView(new Image(photo, 64,64,false,false)));
            VBox datosContacto = new VBox();
            datosContacto.setAlignment(Pos.CENTER);
            datosContacto.getChildren().add(new Label(contacto.getName()));
            datosContacto.getChildren().add(new Label(contacto.getNumber()));
            cajaContacto.getChildren().add(datosContacto);
            contactosVBox.getChildren().add(cajaContacto);
            numElementos++;
            indice++;
        }
        
    }
    
    public void seleccionar(ActionEvent event){
        
        
    }
    
    
    public void editarDatos(ActionEvent event){
        
        
    }
}
