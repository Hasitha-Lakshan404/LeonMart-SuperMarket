/**
 * @author : Hasitha Lakshan
 * Project Name: SuperMarket
 * Date        : 6/3/2022
 * Time        : 11:32 AM
 */

package lk.LeonMart.superMarket.bo.custom;

import lk.LeonMart.superMarket.bo.SuperBO;
import lk.LeonMart.superMarket.dto.CustomDTO;
import lk.LeonMart.superMarket.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DashBoardBO extends SuperBO {
    ArrayList<ItemDTO> getAllItemName() throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getSearchICodeDescription(String enteredText) throws SQLException, ClassNotFoundException;

    ArrayList<CustomDTO> getCustomerBuyingItems(String text) throws SQLException, ClassNotFoundException;

    ArrayList<String> getCmbCustomerIds() throws SQLException, ClassNotFoundException;

    public int getOrderCount() throws SQLException, ClassNotFoundException;

    public int getItemTypes() throws SQLException, ClassNotFoundException;

    public int getTotalCustomer() throws SQLException, ClassNotFoundException;


}
