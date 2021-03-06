/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :5/31/2022
 * Time :5:13 PM
 */

package lk.LeonMart.superMarket.dao.custom;


import lk.LeonMart.superMarket.dao.CrudDAO;
import lk.LeonMart.superMarket.entity.Order;
import lk.LeonMart.superMarket.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO extends CrudDAO<Order,String> {
    public ArrayList<Order> searchOrder(String enteredText) throws SQLException, ClassNotFoundException;

}
