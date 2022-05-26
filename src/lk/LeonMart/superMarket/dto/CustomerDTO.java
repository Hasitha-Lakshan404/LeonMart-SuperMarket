/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :5/26/2022
 * Time :2:16 PM
 */

package lk.LeonMart.superMarket.dto;


public class CustomerDTO {
    private String cusId;
    private String cusTitle;
    private String cusName;
    private String cusAddress;
    private String city;
    private String province;
    private String postalCode;

    public CustomerDTO() {

    }

    public CustomerDTO(String cusId, String cusTitle, String cusName, String cusAddress, String city, String province, String postalCode) {
        this.cusId = cusId;
        this.cusTitle = cusTitle;
        this.cusName = cusName;
        this.cusAddress = cusAddress;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusTitle() {
        return cusTitle;
    }

    public void setCusTitle(String cusTitle) {
        this.cusTitle = cusTitle;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "cusId='" + cusId + '\'' +
                ", cusTitle='" + cusTitle + '\'' +
                ", cusName='" + cusName + '\'' +
                ", cusAddress='" + cusAddress + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }

}
