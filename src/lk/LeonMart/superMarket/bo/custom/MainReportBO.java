/**
 * @author : Hasitha Lakshan
 * Project Name: SuperMarket
 * Date        : 6/3/2022
 * Time        : 11:18 PM
 */

package lk.LeonMart.superMarket.bo.custom;

import lk.LeonMart.superMarket.bo.SuperBO;
import lk.LeonMart.superMarket.dto.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MainReportBO extends SuperBO {
    ArrayList<CustomDTO> MostMovableItem() throws SQLException, ClassNotFoundException;

    ArrayList<CustomDTO> LeastMovableItem() throws SQLException, ClassNotFoundException;
}
