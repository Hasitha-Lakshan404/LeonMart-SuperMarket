/**
 * @author : Hasitha Lakshan
 * Project Name: SuperMarket
 * Date        : 5/29/2022
 * Time        : 11:13 PM
 */

package lk.LeonMart.superMarket.dao.custom;

import lk.LeonMart.superMarket.dao.CrudDAO;
import lk.LeonMart.superMarket.entity.Customer;
import lk.LeonMart.superMarket.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item,String> {
    public ArrayList<Item> searchItems(String enteredText) throws SQLException, ClassNotFoundException;

    public ArrayList<Item> searchItemsBYItemCodeAndDescription(String enteredText) throws SQLException, ClassNotFoundException;
}
