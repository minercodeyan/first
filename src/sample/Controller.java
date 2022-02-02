package sample;
import java.io.*;
import java.net.*;
import java.net.URL;
import java.util.ResourceBundle;
import data.SocketClass;
import data.Car;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller extends Main{
    public static Stage PrimaryStage;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField ipfield;
    @FXML
    private TextField portfield;
    @FXML
    private Button connectbutton;
    @FXML
    private TableView<Car> auto;
    @FXML
    private TableColumn<Car, String> autoid;
    @FXML
    private TableColumn<Car, String> automark;
    @FXML
    private TableColumn<Car, String> autouser;
    @FXML
    private TableColumn<Car, String> autoprise;
    @FXML
    private TableColumn<Car, String> autokm;

    @FXML
    private Button delbutton;
    @FXML
    private Button addbutton;
    @FXML
    private Button chbutton;
    @FXML
    private Button loadButton;

    private ObservableList<Car> getProfessionData(String[] data) {
        ObservableList<Car> list = FXCollections.observableArrayList();
        for (String datum : data) {
            String[] row = datum.split("@");
            Car d = new Car((row[0]), row[1], row[2], row[3], row[4]);
            list.add(d);
        }
        return list;
    }
    @FXML
    void initialize() {
        addbutton.setDisable(true);
        delbutton.setDisable(true);
        chbutton.setDisable(true);
        loadButton.setDisable(true);
        connectbutton.setOnAction(event -> {
            try {
                SocketClass.createSocket(ipfield.getText(), Integer.parseInt(portfield.getText()));
              if(SocketClass.socket.isConnected()){
                  addbutton.setDisable(false);
                  delbutton.setDisable(false);
                  chbutton.setDisable(false);
                  loadButton.setDisable(false);
                  connectbutton.setDisable(true);
              }
            } catch (NumberFormatException e) {
            } catch (UnknownHostException e) {
            } catch (IOException e) {
            }

        });

        loadButton.setOnAction(event ->{
            try {
                auto.setItems(getProfessionData(SocketClass.sendAndGet("show@").split("@@")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            autoid.setCellValueFactory(cell -> cell.getValue().idProperty());
            automark.setCellValueFactory(cell -> cell.getValue().markProperty());
            autoprise.setCellValueFactory(cell -> cell.getValue().userProperty());
            autouser.setCellValueFactory(cell -> cell.getValue().priseProperty());
            autokm.setCellValueFactory(cell -> cell.getValue().kmProperty());
        } );

        delbutton.setOnAction(event -> {
                    Car car = auto.getSelectionModel().getSelectedItem();
                    if (car != null) {
                        try {
                            String reply = SocketClass.sendAndGet("delete@" + car.getId() + "@");
                            if (reply.equals("error")) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setContentText("There is no record in database consisting the name you've entered, please " +
                                        "try something else");
                                alert.showAndWait();
                            } else {
                                FXMLLoader loader=new FXMLLoader();
                                loader.setLocation(getClass().getResource("/View/delete.fxml"));
                                try {
                                    loader.load();
                                    Parent root = loader.getRoot();
                                    Stage stage = new Stage();
                                    stage.setScene(new Scene(root));
                                    stage.showAndWait();
                                    return;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Selection error");
                        alert.setContentText("You have not selected the row");
                        alert.showAndWait();
                    }
            });
        addbutton.setOnAction(event -> {
            FXMLLoader loader1=new FXMLLoader();
            loader1.setLocation(getClass().getResource("/View/add.fxml"));
            try {
                loader1.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader1.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        }  );

    }

}
