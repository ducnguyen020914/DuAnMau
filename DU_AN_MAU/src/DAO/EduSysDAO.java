/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;

/**
 *
 * @author 84985
 */
abstract public class EduSysDAO<E, K> {

    abstract public void insert(E entity);

    abstract public void delete(K entity);

    abstract public void update(E entity);

    abstract public List<E> SelectALL();

    abstract public E SelectByID(K entity);
     abstract public List<E> selectBySQL(String sql,Object... args);

}
