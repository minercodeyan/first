package sample;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class DelController extends Controller{

    @FXML
    private Button del1button;
    @FXML
    private TextField portTextField;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    void initialize() {
      del1button.setOnAction(event -> {
          del1button.getScene().getWindow().hide();
     });

    }
}

