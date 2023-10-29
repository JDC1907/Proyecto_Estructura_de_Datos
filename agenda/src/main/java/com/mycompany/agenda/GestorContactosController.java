/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.agenda;

import agenda.Contacto;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tda.List;

/**
 * FXML Controller class
 *
 * @author nhale
 */
public class GestorContactosController implements Initializable {
    private int indice = 0;

    @FXML
    private Button bteditar;
    @FXML
    private Button btagregar;
    @FXML
    private Tab tabpersona;
    @FXML
    private Tab tabempresa;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtEmpresa;
    @FXML
    private TextField txtEmailPer;
    @FXML
    private TextField txtEmailTra;
    @FXML
    private TextField txtTelf;
    @FXML
    private TextField txtTelfTra;
    @FXML
    private TextField txtTelfCasa;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtdireccionTrabajo;
    @FXML
    private Button btsiguiente;
    @FXML
    private Button btanterior;
    @FXML
    private Pane PaneContactos;
    @FXML
    private Button upContactsButton;
    @FXML
    private Button downContactsButton;
    @FXML
    private VBox contactosVBox;
    
    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarContactos();
    }  
    private void cargarContactos(){
        if(agenda.Sistema.contactos.size()>6){
            upContactsButton.setDisable(false);
            downContactsButton.setDisable(false);
        }
        Iterator<Contacto> it = agenda.Sistema.contactos.iterator();
        int numElementos = 0;
        while(it.hasNext() && numElementos<6){
            Contacto contacto = it.next();
            HBox cajaContacto = new HBox();
            cajaContacto.setCursor(Cursor.HAND);
            cajaContacto.setOnMouseClicked((e)->{
                //cargarDatosContacto(contacto);
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
    
//    private void cargarDatosContacto(Contacto contacto){
//        datosContactoAtributosVBox.getChildren().clear();
//        String photo = "/img/user.png";
//        if(contacto.hasPhotos()){
//            photo = contacto.getFirstPhoto();
//        }
//        contactoImg.setImage(new Image(photo, 64,64,false,false));
//        for(String key: contacto.getKeysAtributtes()){
//            HBox datosContacto = new HBox();
//            datosContacto.setAlignment(Pos.CENTER);
//            datosContacto.setSpacing(10);
//            datosContacto.getChildren().add(new Label(key));
//            datosContacto.getChildren().add(new Label(contacto.getValor(key)));
//            datosContactoAtributosVBox.getChildren().add(datosContacto);
//        }
//    }

    @FXML
    private void retrocederContactos(ActionEvent event) {
        indice--;
        mostrarSeisContactos(indice);
        if(indice<0){
            indice=agenda.Sistema.contactos.size()-1;
        }
    }

    @FXML
    private void avanzarContactos(ActionEvent event) {
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
                //cargarDatosContacto(contacto);
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
//       public Contacto getContactoSeleccionada(){
//        if(agenda.Sistema.contactos != null){
//            List<Contacto> tabla = contactosVbox.getSelectionModel().getSelectedItems();
//            if(tabla.size() == 1){
//                final Cliente competicionSeleccionada = tabla.get(0);
//                return competicionSeleccionada;
//            }
//        }
//        return null;
//    
//       public void ponerContactoSeleccionado(){
//        final Cliente c = getTablaSeleccionada();
//        posicionTabla = clientes.indexOf(c);
//        if (c != null){
//            System.out.println(c.getNombre());
//        txtNombre.setText(c.getNombre());
//        txtCedula.setText(c.getCodigo());
//        txtDireccion.setText(c.getDireccion());
//        txtTelefono.setText(c.getTelefono());
//        boxClientes.setValue(c.getTipo());
//        }
//    }
//}
    
//    public void seleccionar(ActionEvent event){
//        
//        
//    }
//    
//    
//    public void editarDatos(ActionEvent event){
//        
//        
  }
