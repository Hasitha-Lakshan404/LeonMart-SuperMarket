/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :6/3/2022
 * Time :7:22 PM
 */

package lk.LeonMart.superMarket.controller;


import animatefx.animation.SlideInDown;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.LeonMart.superMarket.bo.BOFactory;
import lk.LeonMart.superMarket.bo.custom.AnnuallyIncomeReportBO;
import lk.LeonMart.superMarket.bo.custom.OrderBO;
import lk.LeonMart.superMarket.bo.custom.impl.AnnuallyIncomeReportBOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class AnnuallyReportFormController {
    public AnchorPane apnMain;

    public JFXTextField txtSearchYearOne;
    public JFXTextField txtSearchYearTwo;
    public LineChart tblAnnually;
    public Pane paneVisible;

    AnnuallyIncomeReportBO annuallyIncomeReportBO = (AnnuallyIncomeReportBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ANNUALLY_INCOME);


    public void initialize() throws SQLException, ClassNotFoundException {

        paneVisible.setVisible(false);
        tblAnnually.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");


        txtSearchYearTwo.setText(String.valueOf(getNowYear()));
        txtSearchYearOne.setText(String.valueOf(getNowYear() - 4));

        setAnnually();

    }

    public void backMouseClick(MouseEvent mouseEvent) throws IOException {
        new SlideInDown(apnMain).play();
        apnMain.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/MainReportForm.fxml"));
        apnMain.getChildren().add(parent);
    }


    public void setAnnually() throws SQLException, ClassNotFoundException {

        long[][] ar = annuallyIncomeReportBO.getAnnuallyData(Integer.parseInt(txtSearchYearOne.getText()), Integer.parseInt(txtSearchYearTwo.getText()));

        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Annually");
        //populating the series with data

        for (int i = 0; i < ar.length; i++) {
            series.getData().add(new XYChart.Data(String.valueOf(ar[i][0]), ar[i][1]));
        }

        tblAnnually.getData().add(series);

    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        paneVisible.setVisible(true);
        tblAnnually.getData().clear();
        setAnnually();
    }

    private int getNowYear() {
        LocalDate date = LocalDate.now();
        return date.getYear();

    }

    public void pneMouseClickOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        tblAnnually.getData().clear();
        setAnnually();
        paneVisible.setVisible(false);
    }
}
