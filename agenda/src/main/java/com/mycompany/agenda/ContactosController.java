
package com.mycompany.agenda;

import agenda.Contacto;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
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
import tda.CircularList;
import tda.List;

public class ContactosController {
    private final int NUMERO_CONTACTOS_MOSTRADOS = 6;
    @FXML private VBox contactosVBox, datosContactoAtributosVBox, datosContactoVBox;
    @FXML private ImageView contactoImg;
    @FXML private Button upContactsButton, downContactsButton;
    @FXML private HBox tagsHBox;
    private Contacto contactoSeleccionado;
    private HBox cajaContactoSeleccionada;
    private List<ListIterator<Contacto>> iterators = new ArrayList<>();
    private boolean retrocedio = false; //usado para saber si avanzó  en la lista de contactos
    private boolean avanzo = true; //usado para saber si retrocedio en la lista de contactos
    private Label labelNameFX, labelNumberFX;

    @FXML
    private void initialize(){
        prepararIteradores(agenda.Sistema.contactos, NUMERO_CONTACTOS_MOSTRADOS);//Prepara el uso de iteradores que se van a mostrar como contactos en el panel izquierdo
        cargarContactosPanelIzquierdo(agenda.Sistema.contactos);//cargar los contactos en el panel izquierdo
        agregarButtonTagsEnElHBox();//carga las tags que tengan todos los Contactos y los pone en los botones para filtrar por esas tags
    }
    
    //recibe un iterador y los avanza el numero de veces de STEP
    private void avanzarIterator(int steps, Iterator it){
        for(int i=1;i<steps;i++){
            it.next();
        }
    }
    //Configura los iteradores que se mostraran en pantalla y los guarda en un ArrayList
    private List<ListIterator<Contacto>> prepararIteradores(CircularList list, int numIterators){
        iterators = new ArrayList<>();
        for(int i = 0; i < numIterators; i++){
            ListIterator<Contacto> it = list.listIterator();
            avanzarIterator(i+2, it);//Hace que los elementos avancen la posicion
            iterators.addLast(it);
        }
    return iterators;
}
    
    private void agregarButtonTagsEnElHBox(){//
        for(String tag: agenda.Sistema.tags){
            tagsHBox.getChildren().add(crearButtonTag(tag));
        }
    }
    
    //Crea botones con el texto que tiene la tag, ademas le añade un evento que cada que es presionado filtra los contactos que tengan esa tag
    private Button crearButtonTag(String tag){
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
        return buttonTag;
    }
    
    private void setDisableButtonsDownUp(boolean valor){
        upContactsButton.setDisable(valor);
        downContactsButton.setDisable(valor);
    }
    
    private void cargarContactosPanelIzquierdo(List lista){
        Iterator<Contacto> it = lista.iterator();
        contactosVBox.getChildren().clear();
        setDisableButtonsDownUp(true);
        if(lista.size()>NUMERO_CONTACTOS_MOSTRADOS){
            setDisableButtonsDownUp(false);
        }
        int numElementos = 0;
        while(it.hasNext() && numElementos<NUMERO_CONTACTOS_MOSTRADOS){
            Contacto contacto = it.next();
            mostrarContacto(contacto);
            numElementos++;
        }
    }
    
    private void cargarDatosContacto(Contacto contacto, Label labelName, Label labelNumber){
        datosContactoVBox.setVisible(true);
        datosContactoAtributosVBox.getChildren().clear();
        String photo = "/img/user.png";
        if(contacto.hasPhotos()){
            photo = contacto.getFirstPhoto();
        }
        datosContactoAtributosVBox.setSpacing(10);
        contactoImg.setImage(new Image(photo, 64,64,false,false));

        for(String key: contacto.getKeysAtributtes()){
            HBox datosContacto = new HBox(); //Crea un HBox en donde va a ir guardado un atributo y su valor
            datosContacto.setAlignment(Pos.CENTER); //Hace que los elementos dentro del HBox esten en el centro

            Label label = crearLabelEditable(contacto, key, labelName, labelNumber, datosContacto);//Crea un label que se puede editar cuando se le da un click
            datosContacto.getChildren().add(label); //Agrega una Etiqueta con texto de la llave del diccionario
            
            TextField campoTexto = crearTextField(contacto, key,datosContacto);
            if(key.equals("name")){
                labelName.textProperty().bind(campoTexto.textProperty());//Enlaza el labelName que viene del panel Izquierdo con el name que se muestra en el lado derecho
            }else if(key.equals("number")){
                labelNumber.textProperty().bind(campoTexto.textProperty());///Enlaza el labelNumber que viene del panel Izquierdo con el number que se muestra en el lado derecho
            }
            datosContacto.getChildren().add(campoTexto);//agrega el campo de texto al lado del Label
            
            Button borrarAtributoButton = crearButtonDeleteAtributte(contacto, key, "", datosContacto);

            datosContacto.getChildren().add(borrarAtributoButton);//Agrega el boton para eliminar atributo al lado del Campo de texto
            datosContactoAtributosVBox.getChildren().add(datosContacto);//agrega ese reglon con datos del campo de texto, label e imagen en un VBOX
        }
    }
    
    private Button crearButton(String textButton){
        return new Button(textButton);
    }
    
    private Button crearButtonDeleteAtributte(Contacto contacto, String key, String textButton, Pane pane){
        Button button = crearButton(textButton);
        button.setGraphic(new ImageView(new Image("/img/delette_atributte.png", 16,16,false,false)));
        button.setOnAction((e)->{
            datosContactoAtributosVBox.getChildren().remove(pane);
            contacto.removeKeyAtributte(key);
            }
        );
        return button;
    }
    
