module gestorcontacto.proyecto_estructura {
    requires javafx.controls;
    requires javafx.fxml;

    opens gestorcontacto.proyecto_estructura to javafx.fxml;
    exports gestorcontacto.proyecto_estructura;
}
