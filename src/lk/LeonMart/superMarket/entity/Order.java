/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :5/30/2022
 * Time :4:03 PM
 */

package lk.LeonMart.superMarket.entity;


import lk.LeonMart.superMarket.dto.OrderDetailDTO;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private String OrderId;
    private LocalDate orderDate;
    private String customerId;



    public Order() {

    }

    public Order(String orderId, LocalDate orderDate, String customerId) {
        OrderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
    }



    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "OrderId='" + OrderId + '\'' +
                ", orderDate=" + orderDate +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
