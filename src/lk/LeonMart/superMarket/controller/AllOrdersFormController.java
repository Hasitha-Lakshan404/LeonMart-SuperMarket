/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :6/2/2022
 * Time :12:12 PM
 */

package lk.LeonMart.superMarket.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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


                //Search by ID
                String value = "%" + newValue.getOrderId() + "%";

                ArrayList<OrderDetailDTO> oDetailDto = null;

                try {

                    oDetailDto = orderDetailBO.searchOrderDetails(value);


                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                ObservableList<OrderDetailsTM> orderDetailsTMS = FXCollections.observableArrayList();


                for (OrderDetailDTO od : oDetailDto) {

                    orderDetailsTMS.add(new OrderDetailsTM(od.getOrderId(),
                            od.getItemCode(),
                            od.getQty(),
                            od.getUnitPrice(),
                            od.getDiscount(),
                            od.getTotal()));
                }
                tblOrderDetail.getItems().clear();
                tblOrderDetail.getItems().addAll(orderDetailsTMS);
                tblOrderDetail.refresh();


            }

        });


    }

    public void menuDeleteOnAction(ActionEvent actionEvent) {

    }


    public void searchOrderOnKeyReleased(KeyEvent keyEvent) {

    }

    public void searchOrderDetailOnKeyReleased(KeyEvent keyEvent) {

    }

}
