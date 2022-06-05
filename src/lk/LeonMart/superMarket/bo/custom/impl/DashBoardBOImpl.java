/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :6/3/2022
 * Time :11:32 AM
 */

package lk.LeonMart.superMarket.bo.custom.impl;


import lk.LeonMart.superMarket.bo.BOFactory;
import lk.LeonMart.superMarket.bo.custom.DashBoardBO;
import lk.LeonMart.superMarket.bo.custom.OrderBO;
import lk.LeonMart.superMarket.dao.DAOFactory;
import lk.LeonMart.superMarket.dao.custom.CustomerDAO;
import lk.LeonMart.superMarket.dao.custom.ItemDAO;
import lk.LeonMart.superMarket.dao.custom.QueryDAO;
import lk.LeonMart.superMarket.dao.custom.impl.CustomerDAOImpl;
import lk.LeonMart.superMarket.dto.CustomDTO;
import lk.LeonMart.superMarket.dto.ItemDTO;
import lk.LeonMart.superMarket.dto.OrderDTO;
import lk.LeonMart.superMarket.entity.CustomEntity;
import lk.LeonMart.superMarket.entity.Customer;
import lk.LeonMart.superMarket.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class DashBoardBOImpl implements DashBoardBO {
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.JOINQUERY);
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);


    @Override
    public ArrayList<ItemDTO> getAllItemName() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();

        ArrayList<ItemDTO> allItem = new ArrayList<>();

        for (Item item : all) {

            allItem.add(new ItemDTO(item.getItemCode(),
                    item.getDescription(),
                    item.getPackSize(),
                    item.getUnitPrice(),
                    item.getQtyOnHand(),
                    item.getDiscount()));
        }
        return allItem;
    }

    @Override
    public ArrayList<ItemDTO> getSearchICodeDescription(String enteredText) throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = itemDAO.searchItems(enteredText);
        ArrayList<ItemDTO> itDto = new ArrayList<>();

        for (Item item : items) {
            itDto.add(new ItemDTO(
                    item.getItemCode(),
                    item.getDescription(),
                    item.getPackSize(),
                    item.getUnitPrice(),
                    item.getQtyOnHand(),
                    item.getDiscount()
            ));
        }
        return itDto;
    }

    @Override
    public ArrayList<CustomDTO> getCustomerBuyingItems(String text) throws SQLException, ClassNotFoundException {
        ArrayList<CustomEntity> searchItem = queryDAO.getSearchItemsBYItemCodeAndDescription(text);

        ArrayList<CustomDTO> customDTOS = new ArrayList<>();

        for (CustomEntity customEntity : searchItem) {
            customDTOS.add(new CustomDTO(
                    customEntity.getCusId(),
                    customEntity.getItemCode(),
                    customEntity.getDescription()
            ));
        }
        return customDTOS;

    }

    @Override
    public ArrayList<String> getCmbCustomerIds() throws SQLException, ClassNotFoundException {
        CustomerDAO customerDAO = new CustomerDAOImpl();

        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<String> allIds = new ArrayList<>();


        for (Customer customer : all) {
            allIds.add(customer.getCusId());
        }

        return allIds;
    }

    @Override
    public int getOrderCount() throws SQLException, ClassNotFoundException {

        ArrayList<OrderDTO> allOrders = orderBO.getAllOrders();

        int total = 0;
        for (OrderDTO allOrder : allOrders) {
            total += 1;
        }
        return total;
    }

    @Override
    public int getItemTypes() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();

        int tot = 0;
        for (Item item : all) {
            tot += 1;
        }

        return tot;
    }

    @Override
    public int getTotalCustomer() throws SQLException, ClassNotFoundException {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        int total = 0;

        for (Customer customer : customerDAO.getAll()) {
            total += 1;
        }
        return total;
    }


}
