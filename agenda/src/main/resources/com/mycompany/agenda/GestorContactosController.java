/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.agenda;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author nhale
 */
public class GestorContactosController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
