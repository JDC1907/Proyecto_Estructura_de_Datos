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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
    @FXML
    private Button btsiguienteFoto;
    @FXML
    private Button btanteriorFoto;
    @FXML
    private Button bteliminarFoto;
    @FXML
    private Button btagregarFoto;
    @FXML
    private MenuButton MbtOrdenarpor;
    @FXML
    private MenuItem MApellidoNombre;
    @FXML
    private MenuItem MmayorContenido;
    @FXML
    private MenuItem MfechaCumpleaños;
    @FXML
    private MenuItem Mempresa;
    @FXML
    private MenuItem MpaisResisdencia;
    @FXML
    private MenuItem Mpersona;
    @FXML
    private MenuItem Empresa;
    @FXML
    private MenuItem MredesSociales;
    @FXML
    private Menu Mciudad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
