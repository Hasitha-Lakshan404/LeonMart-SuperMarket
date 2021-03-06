/**
 * @author : Hasitha Lakshan
 * Project Name: SuperMarket
 * Date        : 5/26/2022
 * Time        : 10:15 PM
 */

package lk.LeonMart.superMarket.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T,ID> extends SuperDAO{

    ArrayList<T>getAll() throws SQLException, ClassNotFoundException;
    boolean save(T dto) throws SQLException, ClassNotFoundException;
    boolean update(T dto) throws SQLException, ClassNotFoundException;
    T search(ID id) throws SQLException, ClassNotFoundException;
    boolean exist(ID id);
    boolean delete(ID id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;


}
