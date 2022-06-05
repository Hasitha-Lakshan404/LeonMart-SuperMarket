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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.LeonMart.superMarket.bo.BOFactory;
import lk.LeonMart.superMarket.bo.custom.MonthlyIncomeReportBO;
import lk.LeonMart.superMarket.bo.custom.PlaceOrderBO;
import lk.LeonMart.superMarket.bo.custom.impl.PlaceOrderBOImpl;
import lk.LeonMart.superMarket.dto.CustomerDTO;
import lk.LeonMart.superMarket.dto.ItemDTO;
import lk.LeonMart.superMarket.dto.OrderDTO;
import lk.LeonMart.superMarket.dto.OrderDetailDTO;
import lk.LeonMart.superMarket.util.ValidationUtil;
import lk.LeonMart.superMarket.view.tdm.OrderDetailsTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class OrderFormController {

    public TableView<OrderDetailsTM> tblOrderCart;
    public JFXButton btnAddToCart;
    public JFXTextField txtItemDescription;
    public JFXComboBox<String> cmbItemCode;
    public JFXComboBox<String> cmbCusId;
    public JFXTextField txtCusName;
    public JFXTextField txtAddress;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtDiscount;
    public Label lblTotal;
    public JFXTextField txtQty;
    public Label lblOrderId;
    public Label lblDate;


    PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACE_ORDER);


    LinkedHashMap<JFXTextField, Pattern> ord = new LinkedHashMap<>();
    Pattern ItemDiscountPattern = Pattern.compile("^[0-9.]{1,5}$");
    Pattern ItemQtyPattern = Pattern.compile("^[0-9]{1,10}$");

    ObservableList<OrderDetailsTM> obOrderDetailTmList = FXCollections.observableArrayList();
    private String orderId;

    public void initialize() throws SQLException, ClassNotFoundException {

        storeValidations();

        tblOrderCart.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblOrderCart.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblOrderCart.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrderCart.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        tblOrderCart.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("discount"));
        tblOrderCart.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("total"));


        orderId = generateNewOrderId();
        lblOrderId.setText(orderId);
        lblDate.setText(LocalDate.now().toString());
        txtCusName.setFocusTraversable(false);
        txtCusName.setEditable(false);
        txtAddress.setFocusTraversable(false);
        txtAddress.setEditable(false);
        txtItemDescription.setFocusTraversable(false);
        txtItemDescription.setEditable(false);
        txtUnitPrice.setFocusTraversable(false);
        txtUnitPrice.setEditable(false);
        txtQtyOnHand.setFocusTraversable(false);
        txtQtyOnHand.setEditable(false);


        getItemCode();
        getCustomerId();


        cmbCusId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                try {
                    CustomerDTO customerDTO = placeOrderBO.searchCustomer(newValue + "");
                    txtAddress.setText(customerDTO.getCusAddress());
                    txtCusName.setText(customerDTO.getCusName());

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                try {
                    ItemDTO itemDTO = placeOrderBO.searchItem(newValue + "");
                    txtItemDescription.setText(itemDTO.getDescription());
                    txtQtyOnHand.setText(String.valueOf(itemDTO.getQtyOnHand()));
                    txtUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
                    txtDiscount.setText(String.valueOf(itemDTO.getDiscount()));

                    if (obOrderDetailTmList != null) {

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    private void storeValidations() {
        btnAddToCart.setDisable(true);
        ord.put(txtDiscount, ItemDiscountPattern);
        ord.put(txtQty, ItemQtyPattern);
    }

    private void getCustomerId() {
        try {
            ArrayList<CustomerDTO> all = placeOrderBO.getAllCustomers();
            for (CustomerDTO customerDTO : all) {
                cmbCusId.getItems().add(customerDTO.getCusId());
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load customer ids").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void getItemCode() {
        try {
            /*Get all items*/
            ArrayList<ItemDTO> all = placeOrderBO.getAllItems();
            for (ItemDTO dto : all) {
                cmbItemCode.getItems().add(dto.getItemCode());
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return placeOrderBO.generateNewOrderID();
    }


    public void menuDeleteOnAction(ActionEvent actionEvent) {
        OrderDetailsTM selectedText = tblOrderCart.getSelectionModel().getSelectedItem();

        tblOrderCart.getItems().removeAll(selectedText);
    }

    public void CustomerAddToCartOnAction(ActionEvent actionEvent) {

        if (!txtQty.getText().matches("\\d+") || Integer.parseInt(txtQty.getText()) <= 0 ||
                Integer.parseInt(txtQty.getText()) > Integer.parseInt(txtQtyOnHand.getText())) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty").show();
            txtQty.requestFocus();
            txtQty.selectAll();
            return;

        }

        //=====================================================================================
        String itemCode = cmbItemCode.getSelectionModel().getSelectedItem();
        double disc = (Double.parseDouble(txtDiscount.getText())/100);

        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double tempTotal=(unitPrice*qty)*disc;
        double total = (unitPrice * qty)-tempTotal;


        if (isExist(itemCode)) {
            for (OrderDetailsTM orderDetailsTM : obOrderDetailTmList) {
                if (orderDetailsTM.getItemCode().equals(cmbItemCode.getValue())) {
                    orderDetailsTM.setQty(orderDetailsTM.getQty() + qty);
                    orderDetailsTM.setTotal(orderDetailsTM.getTotal() + total);
                    orderDetailsTM.setDiscount(orderDetailsTM.getDiscount()+tempTotal);

                }
            }
            tblOrderCart.refresh();
            clearText();
        } else {

            //Add data to the Observable List

            OrderDetailsTM orderDetail = new OrderDetailsTM(
                    cmbItemCode.getValue(),
                    txtItemDescription.getText(),
                    qty,
                    unitPrice,
                    tempTotal,
                    total);
            //Added OrderDetail To the Observable list
            obOrderDetailTmList.add(orderDetail);

            //Added Observable list to the table
            tblOrderCart.setItems(obOrderDetailTmList);

        }
        lblTotal.setText(String.valueOf(getAllTotalCost()));
        clearText();

    }

    private double getAllTotalCost() {
        double tCost = 0;
        for (OrderDetailsTM orderDetailsTM : obOrderDetailTmList) {
            tCost += orderDetailsTM.getTotal();
        }
        return tCost;

    }

    private boolean isExist(String code) {
        for (OrderDetailsTM orderDetailsTM : obOrderDetailTmList) {
            if (orderDetailsTM.getItemCode().equals(code)) {
                return true;
            }
        }
        return false;

    }

    public void CustomerClearOnAction(ActionEvent actionEvent) {

    }

    public void placeOrderOnAction(ActionEvent mouseEvent) {

        List<OrderDetailDTO> orderList =new ArrayList<>();

        for (OrderDetailsTM orderDetailsTM : obOrderDetailTmList) {
            orderList.add(new OrderDetailDTO(lblOrderId.getText(),orderDetailsTM.getItemCode(),
                    orderDetailsTM.getQty(),orderDetailsTM.getUnitPrice(),orderDetailsTM.getDiscount()));
        }


        boolean b = saveOrder(orderId, LocalDate.now(), cmbCusId.getValue(), orderList);
        if(b){
           new Alert(Alert.AlertType.INFORMATION,"Order Placed Successfully").show();
        }else{
            new Alert(Alert.AlertType.WARNING,"Order Placed UN-Successful").show();

        }

    }


    private boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails){
        try {
            cmbCusId.setValue(null);
            txtCusName.clear();
            txtAddress.clear();
            clearText();
            return placeOrderBO.purchaseOrder(new OrderDTO(orderId, orderDate, customerId, orderDetails));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void clearText(){

        cmbItemCode.setValue(null);

        txtItemDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtDiscount.clear();
        txtQty.clear();
    }

    public void textFieldValidationOnAction(KeyEvent keyEvent) {
        Object response = ValidationUtil.validateJFXTextField(ord, btnAddToCart);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }

        }
    }
}
