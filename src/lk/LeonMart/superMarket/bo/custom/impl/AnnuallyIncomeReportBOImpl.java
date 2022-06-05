/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :6/4/2022
 * Time :6:49 PM
 */

package lk.LeonMart.superMarket.bo.custom.impl;


import lk.LeonMart.superMarket.bo.custom.AnnuallyIncomeReportBO;
import lk.LeonMart.superMarket.dao.DAOFactory;
import lk.LeonMart.superMarket.dao.custom.CustomerDAO;
import lk.LeonMart.superMarket.dao.custom.QueryDAO;
import lk.LeonMart.superMarket.dao.custom.impl.QueryDAOImpl;
import lk.LeonMart.superMarket.dto.CustomDTO;
import lk.LeonMart.superMarket.entity.CustomEntity;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AnnuallyIncomeReportBOImpl implements AnnuallyIncomeReportBO {

    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.JOINQUERY);

    @Override
    public ArrayList<CustomDTO> getAnnuallyIncomeReportDetails() throws SQLException, ClassNotFoundException {


        ArrayList<CustomEntity> monthlyIncomeReportDetails = queryDAO.getAnnuallyIncomeReportDetails();

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
    public long[][] getAnnuallyData(int txtSearchYearOne,int txtSearchYearTwo) throws SQLException, ClassNotFoundException {

        ArrayList<CustomDTO> anDetail = getAnnuallyIncomeReportDetails();

        //use +1 to get earnings this entered year to other year.
        int count = txtSearchYearTwo - txtSearchYearOne + 1;

        long[][] ar = new long[count][2];  //5

        int tempYear = txtSearchYearTwo;

        for (int i = count - 1; i >= 0; i--) {
            ar[i][0] = tempYear;
            tempYear--;
        }

        for (long[] x : ar
        ) {

        }

        for (CustomDTO customDTO : anDetail) {
            //getLocalDate
            LocalDate d = customDTO.getOrderDate();

            for (int i = 0; i < count; i++) {

                if (d.getYear() == ar[i][0]) {

//                    ar[i][1]+=result.getDouble(2);
                    ar[i][1] += (customDTO.getUnitPrice() * customDTO.getOrderQty()) -customDTO.getDiscount();
                }
            }
        }


        return ar;
    }


}
