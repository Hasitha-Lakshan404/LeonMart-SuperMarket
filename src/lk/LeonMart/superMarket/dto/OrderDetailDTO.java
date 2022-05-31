/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :5/31/2022
 * Time :5:24 PM
 */

package lk.LeonMart.superMarket.dto;


public class OrderDetailDTO {
    private String OrderId;
    private String ItemCode;
    private int qty;
    private double discount;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderId, String itemCode, int qty, double discount) {
        OrderId = orderId;
        ItemCode = itemCode;
        this.qty = qty;
        this.discount = discount;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "OrderId='" + OrderId + '\'' +
                ", ItemCode='" + ItemCode + '\'' +
                ", qty=" + qty +
                ", discount=" + discount +
                '}';
    }
}
