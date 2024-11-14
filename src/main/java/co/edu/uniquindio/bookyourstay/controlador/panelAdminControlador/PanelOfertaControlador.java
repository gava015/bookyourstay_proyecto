package co.edu.uniquindio.bookyourstay.controlador.panelAdminControlador;

import co.edu.uniquindio.bookyourstay.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.Oferta;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PanelOfertaControlador {
    @FXML
    private ComboBox<TipoAlojamiento> cbTipoAlojamiento;

    @FXML
    private TextField txtDescuento;

    @FXML
    private DatePicker dpFechaInicio;

    @FXML
    private DatePicker dpFechaFin;

    @FXML
    private TableView<Oferta> tablaOfertas;

    @FXML
    private TableColumn<Oferta,String> colAlojamiento;

    @FXML
    private TableColumn<Oferta,String> colDescuento;

    @FXML
    private TableColumn<Oferta,String> colFechaDescuento;


}
