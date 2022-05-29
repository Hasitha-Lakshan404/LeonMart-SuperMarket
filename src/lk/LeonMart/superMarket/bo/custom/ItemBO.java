/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :5/29/2022
 * Time :10:56 PM
 */

package lk.LeonMart.superMarket.bo.custom;


import lk.LeonMart.superMarket.bo.SuperBO;
import lk.LeonMart.superMarket.dto.CustomerDTO;
import lk.LeonMart.superMarket.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

    boolean itemExist(String id) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String id) throws SQLException, ClassNotFoundException;

    String generateNewItemID() throws SQLException, ClassNotFoundException;
}
