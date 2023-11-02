
package com.mycompany.agenda;

import agenda.Contacto;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tda.ArrayList;
import tda.List;

public class ContactosController {
    private final int numeroContactosMostrados = 6;
    @FXML private VBox contactosVBox, datosContactoAtributosVBox, datosContactoVBox;
    @FXML private ImageView contactoImg;
    @FXML private Button upContactsButton, downContactsButton, deleteContactButton;
    @FXML private HBox tagsHBox;
    private int indice = 0;
    private Contacto contactoSeleccionado;
    private HBox cajaContactoSeleccionada;

    @FXML
    private void initialize(){
        cargarContactosPanelIzquierdo(agenda.Sistema.contactos);
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
                cargarContactosPanelIzquierdo(c);
            }
            );

            tagsHBox.getChildren().add(buttonTag);

        }
    }
    
    private void setDisableButtonsDownUp(boolean valor){
        upContactsButton.setDisable(valor);
        downContactsButton.setDisable(valor);
    }
    
    private void cargarContactosPanelIzquierdo(List lista){
        Iterator<Contacto> it = lista.iterator();
        contactosVBox.getChildren().clear();
        setDisableButtonsDownUp(true);
        if(lista.size()>numeroContactosMostrados){
            setDisableButtonsDownUp(false);
        }
        //Iterator<Contacto> it = agenda.Sistema.contactos.iterator();
        int numElementos = 0;
        while(it.hasNext() && numElementos<numeroContactosMostrados){
            Contacto contacto = it.next();
            mostrarContacto(contacto);
            numElementos++;
        }
    }
    
    private void cargarDatosContacto(Contacto contacto){
        datosContactoVBox.setVisible(true);
        datosContactoAtributosVBox.getChildren().clear();
        String photo = "/img/user.png";
        if(contacto.hasPhotos()){
            photo = contacto.getFirstPhoto();
        }
        datosContactoAtributosVBox.setSpacing(10);
        contactoImg.setImage(new Image(photo, 64,64,false,false));
        HashMap<String, TextField> diccionarioCamposTexto = new HashMap<>();

        for(String key: contacto.getKeysAtributtes()){
            HBox datosContacto = new HBox(); //Crea un HBox en donde va a ir guardado un atributo y su valor
            datosContacto.setAlignment(Pos.CENTER); //Hace que los elementos dentro del HBox esten en el centro
            Label label = new Label(key);
            label.setOnMouseClicked((e)->{
                editarAtributteContacto(contacto, key, label, datosContacto);
                }
            );
            
            datosContacto.getChildren().add(label); //Agrega una Etiqueta con texto de la llave del diccionario
            TextField campoTexto = new TextField(contacto.getValorAtributte(key)); //Crea un campo de texto con el valor que tiene la llave key en el diccionario de atributos
            campoTexto.setDisable(true);//pone el campo de texto desactvado
            datosContacto.setOnMouseEntered(e->{campoTexto.setDisable(false);});//Cuando el mouse pasa encima del HBox el Campo de texto se activa
            datosContacto.setOnMouseExited(e->{//Cuando el mouse sale del HBox entonces guarda los datos del Campo de texto
                campoTexto.setDisable(true);//Desabilita el campo de texto
                contacto.setAtributte(key, diccionarioCamposTexto.get(key).getText());//actualiza los datos del contacto en el campo de texto 
                }
            );
            datosContacto.getChildren().add(campoTexto);//agrega el campo de texto al lado del Label
            Button borrarAtributoButton = new Button();
            borrarAtributoButton.setGraphic(new ImageView(new Image("/img/delette_atributte.png", 16,16,false,false)));
            borrarAtributoButton.setOnAction((e)->{
                    datosContactoAtributosVBox.getChildren().remove(datosContacto);
                    contacto.removeKeyAtributte(key);
                }
            );
            datosContacto.getChildren().add(borrarAtributoButton);//Agrega el boton para eliminar atributo al lado del Campo de texto
            datosContactoAtributosVBox.getChildren().add(datosContacto);//agrega ese reglon con datos del campo de texto, label e imagen en un VBOX
            
            diccionarioCamposTexto.put(key, campoTexto);//Agrega la llave y valor a un diccionario que tiene todos los campos de texto del contacto
        }
    }
    
    private void editarAtributteContacto(Contacto contacto, String key,Label label, Pane pane){
        pane.getChildren().remove(label);
                TextField txtLabel = new TextField(label.getText());
                pane.getChildren().add(0, txtLabel);
                txtLabel.setOnMouseExited((e2)->{
                    label.setText(txtLabel.getText());
                    pane.getChildren().remove(txtLabel);
                    pane.getChildren().add(0, label);
                    contacto.updateKeyAtributte(key, label.getText());
                });
    }
    
    private void mostrarContactosIterados(int indice){
        contactosVBox.getChildren().clear();
        int numElementos = 0;
        while( numElementos<numeroContactosMostrados){
            if(indice<0){
                indice=agenda.Sistema.contactos.size()-1;
                
            }else if(indice>=agenda.Sistema.contactos.size()){
                indice=0;
            }
            Contacto contacto = agenda.Sistema.contactos.get(indice);
            mostrarContacto(contacto);
            numElementos++;
            indice++;
        }
    }
    
    private void mostrarContacto(Contacto contacto){
        HBox cajaContacto = new HBox();
        cajaContacto.setCursor(Cursor.HAND);
        cajaContacto.setOnMouseClicked((e)->{
            contactoSeleccionado = contacto;
            cajaContactoSeleccionada = cajaContacto ;
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
        
    }
    
    @FXML
    private void retrocederContactos(){
        indice--;
        mostrarContactosIterados(indice);
        if(indice<0){
            indice=agenda.Sistema.contactos.size()-1;
        }
        
    }
    
    @FXML
    private void avanzarContactos(){
        indice++;
        mostrarContactosIterados(indice);
        if(indice>=agenda.Sistema.contactos.size()){
            indice=0;
        }
    }
    
    @FXML
    public void addAtributte(){
        contactoSeleccionado.putAtributte("Nuevo Atributo" + contactoSeleccionado.getKeysAtributtes().size(), "");
        cargarDatosContacto(contactoSeleccionado);
    }
    
    @FXML
    public void deleteContact(){
        //int indiceContactoBorrado = agenda.Sistema.contactos.indexOf(contactoSeleccionado);
        agenda.Sistema.contactos.remove(contactoSeleccionado);
        //int indiceCajaContactoSeleccionada = contactosVBox.getChildren().indexOf(cajaContactoSeleccionada);
        contactosVBox.getChildren().remove(cajaContactoSeleccionada);
        cargarContactosPanelIzquierdo(agenda.Sistema.contactos);
        datosContactoAtributosVBox.getChildren().clear();
        datosContactoVBox.setVisible(false);
    }
}
