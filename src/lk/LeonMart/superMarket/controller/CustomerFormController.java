package lk.LeonMart.superMarket.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
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
    public JFXButton btnAdd;


    CustomerBO customerBO = new CustomerBOImpl();

    public void initialize() throws SQLException, ClassNotFoundException {

        cmbCusTitle.getItems().addAll("Mr", "Mis", "Ms");

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
        ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers();

        for (CustomerDTO cus : allCustomers) {
            tblCustomer.getItems().add(new CustomerTM(cus.getCusId(), cus.getCusTitle(), cus.getCusName(),
                    cus.getCusAddress(), cus.getCity(), cus.getProvince(), cus.getPostalCode()));
        }


    }

    public void CustomerAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (btnAdd.getText().equalsIgnoreCase("Update Now")) {

            customerBO.updateCustomer(new CustomerDTO(txtCusId.getText(),
                    cmbCusTitle.getValue(),
                    txtCusName.getText(),
                    txtCusAddress.getText(),
                    txtCusCity.getText(),
                    txtProvince.getText(),
                    txtPostalCode.getText()));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated Successfully ..!");
            alert.show();

            btnAdd.setText("Save Customer");

            tblCustomer.getItems().setAll(new CustomerTM(txtCusId.getText(),
                    cmbCusTitle.getValue(),
                    txtCusName.getText(),
                    txtCusAddress.getText(),
                    txtCusCity.getText(),
                    txtProvince.getText(),
                    txtPostalCode.getText()));

            loadAllCustomers();

            clearTexts();


        } else {
            customerBO.saveCustomer(new CustomerDTO(txtCusId.getText(),
                    cmbCusTitle.getValue(),
                    txtCusName.getText(),
                    txtCusAddress.getText(),
                    txtCusCity.getText(),
                    txtProvince.getText(),
                    txtPostalCode.getText()));


            refreshTable();
            clearTexts();
        }
    }

    public void CustomerClearOnAction(ActionEvent actionEvent) {
        clearTexts();

    }

    public void menuEditOnAction(ActionEvent actionEvent) {
        CustomerTM selectItem = tblCustomer.getSelectionModel().getSelectedItem();

        txtCusId.setText(selectItem.getCusId());
        cmbCusTitle.setValue(selectItem.getCusTitle());
        txtCusName.setText(selectItem.getCusName());
        txtCusAddress.setText(selectItem.getCusAddress());
        txtCusCity.setText(selectItem.getCity());
        txtProvince.setText(selectItem.getProvince());
        txtPostalCode.setText(selectItem.getPostalCode());

        btnAdd.setText("Update Now");

    }

    public void menuDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerTM selectedItem=tblCustomer.getSelectionModel().getSelectedItem();
        customerBO.deleteCustomer(selectedItem.getCusId());
        tblCustomer.getItems().removeAll(selectedItem);
    }

    private void clearTexts() {
        txtCusId.clear();
        txtCusAddress.clear();
        txtCusName.clear();
        txtCusAddress.clear();
        txtCusCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();
        cmbCusTitle.setValue(null);
    }

    private void refreshTable() {
        tblCustomer.getItems().add(new CustomerTM(txtCusId.getText(),
                cmbCusTitle.getValue(),
                txtCusName.getText(),
                txtCusAddress.getText(),
                txtCusCity.getText(),
                txtProvince.getText(),
                txtPostalCode.getText()));
        tblCustomer.refresh();
    }

}
