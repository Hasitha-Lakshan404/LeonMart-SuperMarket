/**
 * @author : Hasitha Lakshan
 * Project Name: SuperMarket
 * Date        : 6/4/2022
 * Time        : 6:48 PM
 */

package lk.LeonMart.superMarket.bo.custom.impl;

import lk.LeonMart.superMarket.bo.custom.DailyIncomeReportBO;
import lk.LeonMart.superMarket.dao.DAOFactory;
import lk.LeonMart.superMarket.dao.custom.QueryDAO;
import lk.LeonMart.superMarket.dao.custom.impl.QueryDAOImpl;
import lk.LeonMart.superMarket.dto.CustomDTO;
import lk.LeonMart.superMarket.entity.CustomEntity;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DailyIncomeReportBOImpl implements DailyIncomeReportBO {

    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.JOINQUERY);

    @Override
    public ArrayList<CustomDTO> getDailyIncomeReportDetails(int year, int month) throws SQLException, ClassNotFoundException {
        ArrayList<CustomEntity> dailyIncomeReportDetails = queryDAO.getDailyIncomeReportDetails(year, month);

        ArrayList<CustomDTO> cusDto = new ArrayList<>();

        for (CustomEntity dailyReport : dailyIncomeReportDetails) {
            cusDto.add(new CustomDTO(
                    dailyReport.getOrderDate(),
                    dailyReport.getItemCode(),
                    dailyReport.getDescription(),
                    dailyReport.getUnitPrice(),
                    dailyReport.getOrderQty(),
                    dailyReport.getDiscount(),
                    dailyReport.getTotal()
            ));
        }

        return cusDto;
    }

    @Override
    public int getMonth(String month) {
        switch (month) {
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
            default:
                return LocalDate.now().getMonthValue();
        }
    }
}
