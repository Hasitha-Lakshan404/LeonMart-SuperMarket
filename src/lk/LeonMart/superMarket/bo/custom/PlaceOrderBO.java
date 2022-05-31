/**
 * @author : Hasitha Lakshan
 * Project Name: SuperMarket
 * Date        : 5/31/2022
 * Time        : 5:30 PM
 */

package lk.LeonMart.superMarket.bo.custom;

import lk.LeonMart.superMarket.dto.CustomerDTO;
import lk.LeonMart.superMarket.dto.ItemDTO;
import lk.LeonMart.superMarket.dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceOrderBO {
    boolean purchaseOrder(OrderDTO dto) ;

    CustomerDTO searchCustomer(String id) ;

    ItemDTO searchItem(String code) ;

    boolean checkItemIsAvailable(String code) ;

    boolean checkCustomerIsAvailable(String id) ;

    String generateNewOrderID() ;

    ArrayList<CustomerDTO> getAllCustomers() ;

    ArrayList<ItemDTO> getAllItems();
}
