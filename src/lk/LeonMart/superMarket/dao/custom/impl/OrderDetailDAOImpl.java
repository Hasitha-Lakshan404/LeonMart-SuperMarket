/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :5/31/2022
 * Time :5:15 PM
 */

package lk.LeonMart.superMarket.dao.custom.impl;


import lk.LeonMart.superMarket.dao.custom.OrderDetailDAO;
import lk.LeonMart.superMarket.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetail dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetail dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetail search(String s) {
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
