
package com.mycompany.agenda;

import agenda.Contacto;
import java.util.Comparator;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.geometry.Insets;

import javafx.fxml.FXMLLoader;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tda.ArrayList;
import tda.CircularList;
import tda.DoublyLinkedList;
import tda.List;




public class ContactosController {
    private final int NUMERO_CONTACTOS_MOSTRADOS = 7;//Numero de contactos que van a ser mostrado en el panel izquierdo
    @FXML private VBox contactosVBox, datosContactoAtributosVBox, datosContactoVBox, datosContactoNameNumberVBox, contactosRelacionadosVBox, vBoxContatoMuestra;
    @FXML private AnchorPane anchorPaneContac;
    @FXML private ImageView contactoImg,imgBienvenida;
    @FXML private Button upContactsButton, downContactsButton, leftPhotos, rightPhotos;
    @FXML private HBox tagsHBox;
    @FXML private FlowPane contactoTagsFlowPane;
    @FXML private ComboBox<Contacto> contactosRelacionadosComboBox;
    @FXML private TextField nuevaTagTextField,textNombre,textNumero;
    
    
    private Contacto contactoSeleccionado; //Contacto seleccionado
    private List<ListIterator<Contacto>> iterators = new ArrayList<>();
    private List<ListIterator<Contacto>> iterators1 = new ArrayList<>();
    private ListIterator<String> photoIterator;
    private final String PHOTO_DEFAULT = "/img/user.png";
    private String photoContacto = "/img/user.png";
    private boolean retrocedio, retrocedioPhoto = false; //usado para saber si avanzó  en la lista de contactos
    private boolean avanzo, avanzoPhoto = false; //usado para saber si retrocedio en la lista de contactos
    private Node internal;
    

    private Label labelNameFX, labelNumberFX;
   

    
    @FXML
    private Button deletePhoto;
    
   

