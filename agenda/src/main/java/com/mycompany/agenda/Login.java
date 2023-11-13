package com.mycompany.agenda;


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login {

   @FXML
    private TextField usrName ;
    @FXML
    private PasswordField usrPass ;   
    @FXML
    private Label error ;
    @FXML
    private Button boton ;
    private int ingreso;
    private int intentos = 0;
    static private String usuario;
    private static Parent root = null;
    @FXML
    private void  cerrar(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void Inicio() throws IOException {
        ingreso = agenda.Sistema.comprobarUsuario(usrName.getText().strip(), usrPass.getText().strip());
        usuario = usrName.getText().strip();
        usrName.clear();
        usrPass.clear();
        switch(ingreso){
            case 1:
                error.setText("");
                ventana("EstiloGestorContacto");
                
                break;
            case 2:
                error.setText("");
                ventana("empresa");
             
                break;
            case 3:         
                error.setText("");
                 ventana("persona");
                break;
            default:
                intentos ++;
                if (intentos == 3){
                    usrName.setDisable(true);
                    usrPass.setDisable(true);
                    boton.setDisable(true);
                    error.setText("Se ha bloqueado el sistema >:)");
                }
                usrName.clear();
                usrPass.clear();
                error.setText("¡Error! usuario o contraseña incorrecto \n Se va a bloquear el sistema al 3er intento " + intentos +"/3" );
                break;
        }
    }
    public void ventana(String s){
         try {
                         String x = s+".fxml";
                        root = FXMLLoader.load(getClass().getResource(x));
                    }catch (IOException e) {
                    }
                PreCarga1.getStage().setResizable(false);
                PreCarga1.getStage().setMinWidth(900);
                PreCarga1.getStage().setMinHeight(720);
                PreCarga1.getStage().setTitle("Contactos de "+ Capitalizar(s));
                PreCarga1.getScene().setRoot(root);
                
    
    }
    public static String Capitalizar(String str)
    {
        if (str == null || str.length() == 0) {
            return str;
        }
 
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    
    public static String getUsuario() {
        return usuario;
    }
             
}