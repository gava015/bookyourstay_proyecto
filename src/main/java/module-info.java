module co.edu.uniquindio.bookyourstay {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires org.simplejavamail.core;
    requires org.simplejavamail;

    opens co.edu.uniquindio.bookyourstay to javafx.fxml;
    exports co.edu.uniquindio.bookyourstay;
    exports co.edu.uniquindio.bookyourstay.controlador;
    opens co.edu.uniquindio.bookyourstay.controlador to javafx.fxml;
    exports co.edu.uniquindio.bookyourstay.controlador.panelAdminControlador;
    opens co.edu.uniquindio.bookyourstay.controlador.panelAdminControlador to javafx.fxml;
}