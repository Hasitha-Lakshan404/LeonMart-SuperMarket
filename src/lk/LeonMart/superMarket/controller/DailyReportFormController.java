/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :6/3/2022
 * Time :7:23 PM
 */

package lk.LeonMart.superMarket.controller;


import animatefx.animation.SlideInDown;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.LeonMart.superMarket.bo.BOFactory;
import lk.LeonMart.superMarket.bo.custom.DailyIncomeReportBO;
import lk.LeonMart.superMarket.dto.CustomDTO;
import lk.LeonMart.superMarket.view.tdm.CustomTM;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DailyReportFormController {

    public AnchorPane apnMain;
    public JFXTextField txtSearchYear;

    public TableView<CustomTM> tblDailyView;
    public JFXComboBox<String> cmbSearchMonth;
    public Label lblTotal;

    int intMonth;
    DailyIncomeReportBO dailyIncomeReportBO = (DailyIncomeReportBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DAILY_INCOME);


    public void initialize() {
        cmbSearchMonth.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        txtSearchYear.setText(String.valueOf(LocalDate.now().getYear()));

//        intMonth = getIntMonth();

        tblDailyView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        tblDailyView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblDailyView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblDailyView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblDailyView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        tblDailyView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("discount"));
        tblDailyView.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("total"));


        cmbSearchMonth.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {

                intMonth = getIntMonth();
                try {
                    initTable();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initTable() throws SQLException, ClassNotFoundException {
        ArrayList<CustomDTO> dailyDetails = dailyIncomeReportBO.getDailyIncomeReportDetails(Integer.parseInt(txtSearchYear.getText()), intMonth);

        double total = 0;
        for (CustomDTO dailyDetail : dailyDetails) {

            tblDailyView.getItems().add(new CustomTM(
                    dailyDetail.getOrderDate(),
                    dailyDetail.getItemCode(),
                    dailyDetail.getDescription(),
                    dailyDetail.getUnitPrice(),
                    dailyDetail.getOrderQty(),
                    dailyDetail.getDiscount(),
                    dailyDetail.getTotal()
            ));
            total += dailyDetail.getTotal();
        }
        lblTotal.setText(String.valueOf(total));

    }

    private int getIntMonth() {
        tblDailyView.getItems().clear();
        return dailyIncomeReportBO.getMonth(cmbSearchMonth.getValue());

    }

    public void backMouseClick(MouseEvent mouseEvent) throws IOException {
        new SlideInDown(apnMain).play();
        apnMain.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/MainReportForm.fxml"));
        apnMain.getChildren().add(parent);
    }

    public void searchYearKeyReleased(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            tblDailyView.getItems().clear();
            initTable();
        }

    }
}
