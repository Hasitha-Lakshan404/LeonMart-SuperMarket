package lk.LeonMart.superMarket.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import lk.LeonMart.superMarket.bo.custom.ItemBO;
import lk.LeonMart.superMarket.bo.custom.impl.ItemBOImpl;
import lk.LeonMart.superMarket.dto.ItemDTO;
import lk.LeonMart.superMarket.view.tdm.CustomerTM;

import java.sql.SQLException;

public class ItemFormController {


    public TableView<CustomerTM> tblItem;
    public JFXTextField txtItemId;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXTextField txtDescription;
    public JFXButton btnAdd;

    ItemBO itemBO = new ItemBOImpl();

    public void menuEditOnAction(ActionEvent actionEvent) {

    }

    public void menuDeleteOnAction(ActionEvent actionEvent) {
    }

    public void ItemAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(btnAdd.getText().equals("Update Now")){

        }else {
            itemBO.saveItem(new ItemDTO(txtItemId.getText(),
                    txtDescription.getText(),
                    txtPackSize.getText(),
                    Double.parseDouble(txtUnitPrice.getText()),
                    Integer.parseInt(txtQty.getText()),
                    Double.parseDouble("0"))
            );
        }
    }

    public void ItemClearOnAction(ActionEvent actionEvent) {
    }
}
