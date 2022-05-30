/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :5/29/2022
 * Time :10:56 PM
 */

package lk.LeonMart.superMarket.bo.custom.impl;


import lk.LeonMart.superMarket.bo.custom.ItemBO;
import lk.LeonMart.superMarket.dao.CrudUtil;
import lk.LeonMart.superMarket.dao.custom.ItemDAO;
import lk.LeonMart.superMarket.dao.custom.impl.ItemDAOImpl;
import lk.LeonMart.superMarket.dto.CustomerDTO;
import lk.LeonMart.superMarket.dto.ItemDTO;
import lk.LeonMart.superMarket.entity.Customer;
import lk.LeonMart.superMarket.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO=new ItemDAOImpl();

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();

        ArrayList<ItemDTO> allItem= new ArrayList<>();

        for (Item item : all) {

            allItem.add(new ItemDTO(item.getItemCode(),
                    item.getDescription(),
                    item.getPackSize(),
                    item.getUnitPrice(),
                    item.getQtyOnHand(),
                    item.getDiscount()));
        }
        return allItem;
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getItemCode(),
                dto.getDescription(),
                dto.getPackSize(),
                dto.getUnitPrice(),
                dto.getQtyOnHand(),
                dto.getDiscount()));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getItemCode(),dto.getDescription(),dto.getPackSize(),dto.getUnitPrice(),dto.getQtyOnHand(),dto.getDiscount()));
    }

    @Override
    public boolean itemExist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }

    @Override
    public String generateNewItemID() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewId();
    }
}
