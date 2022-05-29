/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :5/29/2022
 * Time :10:56 PM
 */

package lk.LeonMart.superMarket.bo.custom.impl;


import lk.LeonMart.superMarket.bo.custom.ItemBO;
import lk.LeonMart.superMarket.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean itemExist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewItemID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
