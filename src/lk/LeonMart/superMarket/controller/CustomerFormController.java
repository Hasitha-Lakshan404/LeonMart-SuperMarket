package lk.LeonMart.superMarket.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.LeonMart.superMarket.bo.custom.CustomerBO;
import lk.LeonMart.superMarket.bo.custom.impl.CustomerBOImpl;
import lk.LeonMart.superMarket.dto.CustomerDTO;
import lk.LeonMart.superMarket.view.tdm.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerFormController {

    public TableView<CustomerTM> tblCustomer;
    public JFXTextField txtCusId;
    public JFXTextField txtCusName;
    public JFXTextField txtCusAddress;
    public JFXTextField txtCusCity;
    public JFXTextField txtPostalCode;
    public JFXTextField txtProvince;
    public JFXComboBox<String> cmbCusTitle;


    CustomerBO customerBO = new CustomerBOImpl();

    public void initialize() throws SQLException, ClassNotFoundException {

        cmbCusTitle.getItems().addAll("Mr","Mis","Ms");

        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cusId"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("cusTitle"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("cusName"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("cusAddress"));
        tblCustomer.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("city"));
        tblCustomer.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("province"));
        tblCustomer.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("postalCode"));


        loadAllCustomers();
    }

    private void loadAllCustomers() throws SQLException, ClassNotFoundException {
        tblCustomer.getItems().clear();

        //from persistence layer
        ArrayList<CustomerDTO> allCustomers =customerBO.getAllCustomers();

        for (CustomerDTO cus : allCustomers) {
            tblCustomer.getItems().add(new CustomerTM(cus.getCusId(),cus.getCusTitle(),cus.getCusName(),
                    cus.getCusAddress(),cus.getCity(),cus.getProvince(),cus.getPostalCode()));
        }



    }

    public void CustomerAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        customerBO.saveCustomer(new CustomerDTO(txtCusId.getText(),
                cmbCusTitle.getValue(),
                txtCusName.getText(),
                txtCusAddress.getText(),
                txtCusCity.getText(),
                txtProvince.getText(),
                txtPostalCode.getText()));

        tblCustomer.getItems().add(new CustomerTM(txtCusId.getText(),
                cmbCusTitle.getValue(),
                txtCusName.getText(),
                txtCusAddress.getText(),
                txtCusCity.getText(),
                txtProvince.getText(),
                txtPostalCode.getText()));
        tblCustomer.refresh();
        clearTexts();
    }

    public void CustomerClearOnAction(ActionEvent actionEvent) {
    }

    public void menuEditOnAction(ActionEvent actionEvent) {
    }

    public void menuDeleteOnAction(ActionEvent actionEvent) {
    }

    private void clearTexts(){
        txtCusId.clear();
        txtCusAddress.clear();
        txtCusName.clear();
        txtCusAddress.clear();
        txtCusCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();
        cmbCusTitle.getItems().clear();
    }
}
