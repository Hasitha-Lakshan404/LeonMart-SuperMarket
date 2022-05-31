/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :5/30/2022
 * Time :7:39 PM
 */

package lk.LeonMart.superMarket.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class OrderFormController {

    public TableView tblOrderCart;
    public JFXButton btnAddToCart;
    public JFXTextField txtItemDescription;
    public JFXComboBox cmbItemCode;
    public JFXComboBox cmbCusId;
    public JFXTextField txtCusName;
    public JFXTextField txtAddress;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtDiscount;
    public Label lblTotal;
    public JFXTextField txtQty;
    public Label lblOrderId;
    public Label lblDate;


    public void initialize(){

    }


    public void menuDeleteOnAction(ActionEvent actionEvent) {
    }

    public void CustomerAddToCartOnAction(ActionEvent actionEvent) {
    }

    public void CustomerClearOnAction(ActionEvent actionEvent) {
    }

    public void placeOrderOnAction(MouseEvent mouseEvent) {
    }
}
