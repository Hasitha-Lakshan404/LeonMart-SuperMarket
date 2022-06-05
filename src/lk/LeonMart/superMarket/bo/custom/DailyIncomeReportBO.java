/**
 * @author : Hasitha Lakshan
 * Project Name: SuperMarket
 * Date        : 6/4/2022
 * Time        : 6:49 PM
 */

package lk.LeonMart.superMarket.bo.custom;

import lk.LeonMart.superMarket.bo.SuperBO;
import lk.LeonMart.superMarket.dto.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DailyIncomeReportBO extends SuperBO {
    ArrayList<CustomDTO> getDailyIncomeReportDetails(int year,int month) throws SQLException, ClassNotFoundException;

    int getMonth(String month);
}
