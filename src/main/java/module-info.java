module co.edu.uniquindio.bookyourstayco.bookyourstayco {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.bookyourstayco.bookyourstayco to javafx.fxml;
    exports co.edu.uniquindio.bookyourstayco.bookyourstayco;
}