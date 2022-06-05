/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :6/4/2022
 * Time :6:47 PM
 */

package lk.LeonMart.superMarket.bo.custom.impl;


import lk.LeonMart.superMarket.bo.custom.MonthlyIncomeReportBO;
import lk.LeonMart.superMarket.dao.DAOFactory;
import lk.LeonMart.superMarket.dao.custom.QueryDAO;
import lk.LeonMart.superMarket.dao.custom.impl.QueryDAOImpl;
import lk.LeonMart.superMarket.dto.CustomDTO;
import lk.LeonMart.superMarket.entity.CustomEntity;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MonthlyIncomeReportBOImpl implements MonthlyIncomeReportBO {

    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.JOINQUERY);


    @Override
    public ArrayList<CustomDTO> getMonthlyIncomeReportDetails(int year) throws SQLException, ClassNotFoundException {


        ArrayList<CustomEntity> monthlyIncomeReportDetails = queryDAO.getMonthlyIncomeReportDetails(year);

        ArrayList<CustomDTO> customDTOS = new ArrayList<>();

        for (CustomEntity monthlyDetail : monthlyIncomeReportDetails) {
            double total = (monthlyDetail.getUnitPrice() * monthlyDetail.getQty()) - monthlyDetail.getDiscount();

            customDTOS.add(new CustomDTO(
                    monthlyDetail.getOrderDate(),
                    monthlyDetail.getItemCode(),
                    monthlyDetail.getUnitPrice(),
                    monthlyDetail.getOrderQty(),
                    monthlyDetail.getDiscount(),
                    total

            ));
        }

        return customDTOS;

    }

    @Override
    public double[] getMonthTotal(int year) throws SQLException, ClassNotFoundException {

        ArrayList<CustomDTO> monthlyIncomeReportDetails = getMonthlyIncomeReportDetails(year);

        double[] ar = new double[12];

        double jan = 0;
        double feb = 0;
        double march = 0;
        double april = 0;
        double may = 0;
        double jun = 0;
        double july = 0;
        double aug = 0;
        double sep = 0;
        double oct = 0;
        double nov = 0;
        double dec = 0;


        for (CustomDTO monthlyIncomeReportDetail : monthlyIncomeReportDetails) {
            LocalDate d = monthlyIncomeReportDetail.getOrderDate();

            switch (d.getMonthValue()) {
                case 1:
                    jan += (monthlyIncomeReportDetail.getUnitPrice() * monthlyIncomeReportDetail.getOrderQty()) - monthlyIncomeReportDetail.getDiscount();
                    break;
                case 2:
                    feb += (monthlyIncomeReportDetail.getUnitPrice() * monthlyIncomeReportDetail.getOrderQty()) - monthlyIncomeReportDetail.getDiscount();
                    break;
                case 3:
                    march += (monthlyIncomeReportDetail.getUnitPrice() * monthlyIncomeReportDetail.getOrderQty()) - monthlyIncomeReportDetail.getDiscount();
                    break;
                case 4:
                    april += (monthlyIncomeReportDetail.getUnitPrice() * monthlyIncomeReportDetail.getOrderQty()) - monthlyIncomeReportDetail.getDiscount();
                    break;
                case 5:
                    may += (monthlyIncomeReportDetail.getUnitPrice() * monthlyIncomeReportDetail.getOrderQty()) - monthlyIncomeReportDetail.getDiscount();
                    break;
                case 6:
                    jun += (monthlyIncomeReportDetail.getUnitPrice() * monthlyIncomeReportDetail.getOrderQty()) - monthlyIncomeReportDetail.getDiscount();
                    break;
                case 7:
                    july += (monthlyIncomeReportDetail.getUnitPrice() * monthlyIncomeReportDetail.getOrderQty()) - monthlyIncomeReportDetail.getDiscount();
                    break;
                case 8:
                    aug += (monthlyIncomeReportDetail.getUnitPrice() * monthlyIncomeReportDetail.getOrderQty()) - monthlyIncomeReportDetail.getDiscount();
                    break;
                case 9:
                    sep += (monthlyIncomeReportDetail.getUnitPrice() * monthlyIncomeReportDetail.getOrderQty()) - monthlyIncomeReportDetail.getDiscount();
                    break;
                case 10:
                    oct += (monthlyIncomeReportDetail.getUnitPrice() * monthlyIncomeReportDetail.getOrderQty()) - monthlyIncomeReportDetail.getDiscount();
                    break;
                case 11:
                    nov += (monthlyIncomeReportDetail.getUnitPrice() * monthlyIncomeReportDetail.getOrderQty()) - monthlyIncomeReportDetail.getDiscount();
                    break;
                case 12:
                    dec += (monthlyIncomeReportDetail.getUnitPrice() * monthlyIncomeReportDetail.getOrderQty()) - monthlyIncomeReportDetail.getDiscount();
                    break;
            }

            ar[0] = jan;
            ar[1] = feb;
            ar[2] = march;
            ar[3] = april;
            ar[4] = may;
            ar[5] = jun;
            ar[6] = july;
            ar[7] = aug;
            ar[8] = sep;
            ar[9] = oct;
            ar[10] = nov;
            ar[11] = dec;

        }
        return ar;
    }
}
