package lk.LeonMart.superMarket.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.LeonMart.superMarket.bo.BOFactory;
import lk.LeonMart.superMarket.bo.custom.DashBoardBO;
import lk.LeonMart.superMarket.dto.CustomDTO;
import lk.LeonMart.superMarket.dto.ItemDTO;
import lk.LeonMart.superMarket.view.tdm.CustomTM;
import lk.LeonMart.superMarket.view.tdm.ItemTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class DashboardFormController {


    public AnchorPane apnMainPane;


    public Label lblTotalOrders;
    public Label lblItemTypes;
    public Label lblTotalCustomer;
    public TableView<ItemTM> tblItem;
    public TextField txtSearchItem;
    public TableView<CustomTM> tblCustomer;
    public JFXComboBox<String> cmbSelectCustomer;

    public Label lblTotalGet;

    DashBoardBO dashBoardBO = (DashBoardBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DASHBOARD);


    public void initialize() throws SQLException, ClassNotFoundException {

        tblItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));


        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cusId"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));


//        cmbSelectCustomer.getItems().addAll("C00-001", "C00-002");
        setCmbData();

        setItemTableData();

        cmbSelectCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (cmbSelectCustomer.getValue() != null) {
                try {
                    setCustomerTable();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        setLabelValue();

    }


    public void setItemTableData() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItemName = dashBoardBO.getAllItemName();

        for (ItemDTO itemDTO : allItemName) {
            tblItem.getItems().add(new ItemTM(
                    itemDTO.getItemCode(),
                    itemDTO.getDescription(),
                    itemDTO.getUnitPrice()
            ));
        }

    }


    public void setCustomerTable() throws SQLException, ClassNotFoundException {
        ArrayList<CustomDTO> customerBuyingItems = dashBoardBO.getCustomerBuyingItems(cmbSelectCustomer.getValue());

        tblCustomer.getItems().clear();
        int total = 0;
        for (CustomDTO customerBuyingItem : customerBuyingItems) {
            total += 1;
            tblCustomer.getItems().add(new CustomTM(
                    customerBuyingItem.getCustomerId(),
                    customerBuyingItem.getItemCode(),
                    customerBuyingItem.getDescription()
            ));
        }
        lblTotalGet.setText(String.valueOf(total));

    }


    private void setCmbData() throws SQLException, ClassNotFoundException {
        for (String cmbCustomerId : dashBoardBO.getCmbCustomerIds()) {
            cmbSelectCustomer.getItems().add(cmbCustomerId);
        }
    }


    public void searchItemDetails(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String search = "%" + txtSearchItem.getText() + "%";

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            ArrayList<ItemDTO> itemDTOS = dashBoardBO.getSearchICodeDescription(search);
            ObservableList<ItemTM> itTm = FXCollections.observableArrayList();

            for (ItemDTO itDto : itemDTOS) {
                itTm.add(new ItemTM(
                        itDto.getItemCode(),
                        itDto.getDescription(),
                        itDto.getUnitPrice()
                ));
            }
            tblItem.getItems().clear();
            tblItem.getItems().addAll(itTm);
            tblItem.refresh();
        }
    }


    public void setLabelValue() throws SQLException, ClassNotFoundException {
        lblItemTypes.setText(String.valueOf(dashBoardBO.getItemTypes()));
        lblTotalCustomer.setText(String.valueOf(dashBoardBO.getTotalCustomer()));
        lblTotalOrders.setText(String.valueOf(dashBoardBO.getOrderCount()));
    }


}
