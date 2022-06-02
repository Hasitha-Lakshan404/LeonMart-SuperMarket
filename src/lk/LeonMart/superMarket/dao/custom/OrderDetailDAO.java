/**
 * @author : Hasitha Lakshan
 * Project Name: SuperMarket
 * Date        : 5/31/2022
 * Time        : 5:13 PM
 */

package lk.LeonMart.superMarket.dao.custom;

import lk.LeonMart.superMarket.dao.CrudDAO;
import lk.LeonMart.superMarket.entity.Customer;
import lk.LeonMart.superMarket.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailDAO extends CrudDAO<OrderDetail,String> {
    public ArrayList<OrderDetail> searchOrderDetail(String enteredText) throws SQLException, ClassNotFoundException;

}
