/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :5/31/2022
 * Time :5:23 PM
 */

package lk.LeonMart.superMarket.dto;


import java.time.LocalDate;

public class OrderDTO {
    private String OrderId;
    private LocalDate orderDate;
    private String customerId;


    public OrderDTO() {
    }

    public OrderDTO(String orderId, LocalDate orderDate, String customerId) {
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

