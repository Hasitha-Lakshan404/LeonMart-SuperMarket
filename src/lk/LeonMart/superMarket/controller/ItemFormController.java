package lk.LeonMart.superMarket.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.LeonMart.superMarket.bo.BOFactory;
import lk.LeonMart.superMarket.bo.custom.DashBoardBO;
import lk.LeonMart.superMarket.bo.custom.ItemBO;
import lk.LeonMart.superMarket.bo.custom.impl.ItemBOImpl;
import lk.LeonMart.superMarket.dto.CustomerDTO;
import lk.LeonMart.superMarket.dto.ItemDTO;
import lk.LeonMart.superMarket.util.ValidationUtil;
import lk.LeonMart.superMarket.view.tdm.CustomerTM;
import lk.LeonMart.superMarket.view.tdm.ItemTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ItemFormController {


    public TableView<ItemTM> tblItem;
    public JFXTextField txtItemId;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXTextField txtDescription;
    public JFXButton btnAdd;

    public TableColumn colDiscount;
    public JFXTextField txtDiscount;
    public TextField txtSearchItem;
    double updateDiscount;

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    ItemTM selectedItem;

    LinkedHashMap<JFXTextField, Pattern> itm = new LinkedHashMap<>();
    Pattern ItemDescriptionPattern = Pattern.compile("^[A-z ]{4,50}$");
    Pattern ItemPackSizePattern = Pattern.compile("^[A-z0-9 ,/]{1,20}$");
    Pattern ItemPricePattern = Pattern.compile("^\\d+(,\\d{1,2})?$");
    Pattern ItemQtyPattern = Pattern.compile("^[0-9]{1,}$");
    Pattern ItemDiscountPattern = Pattern.compile("^[0-9]{1,2}$");

    public void initialize() throws SQLException, ClassNotFoundException {

        storeValidations();

        tblItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("packSize"));
        tblItem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItem.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        tblItem.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("discount"));

        loadAllItems();
        generateNewId();


    }

    private void storeValidations() {
        btnAdd.setDisable(true);
        itm.put(txtPackSize, ItemPackSizePattern);
        itm.put(txtDescription, ItemDescriptionPattern);
        itm.put(txtUnitPrice, ItemPricePattern);
        itm.put(txtQty, ItemQtyPattern);
//        itm.put(txtDiscount,ItemDiscountPattern);
    }

    public void menuEditOnAction(ActionEvent actionEvent) {
        ItemTM selectedItem = tblItem.getSelectionModel().getSelectedItem();

        txtItemId.setText(selectedItem.getItemCode());
        txtDescription.setText(selectedItem.getDescription());
        txtPackSize.setText(selectedItem.getPackSize());
        txtUnitPrice.setText(String.valueOf(selectedItem.getUnitPrice()));
        txtQty.setText(String.valueOf(selectedItem.getQtyOnHand()));
        updateDiscount = selectedItem.getDiscount();

        txtItemId.setEditable(false);

        btnAdd.setText("Update Now");
    }

    public void menuDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemTM selectedItem = tblItem.getSelectionModel().getSelectedItem();

        itemBO.deleteItem(selectedItem.getItemCode());
        tblItem.getItems().removeAll(selectedItem);
        tblItem.refresh();
        generateNewId();

    }

    public void ItemAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (btnAdd.getText().equalsIgnoreCase("Update Now")) {

            if (itemBO.updateItem(new ItemDTO(txtItemId.getText(), txtDescription.getText(), txtPackSize.getText(),
                    Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtQty.getText()),
                    updateDiscount))) {
                new Alert(Alert.AlertType.INFORMATION, "Item Updated Successful").show();
            }

            btnAdd.setText("Add Item");
            loadAllItems();
            clearText();
        } else {

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
            if (itemBO.updateItem(new ItemDTO(item.getItemCode(), item.getDescription(), item.getPackSize(), item.getUnitPrice(),
                    item.getQtyOnHand(), item.getDiscount()))) {
                loadAllItems();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void menuEditDiscountOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        selectedItem = tblItem.getSelectionModel().getSelectedItem();
        txtDiscount.setText(String.valueOf(selectedItem.getDiscount()));
        btnDisable();
        clearText();
    }

    public void DiscountEditOnKeyReleased(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {

            if (txtDiscount.getText() != null && !txtDiscount.getText().equals("")) {
                final boolean b = itemBO.updateItem(new ItemDTO(selectedItem.getItemCode(),
                        selectedItem.getDescription(),
                        selectedItem.getPackSize(),
                        selectedItem.getUnitPrice(),
                        selectedItem.getQtyOnHand(),
                        Double.parseDouble(txtDiscount.getText())));

                if (b) {
                    new Alert(Alert.AlertType.INFORMATION, "Discount Updated Successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Discount Updated UN-Successfully").show();
                }

                txtDiscount.clear();
                loadAllItems();
                btnEnable();
                selectedItem = null;
            }
        }
    }

    private void btnDisable() {
        txtItemId.setEditable(false);
        txtDescription.setEditable(false);
        txtPackSize.setEditable(false);
        txtUnitPrice.setEditable(false);
        txtQty.setEditable(false);
    }

    private void btnEnable() {
        txtItemId.setEditable(true);
        txtDescription.setEditable(true);
        txtPackSize.setEditable(true);
        txtUnitPrice.setEditable(true);
        txtQty.setEditable(true);
    }

    public void SearchItemKeyReleased(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String search = "%" + txtSearchItem.getText() + "%";

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            ArrayList<ItemDTO> itemDTOS = itemBO.searchItems(search);
            ObservableList<ItemTM> itTm = FXCollections.observableArrayList();

            for (ItemDTO itDto : itemDTOS) {
                itTm.add(new ItemTM(
                        itDto.getItemCode(),
                        itDto.getDescription(),
                        itDto.getPackSize(),
                        itDto.getUnitPrice(),
                        itDto.getQtyOnHand(),
                        itDto.getDiscount()
                       ));
            }
            tblItem.getItems().clear();
            tblItem.getItems().addAll(itTm);
            tblItem.refresh();
        }

    }

    public void textFieldValidationOnAction(KeyEvent keyEvent) {
        Object response = ValidationUtil.validateJFXTextField(itm, btnAdd);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }



}
