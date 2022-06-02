/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :5/26/2022
 * Time :10:39 PM
 */

package lk.LeonMart.superMarket.dao.custom;


import lk.LeonMart.superMarket.dao.CrudDAO;
import lk.LeonMart.superMarket.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    public ArrayList<Customer> searchCustomers(String enteredText) throws SQLException, ClassNotFoundException;
}
