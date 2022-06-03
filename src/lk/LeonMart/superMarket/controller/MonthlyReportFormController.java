/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :6/3/2022
 * Time :7:22 PM
 */

package lk.LeonMart.superMarket.controller;


import animatefx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MonthlyReportFormController {

    public AnchorPane apnMain;

    public void backMouseClick(MouseEvent mouseEvent) throws IOException {
        new SlideInDown(apnMain).play();
        apnMain.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/MainReportForm.fxml"));
        apnMain.getChildren().add(parent);
    }
}
