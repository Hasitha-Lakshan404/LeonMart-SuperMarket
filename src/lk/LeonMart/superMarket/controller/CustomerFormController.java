package lk.LeonMart.superMarket.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import lk.LeonMart.superMarket.dto.CustomerDTO;

import java.util.ArrayList;

public class CustomerFormController {

    public TableView tblCustomer;
    public JFXTextField txtCusId;
    public JFXTextField txtCusName;
    public JFXTextField txtCusAddress;
    public JFXTextField txtCusCity;
    public JFXTextField txtPostalCode;
    public JFXTextField txtProvince;
    public JFXComboBox<String> cmbCusTitle;

    public void initialize(){

    }

    private void loadAllCustomers(){
        tblCustomer.getItems().clear();

        //from persistence layer
        ArrayList<CustomerDTO> allCustomers =


    }

    public void CustomerAddOnAction(ActionEvent actionEvent){

    }

    public void CustomerClearOnAction(ActionEvent actionEvent) {
    }

    public void menuEditOnAction(ActionEvent actionEvent) {
    }

    public void menuDeleteOnAction(ActionEvent actionEvent) {
    }
}
