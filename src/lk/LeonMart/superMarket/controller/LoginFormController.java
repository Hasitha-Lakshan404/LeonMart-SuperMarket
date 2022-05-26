package lk.LeonMart.superMarket.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    public JFXComboBox<String> cmbType;
    public AnchorPane apnMainPane;

    public void initialize() {
        cmbType.getItems().addAll("Admin", "Cashier");
    }

    public void logInOnAction(ActionEvent actionEvent) throws IOException {
        lordWindow();
//        superMarket/view/LoginForm.fxml
    }

    public void lordWindow() throws IOException {

        Stage stage = (Stage) apnMainPane.getScene().getWindow();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/lk/LeonMart/superMarket/view/HomeForm.fxml"));

        Parent root1 = loader1.load();
        Scene scene1 = new Scene(root1);

        stage.setScene(scene1);

        HomeFormController controller = loader1.getController();
        controller.getAllData(cmbType.getValue());

        stage.centerOnScreen();

    }


}
