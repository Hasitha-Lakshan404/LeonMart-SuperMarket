/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :6/3/2022
 * Time :9:57 PM
 */

package lk.LeonMart.superMarket.entity;


public class CustomEntity {
    private String itemCode;
    private String description;
    private String packSize;
    private double unitPrice;
    private int orderQty;

    public CustomEntity() {
    }

    public CustomEntity(String itemCode, String description, String packSize, double unitPrice, int orderQty) {
        this.itemCode = itemCode;
        this.description = description;
        this.packSize = packSize;
        this.unitPrice = unitPrice;
        this.orderQty = orderQty;
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

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    @Override
    public String toString() {
        return "CustomEntity{" +
                "itemCode='" + itemCode + '\'' +
                ", description='" + description + '\'' +
                ", packSize='" + packSize + '\'' +
                ", unitPrice=" + unitPrice +
                ", orderQty=" + orderQty +
                '}';
    }
}


