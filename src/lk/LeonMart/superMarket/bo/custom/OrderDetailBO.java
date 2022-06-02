/**
 * @author : Hasitha Lakshan
 * Project Name: SuperMarket
 * Date        : 6/2/2022
 * Time        : 4:27 PM
 */

package lk.LeonMart.superMarket.bo.custom;

import lk.LeonMart.superMarket.bo.SuperBO;
import lk.LeonMart.superMarket.dto.CustomerDTO;
import lk.LeonMart.superMarket.dto.OrderDTO;
import lk.LeonMart.superMarket.dto.OrderDetailDTO;
import lk.LeonMart.superMarket.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailBO extends SuperBO {

    ArrayList<OrderDetailDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException;

    public ArrayList<OrderDetailDTO> searchOrderDetails(String enteredText) throws SQLException, ClassNotFoundException;

}
