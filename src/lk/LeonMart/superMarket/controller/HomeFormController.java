package lk.LeonMart.superMarket.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

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
    public Label lblHomeTitleShow;

    public JFXButton btnAllOrder;
    public JFXButton btnDashboard;
    public AnchorPane apnMain;

    public Label lblTime;
    public Label lblDate;


    String userNameShow;


    public void initialize() throws IOException {
        setDate();
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
                lblHomeTitleShow.setText("Customer");

            } else if (btn.getId().equals("ItemButton")) {
                this.setUI("ItemForm");
                lblHomeTitleShow.setText("Item");

            } else if (btn.getId().equals("OrderButton")) {
                setUI("OrderForm");
                lblHomeTitleShow.setText("Order");

            } else if (btn.getId().equals("ReportButton")) {
                setUI("MainReportForm");
                lblHomeTitleShow.setText("Reports");

            } else if (btn.getId().equals("SettingsButton")) {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/LoginForm.fxml"));
                Parent parent= loader.load();
                Stage stage =new Stage(StageStyle.DECORATED);
                stage.setTitle("Login");
                stage.setScene(new Scene(parent));
                stage.show();

                Stage stage1 = (Stage) btnCustomer.getScene().getWindow();
                stage1.close();


            }else if (btn.getId().equals("dashBoardButton")) {
                setUI("DashboardForm");
                lblHomeTitleShow.setText("Dashboard");

            }else if (btn.getId().equals("allOrderButton")) {
                setUI("AllOrdersForm");
                lblHomeTitleShow.setText("All Orders");
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

    public void setDate() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalDate currentDate = LocalDate.now();

            //Displaying current time in 12hour format with AM/PM
            DateFormat dateFormat = new SimpleDateFormat("hh : mm : ss aa");
            String dateString = dateFormat.format(new Date()).toString();
            lblTime.setText(dateString);



            String month = "";
            switch (currentDate.getMonthValue()) {
                case 1:
                    month = "Jan";
                    break;
                case 2:
                    month = "Feb";
                    break;
                case 3:
                    month = "March";
                    break;
                case 4:
                    month = "April";
                    break;
                case 5:
                    month = "May";
                    break;
                case 6:
                    month = "June";
                    break;
                case 7:
                    month = "July";
                    break;
                case 8:
                    month = "August";
                    break;
                case 9:
                    month = "Sep";
                    break;
                case 10:
                    month = "Oct";
                    break;
                case 11:
                    month = "Nov";
                    break;
                case 12:
                    month = "Dec";
                    break;

            }
            lblDate.setText(currentDate.getYear() + "-" + month + "-" + currentDate.getDayOfMonth());


        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
}
