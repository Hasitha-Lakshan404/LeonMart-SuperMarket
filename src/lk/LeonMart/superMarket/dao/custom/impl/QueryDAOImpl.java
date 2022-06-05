/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :6/3/2022
 * Time :9:44 PM
 */

package lk.LeonMart.superMarket.dao.custom.impl;


import lk.LeonMart.superMarket.dao.CrudUtil;
import lk.LeonMart.superMarket.dao.custom.QueryDAO;
import lk.LeonMart.superMarket.entity.CustomEntity;
import lk.LeonMart.superMarket.entity.Customer;
import lk.LeonMart.superMarket.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public ArrayList<CustomEntity> MostMovableItem() throws SQLException, ClassNotFoundException {

        ResultSet resultset = CrudUtil.execute("SELECT leonmart.item.ItemCode,Description,leonmart.item.PackSize,leonmart.item.UnitPrice,SUM(OrderQTY) from item inner join orderdetail on leonmart.item.ItemCode = orderdetail.ItemCode GROUP BY ItemCode ORDER BY SUM(OrderQTY) DESC");

        ArrayList<CustomEntity> cusItem = new ArrayList<>();

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

        ArrayList<CustomEntity> cusItem = new ArrayList<>();

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
    public ArrayList<CustomEntity> getDailyIncomeReportDetails(int year,int month) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = CrudUtil.execute("SELECT `order`.OrderDate, orderdetail.ItemCode,leonmart.item.Description,orderdetail.UnitPrice,orderdetail.OrderQty,orderdetail.Discount FROM `order` INNER JOIN orderdetail ON  `order`.OrderID=leonmart.orderdetail.OrderId INNER JOIN leonmart.item ON  item.ItemCode=OrderDetail.ItemCode WHERE YEAR(`order`.OrderDate)=? AND MONTH(`order`.OrderDate)=? ", year, month);
        ArrayList<CustomEntity> customEntity=new ArrayList<>();

        while (resultSet.next()){
            double total=0;
            customEntity.add(new CustomEntity(
                    resultSet.getDate(1).toLocalDate(),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5),
                    resultSet.getDouble(6),
                    total=(resultSet.getDouble(4)*resultSet.getInt(5))-resultSet.getDouble(6)
                    )
            );
        }

        return customEntity;
    }

    @Override
    public ArrayList<CustomEntity> getAnnuallyIncomeReportDetails() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT `order`.OrderDate, orderdetail.ItemCode,orderdetail.UnitPrice,orderdetail.OrderQty,orderdetail.Discount FROM `order` INNER JOIN orderdetail ON `order`.OrderID=leonmart.orderdetail.OrderId");

        ArrayList<CustomEntity> customEnt = new ArrayList<>();

        while (result.next()) {
            double total = (result.getDouble(3) * result.getInt(4)) - result.getDouble(5);

            customEnt.add(new CustomEntity(
                    result.getDate(1).toLocalDate(),
                    result.getString(2),
                    result.getDouble(3),
                    result.getInt(4),
                    result.getDouble(5),
                    total

            ));
        }

        return customEnt;
    }

    @Override
    public ArrayList<CustomEntity> getMonthlyIncomeReportDetails(int year) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT `order`.OrderDate, orderdetail.ItemCode,orderdetail.UnitPrice,orderdetail.OrderQty,orderdetail.Discount FROM `order` INNER JOIN orderdetail ON `order`.OrderID=leonmart.orderdetail.OrderId WHERE year(`order`.OrderDate)=?", year);

        ArrayList<CustomEntity> customEnt = new ArrayList<>();

        while (result.next()) {
            double total = (result.getDouble(3) * result.getInt(4)) - result.getDouble(5);

            customEnt.add(new CustomEntity(
                    result.getDate(1).toLocalDate(),
                    result.getString(2),
                    result.getDouble(3),
                    result.getInt(4),
                    result.getDouble(5),
                    total

            ));
        }

        return customEnt;
    }

    @Override
    public ArrayList<CustomEntity> getSearchItemsBYItemCodeAndDescription(String enteredText) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT leonmart.customer.CusID,leonmart.item.ItemCode,leonmart.item.Description FROM  `order`  INNER JOIN leonmart.customer ON `order`.CusID=customer.`CusID`  INNER JOIN OrderDetail ON `order`.OrderID=OrderDetail.OrderID INNER JOIN leonmart.item ON OrderDetail.ItemCode=leonmart.item.ItemCode WHERE `order`.CusID=? ", enteredText);

        ArrayList<CustomEntity> customEnt = new ArrayList<>();

        while (result.next()) {
            customEnt.add(new CustomEntity(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3)

            ));
        }

        return customEnt;
    }


}