    @FXML
    private void initialize(){
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
    //Prepara el uso de iteradores que se van a mostrar como contactos en el panel izquierdo
    private List<ListIterator<Contacto>> prepararIteradores(CircularList list, int numIterators){
        iterators = new ArrayList<>();
        avanzo = true;
        for(int i = 0; i < numIterators; i++){
            ListIterator<Contacto> it = list.listIterator();
            avanzarIterator(i+2, it);//Hace que los elementos avancen la posicion
            iterators.addLast(it);
        }
    return iterators;
}
    //Agrega botones en el area de los tag
    private void agregarButtonTagsEnElHBox(){//
        tagsHBox.getChildren().clear();
        for(String tag: agenda.Sistema.getTags()){
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
    //Descativa los botoes de subir y bajar en el menu de contactos del panel izquierdo
    private void setDisableButtonsDownUp(boolean valor){
        upContactsButton.setDisable(valor);
        downContactsButton.setDisable(valor);
    }
    //Carga los contactos de una list y los pone en el panel izquierdo
    private void cargarContactosPanelIzquierdo(List lista){
        prepararIteradores(agenda.Sistema.contactos, NUMERO_CONTACTOS_MOSTRADOS);
        Iterator<Contacto> it = lista.iterator();
        contactosVBox.getChildren().clear();
        setDisableButtonsDownUp(true);
        if(lista.size()>NUMERO_CONTACTOS_MOSTRADOS){
            setDisableButtonsDownUp(false);
        }
        int numElementos = 0;
        while(it.hasNext() && numElementos<NUMERO_CONTACTOS_MOSTRADOS){
            Contacto contacto = it.next();
            mostrarContacto(contacto);//
            numElementos++;
        }
    }

    //Carga los datos del contacto y los coloca en el panel derecho en donde seran mostrados todos los datos del contacto seleccionado
    private void cargarDatosContacto(Contacto contacto){
        contactosRelacionadosComboBox.getItems().clear();
        HashSet<Contacto> contactosRelacionados = new HashSet();
        for(Contacto contactoRelacionado: agenda.Sistema.contactos){
            contactosRelacionados.add(contactoRelacionado);
        }
        contactosRelacionados.remove(contacto);
        for(Contacto contactoRelacionado: contacto.getContactosRelacionados()){
            contactosRelacionados.remove(contactoRelacionado);
        }
        
        cargarTagsContactoSeleccionadoHBox();
                
        contactoSeleccionado.getTags();
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

        datosContactoAtributosVBox.setSpacing(10);
        contactoImg.setImage(new Image(photoContacto, 64,64,false,false)); //carga la foto del contacto
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
        agregarContactosAcontactosRelacionadosVBox();
    }
    
    private void cargarTagsContactoSeleccionadoHBox(){
        contactoTagsFlowPane.getChildren().clear();
        for(String tag: contactoSeleccionado.getTags()){
            if(!tag.equals("Todo")){
                HBox cajaTag = crearTagContactoSeleccionado(tag);
                contactoTagsFlowPane.getChildren().add(cajaTag);
            }
        }
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
    
    private void agregarContactosAcontactosRelacionadosVBox(){
        contactosRelacionadosVBox.getChildren().clear();
        for(Contacto contacto: contactoSeleccionado.getContactosRelacionados()){
            HBox cajaContactoRelacionado = new HBox();
            HBox contactoRelacionado = new HBox();
            contactoRelacionado.setAlignment(Pos.CENTER);
            String photo = PHOTO_DEFAULT;
            if(contacto.hasPhotos()){
                photo = contacto.getFirstPhoto();
            }
            ImageView fotoContactoRelacionado = new ImageView(new Image(photo, 64,64,false,false));
            
            contactoRelacionado.getChildren().add(fotoContactoRelacionado);
            Label labelName = new Label(contacto.getName());
            contactoRelacionado.getChildren().add(labelName);
            Label labelNumber = new Label(contacto.getNumber());
            cajaContactoRelacionado.getChildren().add(contactoRelacionado);
            
            Button borrarContactoRelacionadoButton = new Button("X");
            borrarContactoRelacionadoButton.setCursor(Cursor.HAND);
            borrarContactoRelacionadoButton.setOnMouseClicked((e)->{
                contactoSeleccionado.removeContactoRelacionado(contacto);
                contactosRelacionadosVBox.getChildren().remove(cajaContactoRelacionado);
            });
            cajaContactoRelacionado.getChildren().add(borrarContactoRelacionadoButton);

            contactoRelacionado.setCursor(Cursor.HAND);
            contactoRelacionado.setOnMouseClicked((e)->{
                labelNameFX = labelName;
                labelNumberFX = labelNumber;
                preCargarDatosContactoSeleccionado(contacto);
                cargarDatosContacto(contacto);
            });
            
            contactosRelacionadosVBox.getChildren().add(cajaContactoRelacionado);
        }
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
    
    private void bindLabelconTextField(Label label, TextField textField){
        label.textProperty().bind(textField.textProperty());//Enlaza el labelName que viene del panel Izquierdo con el name que se muestra en el lado derecho
    }
    
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
    
    //Recibe un contacto de donde se sacara guardara el Atributo que va en el label y un Pane que lo contiene
    private Label crearLabelEditable(String key, Pane pane){
        Label label=  new Label(key);
        label.setCursor(Cursor.HAND);
        label.setOnMouseClicked((e)->{
                editarAtributteContacto(contactoSeleccionado, key, label, pane);
            }
        );
        return label;
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
    
    private void updateAtributteContacto(Contacto contacto, String key, Label label, TextField txtLabel, Pane pane){
        label.setText(txtLabel.getText());
        pane.getChildren().remove(txtLabel);
        pane.getChildren().add(0, label);
        contacto.updateKeyAtributte(key, label.getText());
        cargarDatosContacto(contacto);
    }
    
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
            contacto.setName(name);
        }
        );
        
        pane.setOnMouseExited(e->{//Cuando el mouse sale del HBox entonces guarda los datos del Campo de texto
            campoTexto.setDisable(true);
            contacto.setName(name);
        }
        );
        
        if(label.getText().equals("Name") ){
            bindLabelconTextField(labelNameFX, campoTexto);
        }
        return campoTexto;
    }
    
    private TextField crearTextFieldNumber(Contacto contacto, String number, Label label, Pane pane){
        TextField campoTexto = new TextField(number);
        
        campoTexto.setDisable(true);//pone el campo de texto desactvado
        pane.setOnMouseEntered(e->{campoTexto.setDisable(false);});//Cuando el mouse pasa encima del HBox el Campo de texto se activa
        campoTexto.setOnAction(e->{//Cuando el usuario da enter en el txtField entonces guarda los datos del Campo de texto
            contacto.setNumber(number);
            campoTexto.setDisable(true);
        }
        );
        
        pane.setOnMouseExited(e->{//Cuando el mouse sale del HBox entonces guarda los datos del Campo de texto
            contacto.setNumber(number);
            campoTexto.setDisable(true);
        }
        );
        
        if(label.getText().equals("Number")){
            bindLabelconTextField(labelNumberFX, campoTexto);
        }
        return campoTexto;
    }
    
    private void editarValueAtributte(Contacto contacto, String key, String oldTxt, TextField campoTexto){
        if(!campoTexto.getText().equals(oldTxt)){
            contacto.setAtributte(key, campoTexto.getText());//actualiza los datos del contacto en el campo de texto 
        }
        campoTexto.setDisable(true);//Desabilita el campo de texto
    }
 
    private void mostrarContacto(Contacto contacto){
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
        //datosContacto.setAlignment(Pos.TOP_RIGHT);

        VBox favoritos = new VBox();

        Button fav =new Button();
        fav.setAlignment(Pos.CENTER);
        
        datosContacto.setAlignment(Pos.BASELINE_RIGHT);
       Image imagen = new Image("/img/estrelladark.png");
        
       if(contacto.isFavorite()){
            imagen = new Image("/img/estrellalight.png");
            contacto.addTag("Favoritos");
        }else{
           contacto.removeTag("Favoritos");
       }
        // Crear un ImageView con la imagen
        ImageView imageView = new ImageView(imagen);
        // Establecer el ImageView como gráfico del botón
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
       // fav.setPrefSize(5, 5);
        fav.setGraphic(imageView);
        fav.setStyle("-fx-background-color: transparent;");
        HBox.setMargin(fav, new Insets(0, 0, 0, 50));
        fav.setOnAction(e->{
        if(contacto.isFavorite()){
            imageView.setImage(new Image("/img/estrelladark.png"));
            contacto.setFavorito(false);
            contacto.removeTag("Favoritos");
            agregarButtonTagsEnElHBox();
            
       }else{
           imageView.setImage(new Image("/img/estrellalight.png"));
           contacto.setFavorito(true);
            contacto.addTag("Favoritos");
            agregarButtonTagsEnElHBox();
        }
        });
        favoritos.getChildren().add(fav);
        cajaContacto.getChildren().add(favoritos);

        //VBox favoritos = new VBox();
//        Button fav =new Button();
//        fav.setAlignment(Pos.CENTER);
//        
//        datosContacto.setAlignment(Pos.BASELINE_RIGHT);
//        Image imagen = new Image("/img/estrelladark.png");
//        // Crear un ImageView con la imagen
//        ImageView imageView = new ImageView(imagen);
//        // Establecer el ImageView como gráfico del botón
//        imageView.setFitWidth(20);
//        imageView.setFitHeight(20);
//       // fav.setPrefSize(5, 5);
//        fav.setGraphic(imageView);
//        fav.setStyle("-fx-background-color: transparent;");
//        HBox.setMargin(fav, new Insets(0, 0, 0, 15));
//        fav.setOnAction(e->{
//        if(contacto.isFavorite()){
//            imageView.setImage(new Image("/img/estrelladark.png"));
//            System.out.println("cambio");
//            contacto.setFavorito(false);
//            
//        }else{
//            contacto.setFavorito(true);
//            imageView.setImage(new Image("/img/estrellalight.png"));
//            System.out.println("cambiooo");
//        }
       // });
        //favoritos.getChildren().add(fav);
        //cajaContacto.getChildren().add(fav);

        cajaContacto.setCursor(Cursor.HAND);
        cajaContacto.setOnMouseClicked((e)->{
            if(internal!=null){
                internal.setVisible(false);
            }
            datosContactoVBox.setVisible(true);
            labelNameFX = labelName;
            labelNumberFX = labelNumber;
            imgBienvenida.setVisible(false);
            preCargarDatosContactoSeleccionado(contacto);
            cargarDatosContacto(contacto);            
        });
        contactosVBox.getChildren().add(cajaContacto);
    }
    
    private void preCargarDatosContactoSeleccionado(Contacto contacto){
        photoIterator = contacto.getPhotos().listIterator();
        contactoSeleccionado = contacto;
        photoContacto = PHOTO_DEFAULT;
        this.leftPhotos.setDisable(true);
        this.rightPhotos.setDisable(true);
        this.deletePhoto.setDisable(true);
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
        photoIterator = contactoSeleccionado.getPhotos().listIterator();
        cargarDatosContacto(contactoSeleccionado);
    }
    
    @FXML
    public void addTagContactoSeleccionado(){
        String tag = nuevaTagTextField.getText();
        if(!tag.equals("") && !contactoSeleccionado.getTags().contains(tag)){
            contactoSeleccionado.addTag(tag);
            contactoTagsFlowPane.getChildren().add(crearTagContactoSeleccionado(tag));
            agregarButtonTagsEnElHBox();
        }
    }
    
    @FXML
    public void addContactoVinculado(){
        Contacto contacoParaAsociar = contactosRelacionadosComboBox.getSelectionModel().getSelectedItem();
        contacoParaAsociar.addContactoRelacionado(contactoSeleccionado);
        photoIterator = contactoSeleccionado.getPhotos().listIterator();
        cargarDatosContacto(contactoSeleccionado);
    }
    
    @FXML
    public void deleteContact(){
        List<Contacto> contactosVinculados = new ArrayList();
        for(Contacto c: contactoSeleccionado.getContactosRelacionados()){
            contactosVinculados.addLast(c);
        }
        
        for(Contacto vinculado: contactosVinculados){
            vinculado.removeContactoRelacionado(contactoSeleccionado);
        }
        agenda.Sistema.contactos.remove(contactoSeleccionado);
        datosContactoAtributosVBox.getChildren().clear();
        datosContactoVBox.setVisible(false);
        cargarContactosPanelIzquierdo(agenda.Sistema.contactos);
    }
    
    //Crear contacto
    
    
    @FXML
    public void crearcontacto(ActionEvent event) throws IOException{
        
        FXMLLoader internalLoader = new FXMLLoader(App.class.getResource("CrearContacto"+".fxml"));
        internal = internalLoader.load();
        anchorPaneContac.getChildren().add(internal);
        datosContactoVBox.setVisible(false);

        // Cargando la nueva escena desde el archivo FXML
        /*Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("CrearContacto.fxml"));
                    }catch (IOException e) {
                    }
                    
                    Scene scene = new Scene(root, 900, 500);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();*/
        
        


        /*
        try{
        FXMLLoader loader =new FXMLLoader(Stage secondStage);
        loader.setLocation(getClass().getResource("CrearContacto"));
        //imgBienvenida.setVisible(false);
        //datosContactoVBox.setVisible(true);
        Parent root = loader.load();
        Scene s = new Scene(root);
        secondStage.setScene(s);
        ContactosController controller = loader.getController();
        controller.setStage(secondStage);
        secondStage.show();
        }catch(IOException e){
            e.printStackTrace();
        }*/
        
    }
    //Guardar contacto
    public void guardarContacto(){
        
        
    }
    
    //Ordena por Nombre, Apelldio, Fecha, Derecha
    @FXML
    public void OrdenarNombre(){
        CircularList<Contacto> nombre = new DoublyLinkedList();
        for(Contacto c: agenda.Sistema.contactos){
            nombre.addLast(c);
        }
        
        Comparator<Contacto> cmp = (Contacto c1, Contacto c2) -> {   
           return c1.getName().compareTo(c2.getName());
        };

        nombre.sort(cmp);

        datosContactoAtributosVBox.getChildren().clear();
        imgBienvenida.setVisible(true);
        datosContactoVBox.setVisible(false);
        cargarContactosPanelIzquierdo(nombre);  
    }
    
    @FXML
    public void OrdenarApellido(){
        CircularList<Contacto> apellido = new DoublyLinkedList();
        for(Contacto c: agenda.Sistema.contactos){
            if(c.getValorAtributte("Apellido") !=null){
                apellido.addLast(c);
            }
        }
        Comparator<Contacto> cmp = (Contacto c1, Contacto c2) -> {     
            return c1.getValorAtributte("Apellido").compareTo(c2.getValorAtributte("Apellido")); 
        };

       apellido.sort(cmp);

        datosContactoAtributosVBox.getChildren().clear();
        imgBienvenida.setVisible(true);
        datosContactoVBox.setVisible(false);
        cargarContactosPanelIzquierdo(apellido);  
    }
    
    @FXML
    public void OrdenarFecha(){
        CircularList<Contacto> fecha = new DoublyLinkedList();
        for(Contacto c: agenda.Sistema.contactos){
            if(c.getValorAtributte("Fecha") !=null){
                fecha.addLast(c);
            }
        }
        Comparator<Contacto> cmp = (Contacto c1, Contacto c2) -> {    
           return c1.getValorAtributte("Fecha").compareTo(c2.getValorAtributte("Fecha"));
        };
        fecha.sort(cmp);

        datosContactoAtributosVBox.getChildren().clear();
        imgBienvenida.setVisible(true);
        datosContactoVBox.setVisible(false);
        cargarContactosPanelIzquierdo(fecha);  
    }
    
    @FXML
    public void OrdenarDireccion(){
        CircularList<Contacto> direccion = new DoublyLinkedList();
        for(Contacto c: agenda.Sistema.contactos){
            if(c.getValorAtributte("Direccion") !=null){
                direccion.addLast(c);
            }
        }
        Comparator<Contacto> cmp = (Contacto c1, Contacto c2) -> {
           return c1.getValorAtributte("Direccion").compareTo(c2.getValorAtributte("Direccion"));
        };
        direccion.sort(cmp);

        datosContactoAtributosVBox.getChildren().clear();
        imgBienvenida.setVisible(true);
        datosContactoVBox.setVisible(false);
        cargarContactosPanelIzquierdo(direccion);  
    }
    
    
   
    
    
    
    @FXML
    public void nextContactPhoto(){
        if(avanzoPhoto){
            for(ListIterator<Contacto> itList: iterators1){
                itList.next();
                itList.next();
            }
        }
        for(ListIterator<Contacto> itList: iterators1){
            mostrarContacto(itList.next());
        }
        photoContacto = photoIterator.next();
        avanzoPhoto = true;
        retrocedioPhoto = false;
        contactoImg.setImage(new Image(photoContacto, 64,64,false,false));
    }
    
    @FXML
    public void previousContactPhoto(){
        if(retrocedioPhoto){
            for(ListIterator<Contacto> itList: iterators1){
                itList.previous();
                itList.previous();
            }
        }
        for(ListIterator<Contacto> itList: iterators1){
            mostrarContacto(itList.previous());
        }
        photoContacto = photoIterator.previous();
        avanzoPhoto = false;
        retrocedioPhoto = true;
        contactoImg.setImage(new Image(photoContacto, 64,64,false,false));
    }
    
    @FXML 
    public void scrollMouseContacts(){
        contactosVBox.setOnScroll(event -> {
            double deltaY = event.getDeltaY();
            if (deltaY > 0) {
                retrocederContactos();
            } else {
                avanzarContactos();
            }
        });
    }
    

    //Agregar fotos a los contactos
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
        System.out.println(imgFiles);
        for(int i=0;i<imgFiles.size();i++){
        if (imgFiles.get(i) != null) {
            try {
                String imagePath = imgFiles.get(i).toURI().toURL().toExternalForm();
                contactoSeleccionado.addPhoto(imagePath);
            } catch (Exception e) {
                // Maneja la excepción si ocurre un error al cargar la imagen
                e.printStackTrace();
            }
        }
            photoIterator = contactoSeleccionado.getPhotos().listIterator();
            cargarDatosContacto(contactoSeleccionado);
            cargarContactosPanelIzquierdo(agenda.Sistema.contactos);
        }
            
    }

    @FXML
    private void eliminarPhoto(ActionEvent event) {
        if (contactoSeleccionado.getPhotos().size()>0){
            //photoContacto = photoIterator.next();
            deletePhoto.setDisable(false);
            contactoSeleccionado.removePhoto(photoContacto);
            
            photoIterator = contactoSeleccionado.getPhotos().listIterator();
  //          cargarDatosContacto(contactoSeleccionado);
//            cargarContactosPanelIzquierdo(agenda.Sistema.contactos);
        }
        cargarDatosContacto(contactoSeleccionado);
        cargarContactosPanelIzquierdo(agenda.Sistema.contactos);
          
    }
    
    private void avanzarIterator1(int steps, Iterator it){
        for(int i=1;i<steps;i++){
            it.next();
        }
    }
    
    private List<ListIterator<Contacto>> prepararIteradores1(CircularList list, int numIterators){
        iterators1 = new ArrayList<>();
        avanzoPhoto = true;
        for(int i = 0; i < numIterators; i++){
            ListIterator<Contacto> it = list.listIterator();
            avanzarIterator1(i+2, it);//Hace que los elementos avancen la posicion
            iterators1.addLast(it);
        }
    return iterators1;
}

    /*private void setStage(Stage secondStage) {
        
        }*/

}
