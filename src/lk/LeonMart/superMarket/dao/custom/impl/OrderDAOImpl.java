/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :5/31/2022
 * Time :5:14 PM
 */

package lk.LeonMart.superMarket.dao.custom.impl;


import com.sun.org.apache.xpath.internal.operations.Or;
import lk.LeonMart.superMarket.dao.CrudUtil;
import lk.LeonMart.superMarket.dao.custom.OrderDAO;
import lk.LeonMart.superMarket.entity.Item;
import lk.LeonMart.superMarket.entity.Order;
import lk.LeonMart.superMarket.entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet=CrudUtil.execute("SELECT * FROM `Order` ORDER BY OrderId DESC");

        ArrayList<Order> allOrders=new ArrayList<>();

        while (resultSet.next()) {
            allOrders.add(new Order(resultSet.getString(1), resultSet.getDate(2).toLocalDate(), resultSet.getString(3))
            );
        }

        return allOrders;
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
        return  CrudUtil.execute("DELETE FROM `order` WHERE OrderID=?",s);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT OrderId FROM `Order` ORDER BY OrderId DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("OrderID").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public ArrayList<Order> searchOrder(String enteredText) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM `order` where OrderId LIKE ? OR OrderDate LIKE ? OR CusID LIKE ? ORDER BY OrderId DESC", enteredText, enteredText, enteredText);
        ArrayList<Order> orList = new ArrayList<>();


        while (result.next()) {
            orList.add(new Order(
                    result.getString(1),
                    result.getDate(2).toLocalDate(),
                    result.getString(3)

            ));
        }
        return orList;
    }
}
