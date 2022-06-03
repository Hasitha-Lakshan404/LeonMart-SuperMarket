/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :6/2/2022
 * Time :12:12 PM
 */

package lk.LeonMart.superMarket.controller;


import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.LeonMart.superMarket.bo.custom.OrderBO;
import lk.LeonMart.superMarket.bo.custom.OrderDetailBO;
import lk.LeonMart.superMarket.bo.custom.impl.OrderBOImpl;
import lk.LeonMart.superMarket.bo.custom.impl.OrderDetailBOImpl;
import lk.LeonMart.superMarket.dto.OrderDTO;
import lk.LeonMart.superMarket.dto.OrderDetailDTO;
import lk.LeonMart.superMarket.view.tdm.OrderDetailsTM;
import lk.LeonMart.superMarket.view.tdm.OrderTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class AllOrdersFormController {
    public TableView<OrderTM> tblOrder;
    public TableView<OrderDetailsTM> tblOrderDetail;
    public Label lblTotal;

    public JFXTextField txtOrderId;
    public JFXTextField txtItemCode;
    public JFXTextField txtQty;
    public JFXTextField txtDiscount;
    public JFXTextField txtTotal;
    public JFXTextField txtUnitPrice;

    public TextField txtSearchOrder;
    public TextField txtSearchOrderDetails;

    OrderBO orderBO = new OrderBOImpl();
    OrderDetailBO orderDetailBO = new OrderDetailBOImpl();

    public void initialize() throws SQLException, ClassNotFoundException {

        tblOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        tblOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        tblOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerId"));


        tblOrderDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblOrderDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblOrderDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrderDetail.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        tblOrderDetail.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("discount"));
        tblOrderDetail.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("total"));


        //add Orders to The Table
        ArrayList<OrderDTO> allOrders = orderBO.getAllOrders();

        for (OrderDTO allOrder : allOrders) {
            tblOrder.getItems().add(new OrderTM(allOrder.getOrderId(), allOrder.getOrderDate(), allOrder.getCustomerId()));
        }
        tblOrder.refresh();


        tblOrder.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {

                searchOrderDetails(newValue.getOrderId());
            }

        });


    }

    public void menuDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        OrderTM selectedItem = tblOrder.getSelectionModel().getSelectedItem();

        boolean b = orderBO.deleteOrders(selectedItem.getOrderId());

        if (b) {
            new Alert(Alert.AlertType.INFORMATION, "Deleted SussesFull").show();
            tblOrderDetail.getItems().clear();
            tblOrder.getItems().clear();
            initialize();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Something Went Wrong..").show();
        }
    }


    public void searchOrderOnKeyReleased(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            String value = "%" + txtSearchOrder.getText() + "%";

            ArrayList<OrderDTO> orderDto = orderBO.getAllSearchOrder(value);


            ObservableList<OrderTM> orderTMS = FXCollections.observableArrayList();


            for (OrderDTO od : orderDto) {
                orderTMS.add(new OrderTM(
                        od.getOrderId(),
                        od.getOrderDate(),
                        od.getCustomerId()
                        ));

            }

            tblOrder.getItems().clear();
            tblOrder.getItems().addAll(orderTMS);
            tblOrder.refresh();
        }

    }

    public void searchOrderDetailOnKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            tblOrderDetail.getItems().clear();
            searchOrderDetails(txtSearchOrderDetails.getText());
        }
    }

    private void searchOrderDetails(String newValue) {
        //Search by ID
        String value = "%" + newValue + "%";

        ArrayList<OrderDetailDTO> oDetailDto = null;

        try {

            oDetailDto = orderDetailBO.searchOrderDetails(value);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ObservableList<OrderDetailsTM> orderDetailsTMS = FXCollections.observableArrayList();

        double allTotal = 0;

        for (OrderDetailDTO od : oDetailDto) {
            orderDetailsTMS.add(new OrderDetailsTM(od.getOrderId(),
                    od.getItemCode(),
                    od.getQty(),
                    od.getUnitPrice(),
                    od.getDiscount(),
                    od.getTotal()));

            allTotal += od.getTotal();
        }

        tblOrderDetail.getItems().clear();
        tblOrderDetail.getItems().addAll(orderDetailsTMS);
        tblOrderDetail.refresh();
        lblTotal.setText(String.valueOf(allTotal));


    }

}


