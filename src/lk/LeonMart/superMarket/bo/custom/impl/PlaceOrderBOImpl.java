/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :5/31/2022
 * Time :5:21 PM
 */

package lk.LeonMart.superMarket.bo.custom.impl;


import lk.LeonMart.superMarket.bo.custom.PlaceOrderBO;
import lk.LeonMart.superMarket.dao.custom.CustomerDAO;
import lk.LeonMart.superMarket.dao.custom.ItemDAO;
import lk.LeonMart.superMarket.dao.custom.OrderDAO;
import lk.LeonMart.superMarket.dao.custom.OrderDetailDAO;
import lk.LeonMart.superMarket.dao.custom.impl.CustomerDAOImpl;
import lk.LeonMart.superMarket.dao.custom.impl.ItemDAOImpl;
import lk.LeonMart.superMarket.dao.custom.impl.OrderDAOImpl;
import lk.LeonMart.superMarket.dao.custom.impl.OrderDetailDAOImpl;
import lk.LeonMart.superMarket.db.DBConnection;
import lk.LeonMart.superMarket.dto.CustomerDTO;
import lk.LeonMart.superMarket.dto.ItemDTO;
import lk.LeonMart.superMarket.dto.OrderDTO;
import lk.LeonMart.superMarket.dto.OrderDetailDTO;
import lk.LeonMart.superMarket.entity.Customer;
import lk.LeonMart.superMarket.entity.Item;
import lk.LeonMart.superMarket.entity.Order;
import lk.LeonMart.superMarket.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    private final OrderDAO orderDAO = new OrderDAOImpl();
    OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();
    ItemDAO itemDAO = new ItemDAOImpl();
    CustomerDAO customerDAO = new CustomerDAOImpl();


    @Override
    public boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {

        /*Transaction*/
        Connection connection = DBConnection.getInstance().getConnection();
        /*if order id already exist*/
        if (orderDAO.exist(dto.getOrderId())) {

        }
        connection.setAutoCommit(false);

        boolean save = orderDAO.save(new Order(dto.getOrderId(), dto.getOrderDate(), dto.getCustomerId()));

        if (!save) {

            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }




        for (OrderDetailDTO detailDTO : dto.getOrderDetails()) {

            boolean save1 = orderDetailDAO.save(new OrderDetail(detailDTO.getOrderId(), detailDTO.getItemCode(), detailDTO.getQty(), detailDTO.getUnitPrice(), detailDTO.getDiscount()));

            if (!save1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }



            //Search & Update Item
            ItemDTO item = searchItem(detailDTO.getItemCode());
            item.setQtyOnHand(item.getQtyOnHand() - detailDTO.getQty());

            //update item
            boolean update = itemDAO.update(new Item(item.getItemCode(), item.getDescription(), item.getPackSize(), item.getUnitPrice(), item.getQtyOnHand(), item.getDiscount()));

            if (!update) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer cus = customerDAO.search(id);
        return new CustomerDTO(cus.getCusId(),cus.getCusTitle(), cus.getCusName(), cus.getCusAddress(), cus.getCity(),
                cus.getProvince(), cus.getPostalCode());
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item item=itemDAO.search(code);
        return new ItemDTO(item.getItemCode(),item.getDescription(),item.getPackSize(),item.getUnitPrice(),item.getQtyOnHand(),item.getDiscount());
    }

    @Override
    public boolean checkItemIsAvailable(String code) {
        return false;
    }

    @Override
    public boolean checkCustomerIsAvailable(String id) {
        return false;
    }

    @Override
    public String generateNewOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewId();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        for (Customer ent : all) {
            allCustomers.add(new CustomerDTO(ent.getCusId(),ent.getCusTitle(), ent.getCusName(), ent.getCusAddress(), ent.getCity(),
                    ent.getProvince(), ent.getPostalCode()));
        }
        return allCustomers;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        for (Item ent : all) {
            allItems.add(new ItemDTO(ent.getItemCode(),ent.getDescription(),ent.getPackSize(),ent.getUnitPrice(),ent.getQtyOnHand(),ent.getDiscount()));
        }
        return allItems;
    }
}
