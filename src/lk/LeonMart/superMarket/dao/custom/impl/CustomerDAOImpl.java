/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket
 * Date :5/26/2022
 * Time :10:44 PM
 */

package lk.LeonMart.superMarket.dao.custom.impl;


import lk.LeonMart.superMarket.dao.custom.CustomerDAO;
import lk.LeonMart.superMarket.entity.Customer;

import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> getAll() {
        return null;
    }

    @Override
    public boolean save(Customer dto) {
        return false;
    }

    @Override
    public boolean update(Customer dto) {
        return false;
    }

    @Override
    public Customer search(String s) {
        return null;
    }

    @Override
    public boolean exist(String s) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public String generateNewId() {
        return null;
    }
}
