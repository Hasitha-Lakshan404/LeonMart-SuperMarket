/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :5/31/2022
 * Time :5:15 PM
 */

package lk.LeonMart.superMarket.dao.custom.impl;


import lk.LeonMart.superMarket.dao.CrudUtil;
import lk.LeonMart.superMarket.dao.custom.OrderDetailDAO;
import lk.LeonMart.superMarket.entity.Customer;
import lk.LeonMart.superMarket.entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO orderdetail VALUES (?,?,?,?,?)",entity.getOrderId(),
                entity.getItemCode(),entity.getQty(),entity.getUnitPrice(),entity.getDiscount());
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

    @Override
    public ArrayList<OrderDetail> searchOrderDetail(String enteredText) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM orderDetail where OrderId LIKE ? OR itemCode LIKE ? OR OrderQty LIKE ? OR UnitPrice LIKE ? OR Discount LIKE ?", enteredText, enteredText, enteredText, enteredText, enteredText);
        ArrayList<OrderDetail> orList = new ArrayList<>();


        while (result.next()) {
            double total=(result.getInt(3)*result.getDouble(4)-result.getDouble(5));
            orList.add(new OrderDetail(result.getString(1),
                    result.getString(2),
                    result.getInt(3),
                    result.getDouble(4),
                    result.getDouble(5),
                    total

            ));
        }
        return orList;
    }
}
