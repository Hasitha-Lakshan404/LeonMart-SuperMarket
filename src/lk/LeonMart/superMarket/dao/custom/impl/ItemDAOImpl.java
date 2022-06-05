/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :5/29/2022
 * Time :11:13 PM
 */

package lk.LeonMart.superMarket.dao.custom.impl;


import lk.LeonMart.superMarket.dao.CrudUtil;
import lk.LeonMart.superMarket.dao.custom.ItemDAO;
import lk.LeonMart.superMarket.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM item");

        ArrayList<Item> allItem = new ArrayList<>();

        while (resultSet.next()) {
            allItem.add(new Item(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5),
                    resultSet.getDouble(6))
            );
        }

        return allItem;
    }

    @Override
    public boolean save(Item dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO item VALUES (?,?,?,?,?,?)", dto.getItemCode(),
                dto.getDescription(),
                dto.getPackSize(),
                dto.getUnitPrice(),
                dto.getQtyOnHand(),
                dto.getDiscount());
    }

    @Override
    public boolean update(Item dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE item set Description=?,PackSize=?,UnitPrice=?,QtyOnHand=?,Discount=? WHERE ItemCode=?",
                dto.getDescription(), dto.getPackSize(), dto.getUnitPrice(), dto.getQtyOnHand(), dto.getDiscount(), dto.getItemCode());
    }

    @Override
    public Item search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM item WHERE ItemCode=?", s);
        if (rst.next()) {
            return new Item(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getDouble(6)
            );
        }
        return null;
    }


    @Override
    public boolean exist(String s) {
        return false;
    }


    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM item WHERE ItemCode=?", s);
    }


    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT ItemCode FROM item ORDER BY ItemCode DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString(1);
            int newCustomerId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newCustomerId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public ArrayList<Item> searchItems(String enteredText) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Item where ItemCode LIKE ? OR Description LIKE ? OR UnitPrice LIKE ? OR QtyOnHand LIKE ?  ", enteredText, enteredText, enteredText, enteredText);
        ArrayList<Item> list = new ArrayList<>();

        while (result.next()) {
            list.add(new Item(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getDouble(4),
                    result.getInt(5),
                    result.getDouble(6)

            ));
        }
        return list;
    }

    @Override
    public ArrayList<Item> searchItemsBYItemCodeAndDescription(String enteredText) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Item where ItemCode LIKE ? OR Description LIKE ", enteredText, enteredText);
        ArrayList<Item> list = new ArrayList<>();

        while (result.next()) {
            list.add(new Item(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getDouble(4),
                    result.getInt(5),
                    result.getDouble(6)

            ));
        }
        return list;
    }
}
