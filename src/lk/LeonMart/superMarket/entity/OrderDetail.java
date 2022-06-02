/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :5/30/2022
 * Time :4:04 PM
 */

package lk.LeonMart.superMarket.entity;


public class OrderDetail{
    private String OrderId;
    private String ItemCode;
    private int qty;
    private double unitPrice;
    private double discount;
    private  double total;


    public OrderDetail() {
    }

    public OrderDetail(String orderId, String itemCode, int qty, double unitPrice, double discount) {
        OrderId = orderId;
        ItemCode = itemCode;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }

    //for show OrderDetails
    public OrderDetail(String orderId, String itemCode, int qty, double unitPrice, double discount, double total) {
        OrderId = orderId;
        ItemCode = itemCode;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
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
                ", unitPrice=" + unitPrice +
                ", discount=" + discount +
                '}';
    }
}
