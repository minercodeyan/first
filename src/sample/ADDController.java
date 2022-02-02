package sample;
import data.SocketClass;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class ADDController extends Controller{
    @FXML
    private Button add1button;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField tf1;
    @FXML
    private TextField tf2;
    @FXML
    private TextField tf3;
    @FXML
    private TextField tf4;
    public  String s ="insert@";
    @FXML
    void initialize() {

        add1button.setOnAction(event -> {
                       s+=tf1.getText()+"@";
                        s+=tf2.getText()+"@";
                        s+=tf3.getText()+"@";
                        s=s+tf4.getText();
            try {
                SocketClass.send("insert@" + tf1.getText() + "@" + tf2.getText() + "@" +
                        tf3.getText() + "@" + tf4.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
            add1button.getScene().getWindow().hide();
        });
    }
}

