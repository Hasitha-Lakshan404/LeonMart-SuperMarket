/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :5/31/2022
 * Time :5:14 PM
 */

package lk.LeonMart.superMarket.dao.custom.impl;


import lk.LeonMart.superMarket.dao.CrudUtil;
import lk.LeonMart.superMarket.dao.custom.OrderDAO;
import lk.LeonMart.superMarket.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO `Order`  VALUES (?,?,?)", entity.getOrderId(), entity.getOrderDate(), entity.getCustomerId());

    }

    @Override
    public boolean update(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String s) {
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
        ResultSet rst = CrudUtil.execute("SELECT OrderId FROM `Order` ORDER BY OrderId DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("OrderID").replace("OID-", "")) + 1)) : "OID-001";
    }
}
