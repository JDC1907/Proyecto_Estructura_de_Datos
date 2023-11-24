
package com.mycompany.agenda;
import agenda.Contacto;
import agenda.Empresa;
import agenda.Persona;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.ListIterator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import tda.ArrayList;
import tda.List;


/**
 * FXML Controller class
 *
 * @author JDC_1
 */
public class CrearContactoController  {
    @FXML private VBox datosContactoAtributosVBox, datosContactoVBox, datosContactoNameNumberVBox, vBoxCrear;
    @FXML private ImageView contactoImg;
    @FXML private Button leftPhotos, rightPhotos, addPhoto,deletePhoto, addAtributteButton,addTagButton, buttonGuardar ;
    @FXML private HBox tagsHBox;
    @FXML private FlowPane contactoTagsFlowPane;
    @FXML private ComboBox<Contacto> contactosRelacionadosComboBox;
    @FXML private TextField nuevaTagTextField,textNombre,textNumero;
    
    private static Contacto contactoSeleccionado; //Contacto seleccionado
    private ListIterator<String> photoIterator;
    private final String PHOTO_DEFAULT = "/img/user.png";
    private String photoContacto = "/img/user.png";
    private boolean retrocedioPhoto = false; //usado para saber si avanzó  en la lista de contactos
    private boolean avanzoPhoto = false; //usado para saber si retrocedio en la lista de contactos
    private ContactosController contactosController;
    
    private ImageView imgBienvenida;
    
    @FXML
    private void initialize(){
    }
    
    public void cargarImagenBienvenida(ImageView imgBienvenida){
        this.imgBienvenida = imgBienvenida;
    }
    
    public void cargarParent(ContactosController contactosController){
        this.contactosController = contactosController;
    }
    //Guardar contacto
    @FXML
    public void guardarContacto(ActionEvent event) throws IOException{
        agenda.Sistema.contactos.addLast(contactoSeleccionado);       
        vBoxCrear.getChildren().clear();
        vBoxCrear.setVisible(false);
        vBoxCrear.setDisable(true);
        
        /*vBoxCrear.getChildren().clear();
        internal = null;
        datosContactoVBox.getChildren();
        datosContactoVBox.setVisible(true);*/

        //Cierratodo
        /*Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();*/
       // contactosController.actualizarPanelIzquierdo(agenda.Sistema.contactos);
        imgBienvenida.setVisible(true);
        agenda.Sistema.guardarContactos(agenda.Sistema.usuario);
        contactosController.actualizarPanelIzquierdo(agenda.Sistema.contactos);
        contactosController.agregarButtonTagsEnElHBox();
    }
     
