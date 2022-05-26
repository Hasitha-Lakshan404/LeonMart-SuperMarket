/**
 * @author : Hasitha Lakshan
 * Project Name: SuperMarket
 * Date        : 5/26/2022
 * Time        : 10:15 PM
 */

package lk.LeonMart.superMarket.dao;

import java.util.ArrayList;

public interface CrudDAO<T,ID> extends SuperDAO{

    ArrayList<T>getAll();
    boolean save(T dto);
    boolean update(T dto);
    T search(ID id);
    boolean exist(ID id);
    boolean delete(ID id);
    String generateNewId();


}
