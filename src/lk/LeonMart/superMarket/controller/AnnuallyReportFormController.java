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
import lk.LeonMart.superMarket.dao.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AnnuallyReportFormController {
    public AnchorPane apnMain;

    public JFXTextField txtSearchYearOne;
    public JFXTextField txtSearchYearTwo;
    public LineChart tblAnnually;
    public Pane paneVisible;


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
        ResultSet result = CrudUtil.execute("SELECT `order`.OrderDate, orderdetail.ItemCode,orderdetail.UnitPrice,orderdetail.OrderQty,orderdetail.Discount FROM `order` INNER JOIN orderdetail ON `order`.OrderID=leonmart.orderdetail.OrderId ");

        //use +1 to get earnings this entered year to other year.
        int count = Integer.parseInt(txtSearchYearTwo.getText()) - Integer.parseInt(txtSearchYearOne.getText()) + 1;

        long[][] ar = new long[count][2];  //5


        int tempYear = Integer.parseInt(txtSearchYearTwo.getText());

        for (int i = count - 1; i >= 0; i--) {
            ar[i][0] = tempYear;
            tempYear--;
        }

        for (long[] x : ar
        ) {

        }

        while (result.next()) {
            //getLocalDate
            LocalDate d = result.getDate(1).toLocalDate();

            for (int i = 0; i < count; i++) {

                if (d.getYear() == ar[i][0]) {

//                    ar[i][1]+=result.getDouble(2);
                    ar[i][1] += (result.getDouble(3) * result.getInt(4)) - result.getDouble(5);
                }
            }

        }

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
