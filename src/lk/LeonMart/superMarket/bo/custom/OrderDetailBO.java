/**
 * @author : Hasitha Lakshan
 * Project Name: SuperMarket
 * Date        : 6/2/2022
 * Time        : 4:27 PM
 */

package lk.LeonMart.superMarket.bo.custom;

import lk.LeonMart.superMarket.bo.SuperBO;
import lk.LeonMart.superMarket.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailBO extends SuperBO {

    ArrayList<OrderDetailDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException;

    ArrayList<OrderDetailDTO> searchOrderDetails(String enteredText) throws SQLException, ClassNotFoundException;

}
