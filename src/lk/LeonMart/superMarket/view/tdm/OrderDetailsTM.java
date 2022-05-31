/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :5/31/2022
 * Time :5:08 PM
 */

package lk.LeonMart.superMarket.view.tdm;


public class OrderDetailsTM {
    private String itemCode;
    private String description;
    private int qty;
    private double UnitPrice;
    private double discount;
    private double total;

    public OrderDetailsTM() {
    }

    public OrderDetailsTM(String itemCode, String description, int qty, double unitPrice, double discount, double total) {
        this.itemCode = itemCode;
        this.description = description;
        this.qty = qty;
        UnitPrice = unitPrice;
        this.discount = discount;
        this.total = total;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetailsTM{" +
                "itemCode='" + itemCode + '\'' +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", UnitPrice=" + UnitPrice +
                ", discount=" + discount +
                ", total=" + total +
                '}';
    }
}

