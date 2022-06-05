/**
 * @author : Hasitha Lakshan
 * Project Name: SuperMarket
 * Date        : 6/4/2022
 * Time        : 6:47 PM
 */

package lk.LeonMart.superMarket.bo.custom;

import lk.LeonMart.superMarket.bo.SuperBO;
import lk.LeonMart.superMarket.dto.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AnnuallyIncomeReportBO extends SuperBO {
    ArrayList<CustomDTO> getAnnuallyIncomeReportDetails() throws SQLException, ClassNotFoundException;

    public long[][] getAnnuallyData(int yearOne,int YearTwo) throws SQLException, ClassNotFoundException;
}
