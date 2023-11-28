package com.mycompany.agenda;


import static agenda.Sistema.cargarContactos;
import static agenda.Sistema.usuarios;
import agenda.Usuario;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login {

    @FXML
    private TextField usrName, textFieldNuevoNombre, textFieldNuevoUsuario;
    @FXML
    private PasswordField usrPass, passwordFieldNuevo;   
    @FXML
    private Label error, errorCrearUsuario ;
    @FXML
    private Button buttonEntrar;
    @FXML
    private RadioButton administradorRadioButton, normalRadioButton;
    private ToggleGroup tg = new ToggleGroup();
    @FXML
    private VBox vBoxIniciarSesion, vBoxCrearUsuario;
    private boolean ingreso;
    private int intentos = 0;
    static private String usuario;
    static private String nombre;
    private static Parent root = null;
    private Stage stage;
    
    @FXML
    private void initialize(){
        administradorRadioButton.setToggleGroup(tg);
        administradorRadioButton.setUserData("admin");
        normalRadioButton.setToggleGroup(tg);
        normalRadioButton.setUserData("normal");
        normalRadioButton.setSelected(true);
        usrPass.setOnAction((e)->inicio());
    }
    
    @FXML
    private void  cerrar() {
        if (stage != null){
            stage.close();
        }
    }
    
    @FXML
    private void inicio(){
        ingreso = agenda.Sistema.comprobarUsuario(usrName.getText().strip(), usrPass.getText().strip());
        usrName.clear();
        usrPass.clear();
        if(ingreso){
            usuario = agenda.Sistema.usuario.getNombre();
            if(agenda.Sistema.usuario.getTipo().equals("admin")){
                agenda.Sistema.cargarContactosComoAdministrador();
            }else{
                agenda.Sistema.cargarContactos(agenda.Sistema.usuario);
            }
            ventana();
            error.setText("");
        }
        else{
            intentos ++;
            if (intentos == 3){
                usrName.setDisable(true);
                usrPass.setDisable(true);
                buttonEntrar.setDisable(true);
                error.setText("Se ha bloqueado el sistema >:)");
            }
            usrName.clear();
            usrPass.clear();
            error.setText("¡Error! usuario o contraseña incorrecto \n Se va a bloquear el sistema al 3er intento " + intentos +"/3" );
        }
    }
    
    @FXML
    private void crearUsuario(){
        vBoxIniciarSesion.setVisible(false);
        vBoxIniciarSesion.setDisable(true);
        vBoxCrearUsuario.setVisible(true);
        vBoxCrearUsuario.setDisable(false);
    }
    
    @FXML
    private void crear(){
        String userName = textFieldNuevoUsuario.getText().strip();
        String password = passwordFieldNuevo.getText().strip();
        String newName = textFieldNuevoNombre.getText().strip();
        if(!userName.equals("") && !password.equals("") && !newName.equals("")){
            Usuario nuevoUsuario = new Usuario(userName, password, newName, tg.getSelectedToggle().getUserData().toString());
            agenda.Sistema.usuarios.addLast(nuevoUsuario);
            agenda.Sistema.guardarUsuarios();
            usrName.setText(userName);
            textFieldNuevoUsuario.clear();
            passwordFieldNuevo.clear();
            textFieldNuevoNombre.clear();
            Alert a = new Alert(AlertType.INFORMATION);
            a.setTitle("ÉXITO");
            a.setHeaderText("Información");
            a.setContentText("¡¡USUARIO CREADO CON ÉXITO!!");
            a.show();
            cancelar();
        }else{
            errorCrearUsuario.setText("¡Error! usuario, nombre o contraseña VACÌO");
        }
                
    }
    
    @FXML
    private void cancelar(){
        vBoxIniciarSesion.setVisible(true);
        vBoxIniciarSesion.setDisable(false);
        vBoxCrearUsuario.setVisible(false);
        vBoxCrearUsuario.setDisable(true);
    }
    
    public void ventana(){
        try {
            root = FXMLLoader.load(getClass().getResource("EstiloGestorContacto.fxml"));
        }catch (IOException e){
        }
        PreCarga1.getStage().setResizable(false);
        PreCarga1.getStage().setMinWidth(900);
        PreCarga1.getStage().setMinHeight(740);
        PreCarga1.getStage().setTitle("Contactos de "+ capitalizar(usuario));
        PreCarga1.getScene().setRoot(root);
                
    
    }
    public static String capitalizar(String str)
    {
        if (str == null || str.length() == 0) {
            return str;
        }
 
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    
    public static String getUsuario() {
        return usuario;
    }
    
    public void cargarStage(Stage stage){
        this.stage = stage;
    }
}