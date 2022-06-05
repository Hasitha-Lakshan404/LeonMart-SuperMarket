/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :6/2/2022
 * Time :4:27 PM
 */

package lk.LeonMart.superMarket.bo.custom.impl;


import lk.LeonMart.superMarket.bo.custom.OrderDetailBO;
import lk.LeonMart.superMarket.dao.DAOFactory;
import lk.LeonMart.superMarket.dao.custom.OrderDAO;
import lk.LeonMart.superMarket.dao.custom.OrderDetailDAO;
import lk.LeonMart.superMarket.dao.custom.impl.OrderDetailDAOImpl;
import lk.LeonMart.superMarket.dto.CustomerDTO;
import lk.LeonMart.superMarket.dto.OrderDetailDTO;
import lk.LeonMart.superMarket.entity.Customer;
import lk.LeonMart.superMarket.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailBOImpl implements OrderDetailBO {

    private final OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    @Override
    public ArrayList<OrderDetailDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderDetailDTO> searchOrderDetails(String enteredText) throws SQLException, ClassNotFoundException {


        ArrayList<OrderDetail> orderDetails = orderDetailDAO.searchOrderDetail(enteredText);


        ArrayList<OrderDetailDTO> orDetailDto=new ArrayList<>();

        for (OrderDetail ordersList : orderDetails) {

            orDetailDto.add(new OrderDetailDTO(ordersList.getOrderId(),
                    ordersList.getItemCode(),
                    ordersList.getQty(),
                    ordersList.getUnitPrice(),
                    ordersList.getDiscount(),
                    ordersList.getTotal()
            ));
        }
        return orDetailDto;
    }
}
