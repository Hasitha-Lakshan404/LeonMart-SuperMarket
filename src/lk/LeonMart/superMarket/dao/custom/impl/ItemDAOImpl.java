/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :5/29/2022
 * Time :11:13 PM
 */

package lk.LeonMart.superMarket.dao.custom.impl;


import lk.LeonMart.superMarket.dao.custom.ItemDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<ItemDAO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(ItemDAO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(ItemDAO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ItemDAO search(String s) {
        return null;
    }

    @Override
    public boolean exist(String s) {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
