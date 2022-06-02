package lk.LeonMart.superMarket.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import lk.LeonMart.superMarket.bo.custom.ItemBO;
import lk.LeonMart.superMarket.bo.custom.impl.ItemBOImpl;
import lk.LeonMart.superMarket.dto.CustomerDTO;
import lk.LeonMart.superMarket.dto.ItemDTO;
import lk.LeonMart.superMarket.view.tdm.CustomerTM;
import lk.LeonMart.superMarket.view.tdm.ItemTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemFormController {


    public TableView<ItemTM> tblItem;
    public JFXTextField txtItemId;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXTextField txtDescription;
    public JFXButton btnAdd;

    public TableColumn colDiscount;
    double updateDiscount;

    ItemBO itemBO = new ItemBOImpl();



    public void initialize() throws SQLException, ClassNotFoundException {

        tblItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("packSize"));
        tblItem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItem.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        tblItem.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("discount"));

        loadAllItems();
        generateNewId();


//        colDiscount.setCellFactory(TextFieldTableCell.forTableColumn());

//        Callback<TableColumn<Double, Double>, TableCell<Double, Double>> tableColumnTableCellCallback = TableCell.;
//        colDiscount.setCellFactory(tableColumnTableCellCallback);


    }

    public void menuEditOnAction(ActionEvent actionEvent) {
        ItemTM selectedItem=tblItem.getSelectionModel().getSelectedItem();

        txtItemId.setText(selectedItem.getItemCode());
        txtDescription.setText(selectedItem.getDescription());
        txtPackSize.setText(selectedItem.getPackSize());
        txtUnitPrice.setText(String.valueOf(selectedItem.getUnitPrice()));
        txtQty.setText(String.valueOf(selectedItem.getQtyOnHand()));
        updateDiscount= selectedItem.getDiscount();

        txtItemId.setEditable(false);

        btnAdd.setText("Update Now");
    }

    public void menuDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemTM selectedItem=tblItem.getSelectionModel().getSelectedItem();

        itemBO.deleteItem(selectedItem.getItemCode());
        tblItem.getItems().removeAll(selectedItem);
        tblItem.refresh();
        generateNewId();

    }

    public void ItemAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(btnAdd.getText().equalsIgnoreCase("Update Now")){

            if (itemBO.updateItem(new ItemDTO(txtItemId.getText(),txtDescription.getText(),txtPackSize.getText(),
                    Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQty.getText()),
                    updateDiscount))){
                new Alert(Alert.AlertType.INFORMATION,"Item Updated Successful").show();
            }


            btnAdd.setText("Add Item");
            loadAllItems();
            clearText();
        }else {

            itemBO.saveItem(new ItemDTO(txtItemId.getText(),
                    txtDescription.getText(),
                    txtPackSize.getText(),
                    Double.parseDouble(txtUnitPrice.getText()),
                    Integer.parseInt(txtQty.getText()),
                    Double.parseDouble("0"))
            );
            tblItem.getItems().add(new ItemTM(txtItemId.getText(),
                    txtDescription.getText(),
                    txtPackSize.getText(),
                    Double.parseDouble(txtUnitPrice.getText()),
                    Integer.parseInt(txtQty.getText()),
                    Double.parseDouble("0")));
            tblItem.refresh();

            clearText();
        }
    }

    public void ItemClearOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        clearText();

    }

    private void loadAllItems() throws SQLException, ClassNotFoundException {

        tblItem.getItems().clear();

        ArrayList<ItemDTO> allItem = itemBO.getAllItems();

        for (ItemDTO itm : allItem) {
            tblItem.getItems().add(new ItemTM(itm.getItemCode(),
                    itm.getDescription(),
                    itm.getPackSize(),
                    itm.getUnitPrice(),
                    itm.getQtyOnHand(),
                    itm.getDiscount()));
        }
    }

    private void clearText() throws SQLException, ClassNotFoundException {
        txtItemId.clear();
        txtDescription.clear();
        txtPackSize.clear();
        txtUnitPrice.clear();
        txtQty.clear();
        generateNewId();
    }
    private void generateNewId() throws SQLException, ClassNotFoundException {
        txtItemId.setText(itemBO.generateNewItemID());
    }

    public void colDiscountEditCommit(TableColumn.CellEditEvent cellEditEvent) {
        ItemTM item = tblItem.getSelectionModel().getSelectedItem();

        item.setDiscount(Double.parseDouble(cellEditEvent.getNewValue().toString()));

        //Update Quarry->
        try {
            if (itemBO.updateItem(new ItemDTO(item.getItemCode(),item.getDescription(),item.getPackSize(),item.getUnitPrice(),
                    item.getQtyOnHand(), item.getDiscount()))) {
                loadAllItems();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
