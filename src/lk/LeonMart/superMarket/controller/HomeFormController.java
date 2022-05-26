package lk.LeonMart.superMarket.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class HomeFormController {

    public Rectangle icnRecSide;
    public AnchorPane apnSideNamePane;
    public AnchorPane apnMainPane;
    public JFXButton it;
    public Label lblUserShow;
    public JFXButton btnSettings;
    public JFXButton btnCustomer;
    public JFXButton btnItem;
    public JFXButton btnOrder;
    public JFXButton btnReport;
    String userNameShow;


    public void initialize() throws IOException {
        apnSideNamePane.setVisible(false);

        setUI("DashboardForm");

    }

    public void iconSideMouseEnteredOnAction(MouseEvent mouseEvent) {
        apnSideNamePane.setVisible(true);
    }

    public void apnNameMouseExitedOnAction(MouseEvent mouseEvent) {
        apnSideNamePane.setVisible(false);
    }

    public void setUI(String URI) throws IOException {
        apnMainPane.getChildren().clear();
        apnMainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/lk/LeonMart/superMarket/view/" + URI + ".fxml")));
    }

    public void buttonsClickOnAction(MouseEvent mouseEvent) throws IOException {
        Object o = mouseEvent.getSource();

        if (o instanceof JFXButton) {
            JFXButton btn = (JFXButton) o;

            if (btn.getId().equals("customerButton")) {
                setUI("CustomerForm");
            } else if (btn.getId().equals("ItemButton")) {
                this.setUI("ItemForm");

            } else if (btn.getId().equals("OrderButton")) {

            } else if (btn.getId().equals("ReportButton")) {

            } else if (btn.getId().equals("SettingsButton")) {

            }

        }
    }

    public void getAllData(String value) throws IOException {
        this.userNameShow = value;
//
//        initialize();
        lblUserShow.setText(value);

        if(value.equalsIgnoreCase("Admin")){
            btnOrder.setDisable(true);
            btnCustomer.setDisable(true);
        }else if(value.equalsIgnoreCase("Cashier")){
            btnReport.setDisable(true);
            btnItem.setDisable(true);
        }

    }
}
