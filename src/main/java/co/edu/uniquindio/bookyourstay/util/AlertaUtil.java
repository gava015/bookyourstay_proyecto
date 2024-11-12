package co.edu.uniquindio.bookyourstay.util;

import javafx.scene.control.Alert;

public class AlertaUtil {
    public static void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }
}
