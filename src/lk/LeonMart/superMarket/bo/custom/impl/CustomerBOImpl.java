/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :5/26/2022
 * Time :11:04 PM
 */

package lk.LeonMart.superMarket.bo.custom.impl;


import lk.LeonMart.superMarket.bo.custom.CustomerBO;
import lk.LeonMart.superMarket.dao.DAOFactory;
import lk.LeonMart.superMarket.dao.custom.CustomerDAO;
import lk.LeonMart.superMarket.dao.custom.QueryDAO;
import lk.LeonMart.superMarket.dao.custom.impl.CustomerDAOImpl;
import lk.LeonMart.superMarket.dto.CustomerDTO;
import lk.LeonMart.superMarket.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> allCustomers= new ArrayList<>();
        for (Customer customer : all) {
            allCustomers.add(new CustomerDTO(customer.getCusId(),
                    customer.getCusTitle(),
                    customer.getCusName(),
                    customer.getCusAddress(),
                    customer.getCity(),
                    customer.getProvince(),
                    customer.getPostalCode()));
        }
        return allCustomers;
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getCusId(),
                dto.getCusTitle(),
                dto.getCusName(),
                dto.getCusAddress(),
                dto.getCity(),
                dto.getProvince(),
                dto.getPostalCode()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getCusId(),
                dto.getCusTitle(),
                dto.getCusName(),
                dto.getCusAddress(),
                dto.getCity(),
                dto.getProvince(),
                dto.getPostalCode()) );
    }

    @Override
    public boolean customerExist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public String generateNewCustomerID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewId();
    }

    @Override
    public ArrayList<CustomerDTO> searchCustomers(String enteredText) throws SQLException, ClassNotFoundException {

        ArrayList<Customer> customers = customerDAO.searchCustomers(enteredText);
        ArrayList<CustomerDTO> cusDto=new ArrayList<>();

        for (Customer customer : customers) {
            cusDto.add(new CustomerDTO(customer.getCusId(),
                    customer.getCusTitle(),
                    customer.getCusName(),
                    customer.getCusAddress(),
                    customer.getCity(),
                    customer.getProvince(),
                    customer.getPostalCode()
            ));
        }
        return cusDto;
    }
}
