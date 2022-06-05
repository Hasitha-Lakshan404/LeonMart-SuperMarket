/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :6/3/2022
 * Time :9:43 PM
 */

package lk.LeonMart.superMarket.dao.custom;


import lk.LeonMart.superMarket.dao.SuperDAO;
import lk.LeonMart.superMarket.entity.CustomEntity;
import lk.LeonMart.superMarket.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;


public interface QueryDAO extends SuperDAO {
    ArrayList<CustomEntity> MostMovableItem() throws SQLException, ClassNotFoundException;

    ArrayList<CustomEntity> LeastMovableItem() throws SQLException, ClassNotFoundException;

    ArrayList<CustomEntity> getDailyIncomeReportDetails(int year,int Month) throws SQLException, ClassNotFoundException;

    ArrayList<CustomEntity> getAnnuallyIncomeReportDetails() throws SQLException, ClassNotFoundException;

    ArrayList<CustomEntity> getMonthlyIncomeReportDetails(int Year) throws SQLException, ClassNotFoundException;

    public ArrayList<CustomEntity> getSearchItemsBYItemCodeAndDescription(String enteredText) throws SQLException, ClassNotFoundException;


}
