/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :6/3/2022
 * Time :7:22 PM
 */

package lk.LeonMart.superMarket.controller;


import animatefx.animation.SlideInDown;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.LeonMart.superMarket.bo.BOFactory;
import lk.LeonMart.superMarket.bo.custom.MonthlyIncomeReportBO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class MonthlyReportFormController {

    public AnchorPane apnMain;
    public LineChart crtMonthly;
    public JFXTextField txtSearchMonth;

    MonthlyIncomeReportBO monthlyIncomeReportBO = (MonthlyIncomeReportBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MONTHLY_INCOME);


    public void initialize() throws SQLException, ClassNotFoundException {

        crtMonthly.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        txtSearchMonth.setText(String.valueOf(LocalDate.now().getYear()));
        setMonthlyDetails();
    }


    public void setMonthlyDetails() throws SQLException, ClassNotFoundException {

        double[] monthTotal = monthlyIncomeReportBO.getMonthTotal(Integer.parseInt(txtSearchMonth.getText()));

        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Monthly wise Income");
        //populating the series with data
        series.getData().add(new XYChart.Data("January", monthTotal[0]));
        series.getData().add(new XYChart.Data("February", monthTotal[1]));
        series.getData().add(new XYChart.Data("March", monthTotal[2]));
        series.getData().add(new XYChart.Data("April", monthTotal[3]));
        series.getData().add(new XYChart.Data("May", monthTotal[4]));
        series.getData().add(new XYChart.Data("June", monthTotal[5]));
        series.getData().add(new XYChart.Data("July", monthTotal[6]));
        series.getData().add(new XYChart.Data("August", monthTotal[7]));
        series.getData().add(new XYChart.Data("September", monthTotal[8]));
        series.getData().add(new XYChart.Data("October", monthTotal[9]));
        series.getData().add(new XYChart.Data("November", monthTotal[10]));
        series.getData().add(new XYChart.Data("December", monthTotal[11]));

        crtMonthly.getData().add(series);

    }

    public void backMouseClick(MouseEvent mouseEvent) throws IOException {
        new SlideInDown(apnMain).play();
        apnMain.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/MainReportForm.fxml"));
        apnMain.getChildren().add(parent);
    }

    public void searchMonthKeyReleased(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            crtMonthly.getData().clear();
            setMonthlyDetails();
        }
    }
}