     //carga todos los datos que se mostrará en este panel, en especial los que nos interesan en esta clase son lo textFile de cada uno de los atributos
    private void cargarDatosContacto(){
        Contacto contacto = contactoSeleccionado;
        
        contactosRelacionadosComboBox.getItems().clear();//Este metodo que llamamos no importa en esta clase
        HashSet<Contacto> contactosRelacionados = new HashSet();
        for(Contacto contactoRelacionado: agenda.Sistema.contactos){
            contactosRelacionados.add(contactoRelacionado);
        }
        contactosRelacionados.remove(contacto);
        for(Contacto contactoRelacionado: contacto.getContactosRelacionados()){
            contactosRelacionados.remove(contactoRelacionado);
        }
        
        //cargarTagsContactoSeleccionadoHBox();      
        contacto.getTags();
        contactosRelacionadosComboBox.getItems().addAll(contactosRelacionados);
        
        datosContactoVBox.setVisible(true);//Hace visible el vBox en donde estan los datos de los contacos
        datosContactoNameNumberVBox.getChildren().clear();//Limpia el VBox en donde van guardado el nombre y numero del contaco
        
        
        if(contacto.hasPhotos()){//Si hay fotos obtiene la primera foto
            photoContacto = photoIterator.next();
        }else{
            photoContacto = PHOTO_DEFAULT;
        }
        
        if (contacto.getPhotos().size()>1){//Si hay mas de una foto activa los botones para navegar las fotografias
            leftPhotos.setDisable(false);
            rightPhotos.setDisable(false);
           
        }else{
            leftPhotos.setDisable(true);
            rightPhotos.setDisable(true);
            deletePhoto.setDisable(true);
        }
        if (contacto.getPhotos().size()>0){
            deletePhoto.setDisable(false);
        }
        //--------------------------------------------------------------------------------------------------------------------------------
        //Agregacion de atributos

        datosContactoAtributosVBox.setSpacing(10);
        contactoImg.setImage(new Image(photoContacto, 64,64,false,false)); //carga la foto del contacto
        //Esto es donde la informacion que queremos guardar o mostrar en el textNombre y textNumero
        HBox datosContactoNombre = crearHBoxAtributteContacto();
        agregarLabelNameAndTextFieldNameToPane("Name", contacto.getName(), contacto, datosContactoNombre);
        
        HBox datosContactoNumero = crearHBoxAtributteContacto();
        agregarLabelNumberAndTextFieldNumberToPane("Number", contacto.getNumber(), contacto, datosContactoNumero);
        
        datosContactoNameNumberVBox.getChildren().add(datosContactoNombre);
        datosContactoNameNumberVBox.getChildren().add(datosContactoNumero);
        datosContactoAtributosVBox.getChildren().clear();
        for(String key: contacto.getKeysAtributtes()){ //Se agregan los atributos(key) junto a sus valores
            agregarHBoxADatosContactoAtributosVBox(key);
        }     
    }
    //Habilita los campos al momento de escoger el tipo de contacto
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private void setDisabelCampos(boolean activarCampos){
        addPhoto.setDisable(activarCampos);
        //deletePhoto.setDisable(activarCampos);
        textNombre.setDisable(activarCampos);
        textNumero.setDisable(activarCampos);
        addAtributteButton.setDisable(activarCampos);
        nuevaTagTextField.setDisable(activarCampos);
        addTagButton.setDisable(activarCampos);
        contactosRelacionadosComboBox.setDisable(activarCampos);
        buttonGuardar.setDisable(activarCampos);
    }
    //Seleciona que tipo de contacto es (Persona y empresa)
    @FXML
    private void selecionarPersona(){
        contactoSeleccionado = new Persona("","");
        setDisabelCampos(false);
        cargarDatosContacto();

    }
    @FXML
    private void selecionarEmpresa(){
        contactoSeleccionado = new Empresa("","");
        setDisabelCampos(false);
        cargarDatosContacto();

    }