    //Recibe un contacto de donde se sacara guardara el Atributo que va en el label y un Pane que lo contiene
    private Label crearLabelEditable(Contacto contacto, String key, Label labelName, Label labelNumber, Pane pane){
        Label label=  new Label(key);
        label.setOnMouseClicked((e)->{
                editarAtributteContacto(contacto, key, label, labelName, labelNumber, pane);
                
            }
        );
        return label;
    }
    
    private void editarAtributteContacto(Contacto contacto, String key,Label label, Label labelName, Label labelNumber, Pane pane){
        pane.getChildren().remove(label);
        TextField txtLabel = new TextField(label.getText());
        pane.getChildren().add(0, txtLabel);
        
        txtLabel.setOnAction((e2)->{
            updateAtributteContacto(contacto, key, label, labelName, labelNumber, txtLabel, pane);
        });
        
        txtLabel.setOnMouseExited((e2)->{
            updateAtributteContacto(contacto, key, label, labelName, labelNumber, txtLabel, pane);
        });
    }
    
    private void updateAtributteContacto(Contacto contacto, String key, Label label, Label labelName, Label labelNumber, TextField txtLabel, Pane pane){
        label.setText(txtLabel.getText());
        pane.getChildren().remove(txtLabel);
        pane.getChildren().add(0, label);
        contacto.updateKeyAtributte(key, label.getText());
        cargarDatosContacto(contacto, labelName, labelNumber);
    }
    
    private TextField crearTextField(Contacto contacto, String key,Pane pane){
        TextField campoTexto = new TextField(contacto.getValorAtributte(key));
        
        campoTexto.setDisable(true);//pone el campo de texto desactvado
        pane.setOnMouseEntered(e->{campoTexto.setDisable(false);});//Cuando el mouse pasa encima del HBox el Campo de texto se activa
        String oldTxt = campoTexto.getText();
        campoTexto.setOnAction(e->{//Cuando el mouse sale del HBox entonces guarda los datos del Campo de texto
            editarValueAtributte(contacto, key, oldTxt, campoTexto);
        }
        );
        
        pane.setOnMouseExited(e->{//Cuando el mouse sale del HBox entonces guarda los datos del Campo de texto
            editarValueAtributte(contacto, key, oldTxt, campoTexto);
        }
        );
        return campoTexto;
    }
    
    private void editarValueAtributte(Contacto contacto, String key, String oldTxt, TextField campoTexto){
        if(!campoTexto.getText().equals(oldTxt)){
            contacto.setAtributte(key, campoTexto.getText());//actualiza los datos del contacto en el campo de texto 
        }
        campoTexto.setDisable(true);//Desabilita el campo de texto
    }
 
    private void mostrarContacto(int indice, Contacto contacto){
        HBox cajaContacto = new HBox();
        
        String photo = "/img/user.png";
        if(contacto.hasPhotos()){
            photo = contacto.getFirstPhoto();
        }
        cajaContacto.getChildren().add(new ImageView(new Image(photo, 64,64,false,false)));
        VBox datosContacto = new VBox();
        datosContacto.setAlignment(Pos.CENTER);
        Label labelName = new Label(contacto.getName());
        datosContacto.getChildren().add(labelName);
        Label labelNumber = new Label(contacto.getNumber());
        datosContacto.getChildren().add(labelNumber);
        cajaContacto.getChildren().add(datosContacto);
        
        cajaContacto.setCursor(Cursor.HAND);
        cajaContacto.setOnMouseClicked((e)->{
            contactoSeleccionado = contacto;
            cajaContactoSeleccionada = cajaContacto ;
            cargarDatosContacto(contacto, labelName, labelNumber);
            labelNameFX = labelName;
            labelNumberFX = labelNumber;
        });
        contactosVBox.getChildren().add(indice,cajaContacto);
        
    }
    
    private boolean mostrarContacto(Contacto contacto){
        if(contacto == null){
            return false;
        }
        mostrarContacto(contactosVBox.getChildren().size(), contacto);
        return true;
        
    }
    
    @FXML
    private void retrocederContactos(){
        contactosVBox.getChildren().clear();
        if (avanzo){
            for(ListIterator<Contacto> itList: iterators){
                itList.previous();
                itList.previous();
            }
        }
        for(ListIterator<Contacto> itList: iterators){
            mostrarContacto(itList.previous());
        }
        retrocedio = true;
        avanzo = false;
    }
    
    @FXML
    private void avanzarContactos(){
        contactosVBox.getChildren().clear();
        if (retrocedio){
            for(ListIterator<Contacto> itList: iterators){
                itList.next();
                itList.next();
            }
        }
        for(ListIterator<Contacto> itList: iterators){
            mostrarContacto(itList.next());
        }
        retrocedio = false;
        avanzo = true;
    }
    
    @FXML
    public void addAtributte(){
        contactoSeleccionado.putAtributte("Nuevo Atributo" + contactoSeleccionado.getKeysAtributtes().size(), "");
        cargarDatosContacto(contactoSeleccionado, labelNameFX,labelNumberFX);
    }
    
    @FXML
    public void deleteContact(){
        agenda.Sistema.contactos.remove(contactoSeleccionado);
        contactosVBox.getChildren().remove(cajaContactoSeleccionada);
        cargarContactosPanelIzquierdo(agenda.Sistema.contactos);
        datosContactoAtributosVBox.getChildren().clear();
        datosContactoVBox.setVisible(false);
    }
}
