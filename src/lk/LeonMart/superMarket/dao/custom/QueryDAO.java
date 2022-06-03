/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :6/3/2022
 * Time :9:43 PM
 */

package lk.LeonMart.superMarket.dao.custom;


import lk.LeonMart.superMarket.dao.SuperDAO;
import lk.LeonMart.superMarket.entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;


public interface QueryDAO extends SuperDAO {
    ArrayList<CustomEntity> MostMovableItem() throws SQLException, ClassNotFoundException;
    ArrayList<CustomEntity> LeastMovableItem() throws SQLException, ClassNotFoundException;
}
