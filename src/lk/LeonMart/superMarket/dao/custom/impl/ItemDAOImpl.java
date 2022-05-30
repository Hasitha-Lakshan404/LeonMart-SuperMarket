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
        ResultSet resultSet=CrudUtil.execute("SELECT * FROM item");

        ArrayList<Item> allItem=new ArrayList<>();

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
                dto.getDescription(),dto.getPackSize(),dto.getUnitPrice(),dto.getQtyOnHand(),dto.getDiscount(),dto.getItemCode());
    }

    @Override
    public Item search(String s) {
        return null;
    }

    @Override
    public boolean exist(String s) {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM item WHERE ItemCode=?",s);
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
}
