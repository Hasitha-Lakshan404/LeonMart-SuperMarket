/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :6/2/2022
 * Time :3:17 PM
 */

package lk.LeonMart.superMarket.bo.custom.impl;


import lk.LeonMart.superMarket.bo.custom.OrderBO;
import lk.LeonMart.superMarket.dao.DAOFactory;
import lk.LeonMart.superMarket.dao.custom.OrderDAO;
import lk.LeonMart.superMarket.dao.custom.QueryDAO;
import lk.LeonMart.superMarket.dao.custom.impl.OrderDAOImpl;
import lk.LeonMart.superMarket.dto.OrderDTO;
import lk.LeonMart.superMarket.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {

    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);


    @Override
    public ArrayList<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        ArrayList<Order> all = orderDAO.getAll();
        ArrayList<OrderDTO> allOrder = new ArrayList<>();

        for (Order order : all) {
            allOrder.add(new OrderDTO(order.getOrderId(), order.getOrderDate(), order.getCustomerId()));
        }
        return allOrder;
    }

    @Override
    public boolean deleteOrders(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(id);
    }

    @Override
    public ArrayList<OrderDTO> getAllSearchOrder(String enteredText) throws SQLException, ClassNotFoundException {

        ArrayList<Order> order = orderDAO.searchOrder(enteredText);

        ArrayList<OrderDTO> orDto = new ArrayList<>();

        for (Order orderList : order) {

            orDto.add(new OrderDTO(
                    orderList.getOrderId(),
                    orderList.getOrderDate(),
                    orderList.getCustomerId()
            ));
        }
        return orDto;
    }
}
