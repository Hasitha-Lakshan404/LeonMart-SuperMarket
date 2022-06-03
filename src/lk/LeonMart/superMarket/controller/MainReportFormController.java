/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :6/2/2022
 * Time :11:29 PM
 */

package lk.LeonMart.superMarket.controller;


import animatefx.animation.SlideInUp;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.LeonMart.superMarket.bo.custom.MainReportBO;
import lk.LeonMart.superMarket.bo.custom.impl.MainReportBOImpl;
import lk.LeonMart.superMarket.dto.CustomDTO;
import lk.LeonMart.superMarket.view.tdm.CustomTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainReportFormController {

    public TableView<CustomTM> tblLeastItem;
    public TableView<CustomTM> tblMostItem;
    public AnchorPane apnMain;
    public JFXButton btnAnnually;

    MainReportBO mainReportBO=new MainReportBOImpl();

    public void initialize() throws SQLException, ClassNotFoundException {

        setDataToCol(tblMostItem);
        setDataToCol(tblLeastItem);

        setMostMovables();
        setLeastMovables();
    }

    private void setDataToCol(TableView<CustomTM> T) {
        T.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        T.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        T.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("packSize"));
        T.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        T.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("orderQty"));
    }

    private void setMostMovables() throws SQLException, ClassNotFoundException {
        tblMostItem.getItems().clear();
        ArrayList<CustomDTO> customDTOS = mainReportBO.MostMovableItem();

        for (CustomDTO customDTO : customDTOS) {
            tblMostItem.getItems().add(new CustomTM(
                    customDTO.getItemCode(),
                    customDTO.getDescription(),
                    customDTO.getPackSize(),
                    customDTO.getUnitPrice(),
                    customDTO.getOrderQty()
            ));

        }
        tblMostItem.refresh();

    }

    private void setLeastMovables() throws SQLException, ClassNotFoundException {
        tblLeastItem.getItems().clear();
        ArrayList<CustomDTO> customDTOS = mainReportBO.LeastMovableItem();

        for (CustomDTO customDTO : customDTOS) {
            tblLeastItem.getItems().add(new CustomTM(
                    customDTO.getItemCode(),
                    customDTO.getDescription(),
                    customDTO.getPackSize(),
                    customDTO.getUnitPrice(),
                    customDTO.getOrderQty()
            ));

        }
        tblLeastItem.refresh();
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
