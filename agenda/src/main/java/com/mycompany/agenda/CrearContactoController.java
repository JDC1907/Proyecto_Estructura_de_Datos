
package com.mycompany.agenda;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 * FXML Controller class
 *
 * @author JDC_1
 */
public class CrearContactoController implements Initializable {


    @FXML
    private VBox vBoxCrear;
    @FXML
    private VBox datosContactoVBox;
    @FXML
    private Button leftPhotos;
    @FXML
    private ImageView contactoImg;
    @FXML
    private Button rightPhotos;
    @FXML
    private Button addPhoto;
    @FXML
    private Button deletePhoto;
    @FXML
    private VBox datosContactoNameNumberVBox;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textNumero;
    @FXML
    private VBox datosContactoAtributosVBox;
    @FXML
    private Button addAtributteButton;
    @FXML
    private VBox fechasVBox;
    @FXML
    private Label nameLabel1;
    @FXML
    private TextField nameTextField1;
    @FXML
    private Button addAtributteButton1;
    @FXML
    private FlowPane contactoTagsFlowPane;
    @FXML
    private HBox contactoTagsHBox;
    @FXML
    private Label nameLabel12;
    @FXML
    private TextField nuevaTagTextField;
    @FXML
    private Button addTagButton;
    @FXML
    private VBox contactosRelacionadosVBox;
    @FXML
    private Label nameLabel11;
    @FXML
    private Label nameLabel111;
    @FXML
    private ComboBox<?> contactosRelacionadosComboBox;
    @FXML
    private Button addContactoVinculadoButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void previousContactPhoto(ActionEvent event) {
    }

    @FXML
    private void nextContactPhoto(ActionEvent event) {
    }

    @FXML
    private void agregarPhoto(ActionEvent event) {
    }

    @FXML
    private void eliminarPhoto(ActionEvent event) {
    }

    @FXML
    private void addAtributte(ActionEvent event) {
    }

    @FXML
    private void addTagContactoSeleccionado(ActionEvent event) {
    }

    @FXML
    private void addContactoVinculado(ActionEvent event) {
    }

    @FXML
    private void guardarContacto(ActionEvent event) {
    }

}