    private void agregarButtonTagsEnElHBox(){//
        tagsHBox.getChildren().clear();
        for(String tag: agenda.Sistema.getTags()){
            tagsHBox.getChildren().add(crearButtonTag(tag));
        }
    }   
    //Crea botones con el texto que tiene la tag, ademas le añade un evento que cada que es presionado filtra los contactos que tengan esa tag
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

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
                }
            );
        return buttonTag;
    }
    
    private HBox crearTagContactoSeleccionado(String tag){
        HBox cajaTag = new HBox();
        cajaTag.getChildren().add(new Label(tag));
                Button buttonBorrarTag = new Button();
                buttonBorrarTag.setGraphic(new ImageView(new Image("/img/delette_atributte.png", 16,16,false,false)));
                buttonBorrarTag.setCursor(Cursor.HAND);
                buttonBorrarTag.setOnAction((e)->{
                        contactoSeleccionado.removeTag(tag);
                        contactoTagsFlowPane.getChildren().remove(cajaTag);
                        agregarButtonTagsEnElHBox();
                    }
                );
                cajaTag.getChildren().add(buttonBorrarTag);
        return cajaTag;
    }

    private void agregarLabelNameAndTextFieldNameToPane(String textLabel, String stringTextField, Contacto contacto, Pane pane){
        Label label = new Label(textLabel);
        TextField textField = crearTextFieldName(contacto, stringTextField, label, pane);
        pane.getChildren().add(label);
        pane.getChildren().add( textField);
    }
    
    private void agregarLabelNumberAndTextFieldNumberToPane(String textLabel, String stringTextField, Contacto contacto, Pane pane){
        Label label = new Label(textLabel);
        TextField textField = crearTextFieldNumber(contacto, stringTextField, label, pane);
        pane.getChildren().add(label);
        pane.getChildren().add( textField);
    }

    private HBox crearHBoxAtributteContacto(){
        HBox hBox = new HBox();//Crea un HBox en donde va a ir guardado un atributo y su valor
        hBox.setAlignment(Pos.CENTER);//Hace que los elementos dentro del HBox esten en el centro
        
        return hBox;
    }

    //El que se encargar d eponer todos lo texfile y los botones de eliminar atributo al momento de agregar un nuevo atributo
    private void agregarHBoxADatosContactoAtributosVBox(String key){ 
        HBox datosContacto = crearHBoxAtributteContacto();
        Label label = crearLabelEditable(key, datosContacto);//Crea un label que se puede editar cuando se le da un click
        datosContacto.getChildren().add(label); //Agrega una Etiqueta con texto de la llave del diccionario

        TextField campoTexto = crearTextField(key,datosContacto);

        datosContacto.getChildren().add(campoTexto);//agrega el campo de texto al lado del Label

        Button borrarAtributoButton = crearButtonDeleteAtributte(key, "", datosContacto);

        datosContacto.getChildren().add(borrarAtributoButton);//Agrega el boton para eliminar atributo al lado del Campo de texto
        datosContactoAtributosVBox.getChildren().add(datosContacto);//agrega ese reglon con datos del campo de texto, label e imagen en un VBOX
    }

    private Button crearButton(String textButton){
        return new Button(textButton);
    }
    //Recibe un contacto de donde se sacara guardara el Atributo que va en el label y un Pane que lo contiene
    //---------------------------------------------------------------------------------------------------------------------------------------------------
    
    private Label crearLabelEditable(String key, Pane pane){
        Label label=  new Label(key);
        label.setCursor(Cursor.HAND);
        label.setOnMouseClicked((e)->{
                editarAtributteContacto(contactoSeleccionado, key, label, pane);
            }
        );
        return label;
    }
    
    private void updateAtributteContacto(Contacto contacto, String key, Label label, TextField txtLabel, Pane pane){
        label.setText(txtLabel.getText());
        pane.getChildren().remove(txtLabel);
        pane.getChildren().add(0, label);
        contacto.updateKeyAtributte(key, label.getText());
    }

    //Crea los TextFile y ademas los de Name y Number 
    //---------------------------------------------------------------------------------------------------------------------------------------------------
    private TextField crearTextField(String key,Pane pane){
        Contacto contacto = contactoSeleccionado;
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
    
    private TextField crearTextFieldName(Contacto contacto, String name, Label label, Pane pane){
        TextField campoTexto = new TextField(name);
        
        campoTexto.setDisable(true);//pone el campo de texto desactvado
        pane.setOnMouseEntered(e->{campoTexto.setDisable(false);});//Cuando el mouse pasa encima del HBox el Campo de texto se activa
        campoTexto.setOnAction(e->{//Cuando el usuario da enter en el txtField entonces guarda los datos del Campo de texto
            campoTexto.setDisable(true);
            contacto.setName(campoTexto.getText());
        }
        );
        
        pane.setOnMouseExited(e->{//Cuando el mouse sale del HBox entonces guarda los datos del Campo de texto
            campoTexto.setDisable(true);
            contacto.setName(campoTexto.getText());
        }
        );
        
        if(label.getText().equals("Name") ){
            //bindLabelconTextField(labelNameFX, campoTexto);
        }
        return campoTexto;
    }
    
    private TextField crearTextFieldNumber(Contacto contacto, String number, Label label, Pane pane){
        TextField campoTexto = new TextField(number);
        
        campoTexto.setDisable(true);//pone el campo de texto desactvado
        pane.setOnMouseEntered(e->{campoTexto.setDisable(false);});//Cuando el mouse pasa encima del HBox el Campo de texto se activa
        campoTexto.setOnAction(e->{//Cuando el usuario da enter en el txtField entonces guarda los datos del Campo de texto
            contacto.setNumber(campoTexto.getText());
            campoTexto.setDisable(true);
            
        }
        );
        
        pane.setOnMouseExited(e->{//Cuando el mouse sale del HBox entonces guarda los datos del Campo de texto
            contacto.setNumber(campoTexto.getText());
            campoTexto.setDisable(true);
        }
        );
        
        if(label.getText().equals("Number")){
            //bindLabelconTextField(labelNumberFX, campoTexto);
        }
        return campoTexto;
    }
   
    //Edita todos los atributos que esten lleno de datos
    //---------------------------------------------------------------------------------------------------------------------------------------------------
    private void editarValueAtributte(Contacto contacto, String key, String oldTxt, TextField campoTexto){
        if(!campoTexto.getText().equals(oldTxt)){
            contacto.setAtributte(key, campoTexto.getText());//actualiza los datos del contacto en el campo de texto 
        }
        campoTexto.setDisable(true);//Desabilita el campo de texto
    }
    
    private void editarAtributteContacto(Contacto contacto, String key,Label label, Pane pane){
        pane.getChildren().remove(label);
        TextField txtLabel = new TextField(label.getText());
        pane.getChildren().add(0, txtLabel);
        
        txtLabel.setOnAction((e2)->{
            updateAtributteContacto(contacto, key, label, txtLabel, pane);
        });
        
        txtLabel.setOnMouseExited((e2)->{
            updateAtributteContacto(contacto, key, label, txtLabel, pane);
        });
    }

    //Agrega todos los Atributos,tag,ContactosVinculador del Contacto
    //---------------------------------------------------------------------------------------------------------------------------------------------------
    
     @FXML
    public void addAtributte(){
        contactoSeleccionado.putAtributte("Nuevo Atributo" + contactoSeleccionado.getKeysAtributtes().size(), "");
        photoIterator = contactoSeleccionado.getPhotos().listIterator();
        cargarDatosContacto(); 
    }
    
    @FXML
    public void addTagContactoSeleccionado(){
        String tag = nuevaTagTextField.getText();
        if(!tag.equals("") && !contactoSeleccionado.getTags().contains(tag)){
            contactoSeleccionado.addTag(tag);
            contactoTagsFlowPane.getChildren().add(crearTagContactoSeleccionado(tag));
            //agregarButtonTagsEnElHBox();
        }
    }
    
    @FXML
    public void addContactoVinculado(){
        Contacto contacoParaAsociar = contactosRelacionadosComboBox.getSelectionModel().getSelectedItem();
        contacoParaAsociar.addContactoRelacionado(contactoSeleccionado);
        cargarDatosContacto();
        photoIterator = contactoSeleccionado.getPhotos().listIterator();
 
    }
    //Fotos
    //---------------------------------------------------------------------------------------------------------------------------------------------------
   @FXML
    public void nextContactPhoto(){
        if(retrocedioPhoto){
            photoIterator.next();
            photoIterator.next();
        }
        photoContacto = photoIterator.next();
        avanzoPhoto = true;
        retrocedioPhoto = false;
        contactoImg.setImage(new Image(photoContacto, 64,64,false,false));
    }
    
    @FXML
    public void previousContactPhoto(){
        if(avanzoPhoto){
            photoIterator.previous();
            photoIterator.previous();
        }
        photoContacto = photoIterator.previous();
        avanzoPhoto = false;
        retrocedioPhoto = true;
        contactoImg.setImage(new Image(photoContacto, 64,64,false,false));
    }
    
    @FXML
    private void agregarPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        
        java.util.List<File> imgFiles =  fileChooser.showOpenMultipleDialog(null);
        if(imgFiles!=null){
            for(int i=0;i<imgFiles.size();i++){
                try {
                    String imagePath = imgFiles.get(i).toURI().toURL().toExternalForm();
                    contactoSeleccionado.addPhoto(imagePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                photoIterator = contactoSeleccionado.getPhotos().listIterator();
            }
            cargarDatosContacto();
        }
    }

    @FXML
    private void eliminarPhoto() {
        if (contactoSeleccionado.getPhotos().size()>0){
            deletePhoto.setDisable(false);
            contactoSeleccionado.removePhoto(photoContacto);
            
            photoIterator = contactoSeleccionado.getPhotos().listIterator();
        }       
    }
    
    private Button crearButtonDeleteAtributte(String key, String textButton, Pane pane){
        Button button = crearButton(textButton);
        button.setGraphic(new ImageView(new Image("/img/delette_atributte.png", 16,16,false,false)));
        button.setOnAction((e)->{
            datosContactoAtributosVBox.getChildren().remove(pane);
            contactoSeleccionado.removeKeyAtributte(key);
            }
        );
        return button;
    }
}
