/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :6/3/2022
 * Time :11:18 PM
 */

package lk.LeonMart.superMarket.bo.custom.impl;


import lk.LeonMart.superMarket.bo.custom.MainReportBO;
import lk.LeonMart.superMarket.dao.custom.QueryDAO;
import lk.LeonMart.superMarket.dao.custom.impl.QueryDAOImpl;
import lk.LeonMart.superMarket.dto.CustomDTO;
import lk.LeonMart.superMarket.entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainReportBOImpl implements MainReportBO {
    QueryDAO customerDAO = new QueryDAOImpl();

    @Override
    public ArrayList<CustomDTO> MostMovableItem() throws SQLException, ClassNotFoundException {

        ArrayList<CustomEntity> customEntities = customerDAO.MostMovableItem();

        ArrayList<CustomDTO> itDto = new ArrayList<>();

        for (CustomEntity customEt : customEntities) {
            itDto.add(new CustomDTO(
                    customEt.getItemCode(),
                    customEt.getDescription(),
                    customEt.getPackSize(),
                    customEt.getUnitPrice(),
                    customEt.getOrderQty()
            ));
        }

        return itDto;

    }

    @Override
    public ArrayList<CustomDTO> LeastMovableItem() throws SQLException, ClassNotFoundException {

        ArrayList<CustomEntity> customEntities = customerDAO.LeastMovableItem();

        ArrayList<CustomDTO> itDto = new ArrayList<>();

        for (CustomEntity customEt : customEntities) {
            itDto.add(new CustomDTO(
                    customEt.getItemCode(),
                    customEt.getDescription(),
                    customEt.getPackSize(),
                    customEt.getUnitPrice(),
                    customEt.getOrderQty()
            ));
        }

        return itDto;
    }

}
