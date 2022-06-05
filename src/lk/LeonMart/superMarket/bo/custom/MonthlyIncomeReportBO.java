/**
 * @author : Hasitha Lakshan
 * Project Name: SuperMarket
 * Date        : 6/4/2022
 * Time        : 6:46 PM
 */

package lk.LeonMart.superMarket.bo.custom;

import lk.LeonMart.superMarket.bo.SuperBO;
import lk.LeonMart.superMarket.dto.CustomDTO;
import lk.LeonMart.superMarket.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MonthlyIncomeReportBO extends SuperBO {
    ArrayList<CustomDTO> getMonthlyIncomeReportDetails(int year) throws SQLException, ClassNotFoundException;

    public double[] getMonthTotal(int year) throws SQLException, ClassNotFoundException;
}
