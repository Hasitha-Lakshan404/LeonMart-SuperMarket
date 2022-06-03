/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :6/2/2022
 * Time :11:29 PM
 */

package lk.LeonMart.superMarket.controller;


import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInRight;
import animatefx.animation.SlideInUp;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainReportFormController {

    public TableView tblLeastItem;
    public TableView tblMostItem;
    public AnchorPane apnMain;
    public JFXButton btnAnnually;

    public void initialize() {

    }


    public void btnMonthlyReportOnAction(ActionEvent actionEvent) throws IOException {
        new SlideInUp(apnMain).play();
        apnMain.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/MonthlyReportForm.fxml"));
        apnMain.getChildren().add(parent);
    }

    public void btnAnnuallyReportOnAction(ActionEvent actionEvent) throws IOException {
        new SlideInUp(apnMain).play();
        apnMain.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/AnnuallyReportForm.fxml"));
        apnMain.getChildren().add(parent);
    }

    public void btnDailyReportOnAction(ActionEvent actionEvent) throws IOException {
        new SlideInUp(apnMain).play();
        apnMain.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/DailyReportForm.fxml"));
        apnMain.getChildren().add(parent);
    }
}
