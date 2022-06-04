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
import lk.LeonMart.superMarket.dao.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MonthlyReportFormController {

    public AnchorPane apnMain;
    public LineChart crtMonthly;
    public JFXTextField txtSearchMonth;


    public void initialize() throws SQLException, ClassNotFoundException {
        crtMonthly.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        txtSearchMonth.setText(String.valueOf(LocalDate.now().getYear()));
        setMonthlyDetails();
    }

    public void setMonthlyDetails() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT `order`.OrderDate, orderdetail.ItemCode,orderdetail.UnitPrice,orderdetail.OrderQty,orderdetail.Discount FROM `order` INNER JOIN orderdetail ON `order`.OrderID=leonmart.orderdetail.OrderId WHERE year(`order`.OrderDate)=?", Integer.parseInt(txtSearchMonth.getText()));

        double jan = 0;
        double feb = 0;
        double march = 0;
        double april = 0;
        double may = 0;
        double jun = 0;
        double july = 0;
        double aug = 0;
        double sep = 0;
        double oct = 0;
        double nov = 0;
        double dec = 0;


        while (result.next()) {
            LocalDate d = result.getDate(1).toLocalDate();

            switch (d.getMonthValue()) {
                case 1:
                    jan += (result.getDouble(3) * result.getInt(4)) - result.getDouble(5);
                    break;
                case 2:
                    feb += (result.getDouble(3) * result.getInt(4)) - result.getDouble(5);
                    break;
                case 3:
                    march += (result.getDouble(3) * result.getInt(4)) - result.getDouble(5);
                    break;
                case 4:
                    april += (result.getDouble(3) * result.getInt(4)) - result.getDouble(5);
                    break;
                case 5:
                    may += (result.getDouble(3) * result.getInt(4)) - result.getDouble(5);
                    break;
                case 6:
                    jun += (result.getDouble(3) * result.getInt(4)) - result.getDouble(5);
                    break;
                case 7:
                    july += (result.getDouble(3) * result.getInt(4)) - result.getDouble(5);
                    break;
                case 8:
                    aug += (result.getDouble(3) * result.getInt(4)) - result.getDouble(5);
                    break;
                case 9:
                    sep += (result.getDouble(3) * result.getInt(4)) - result.getDouble(5);
                    break;
                case 10:
                    oct += (result.getDouble(3) * result.getInt(4)) - result.getDouble(5);
                    break;
                case 11:
                    nov += (result.getDouble(3) * result.getInt(4)) - result.getDouble(5);
                    break;
                case 12:
                    dec += (result.getDouble(3) * result.getInt(4)) - result.getDouble(5);
                    break;
            }

        }


//       tblMonthly.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Monthly wise Income");
        //populating the series with data
        series.getData().add(new XYChart.Data("January", jan));
        series.getData().add(new XYChart.Data("February", feb));
        series.getData().add(new XYChart.Data("March", march));
        series.getData().add(new XYChart.Data("April", april));
        series.getData().add(new XYChart.Data("May", may));
        series.getData().add(new XYChart.Data("June", jun));
        series.getData().add(new XYChart.Data("July", july));
        series.getData().add(new XYChart.Data("August", aug));
        series.getData().add(new XYChart.Data("September", sep));
        series.getData().add(new XYChart.Data("October", oct));
        series.getData().add(new XYChart.Data("November", nov));
        series.getData().add(new XYChart.Data("December", dec));

        crtMonthly.getData().add(series);

    }


    public void backMouseClick(MouseEvent mouseEvent) throws IOException {
        new SlideInDown(apnMain).play();
        apnMain.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/MainReportForm.fxml"));
        apnMain.getChildren().add(parent);
    }

    public void searchMonthKeyReleased(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {

        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            crtMonthly.getData().clear();
            setMonthlyDetails();
        }
    }
}
