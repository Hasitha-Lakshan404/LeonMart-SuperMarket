/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :6/3/2022
 * Time :9:44 PM
 */

package lk.LeonMart.superMarket.dao.custom.impl;


import javafx.collections.FXCollections;
import lk.LeonMart.superMarket.dao.CrudUtil;
import lk.LeonMart.superMarket.dao.custom.QueryDAO;
import lk.LeonMart.superMarket.entity.CustomEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public ArrayList<CustomEntity> MostMovableItem() throws SQLException, ClassNotFoundException {

        ResultSet resultset = CrudUtil.execute("SELECT leonmart.item.ItemCode,Description,leonmart.item.PackSize,leonmart.item.UnitPrice,SUM(OrderQTY) from item inner join orderdetail on leonmart.item.ItemCode = orderdetail.ItemCode GROUP BY ItemCode ORDER BY SUM(OrderQTY) DESC");

        ArrayList<CustomEntity> cusItem=new ArrayList<>();

        while (resultset.next()) {
            cusItem.add(new CustomEntity(
                    resultset.getString(1),
                    resultset.getString(2),
                    resultset.getString(3),
                    resultset.getDouble(4),
                    resultset.getInt(5)
            ));
        }

        return cusItem;
    }

    @Override
    public ArrayList<CustomEntity> LeastMovableItem() throws SQLException, ClassNotFoundException {
        ResultSet resultset = CrudUtil.execute("SELECT leonmart.item.ItemCode,Description,leonmart.item.PackSize,leonmart.item.UnitPrice,SUM(OrderQTY) from item inner join orderdetail on leonmart.item.ItemCode = orderdetail.ItemCode GROUP BY ItemCode ORDER BY SUM(OrderQTY) ASC");

        ArrayList<CustomEntity> cusItem=new ArrayList<>();

        while (resultset.next()) {
            cusItem.add(new CustomEntity(
                    resultset.getString(1),
                    resultset.getString(2),
                    resultset.getString(3),
                    resultset.getDouble(4),
                    resultset.getInt(5)
            ));
        }

        return cusItem;
    }
}
