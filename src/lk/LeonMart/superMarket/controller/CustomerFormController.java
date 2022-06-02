package lk.LeonMart.superMarket.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    public TextField txtSearchCustomer;


    CustomerBO customerBO = new CustomerBOImpl();

    public void initialize() throws SQLException, ClassNotFoundException {

        generateId();
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Customer Updated Successfully ..!");
            alert.show();

            btnAdd.setText("Save Customer");


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

    public void CustomerClearOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
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
        txtCusId.setEditable(false);

        btnAdd.setText("Update Now");

    }

    public void menuDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
        customerBO.deleteCustomer(selectedItem.getCusId());
        tblCustomer.getItems().removeAll(selectedItem);
        generateId();
    }

    private void clearTexts() throws SQLException, ClassNotFoundException {
        txtCusId.clear();
        txtCusAddress.clear();
        txtCusName.clear();
        txtCusAddress.clear();
        txtCusCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();
        cmbCusTitle.setValue(null);

        generateId();
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

    private void generateId() throws SQLException, ClassNotFoundException {
        txtCusId.setText(customerBO.generateNewCustomerID());
    }

    public void searchDetails(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {

        String search = "%" + txtSearchCustomer.getText() + "%";

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            ArrayList<CustomerDTO> customerDTOS = customerBO.searchCustomers(search);
            ObservableList<CustomerTM> oBCustomerTMS = FXCollections.observableArrayList();

            for (CustomerDTO cusDto : customerDTOS) {
                oBCustomerTMS.add(new CustomerTM(cusDto.getCusId(),
                        cusDto.getCusTitle(),
                        cusDto.getCusName(),
                        cusDto.getCusAddress(),
                        cusDto.getCity(),
                        cusDto.getProvince(),
                        cusDto.getPostalCode()));
            }
            tblCustomer.getItems().clear();
            tblCustomer.getItems().addAll(oBCustomerTMS);
            tblCustomer.refresh();
        }

    }
}
