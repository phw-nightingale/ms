package cn.it.phw.ms.dao;

import cn.it.phw.ms.pojo.BaseEntity;
import cn.it.phw.ms.pojo.BaseExample;

import java.util.List;

public interface BaseMapper<T extends BaseEntity> {

    /*static final String SQL_INSERT = ".insert";

    static final String SQL_INSERT_LIST = ".insertList";

    static final String SQL_DELETE_BY_PK = ".deleteByPrimaryKey";

    static final String SQL_DELETE_BY_EXAMPLE = ".deleteByExample";

    static final String SQL_SELECT_BY_PK = ".selectByPrimaryKey";

    static final String SQL_SELECT_BY_EXAMPLE = ".selectByExample";

    static final String SQL_UPDATE_BY_PK = ".updateByPrimaryKey";

    static final String SQL_UPDATE_BY_EXAMPLE = ".updateByExample";*/


    /**
     * 根据主键查询一条记录
     * @param id
     * @return
     */
    T selectByPrimaryKey(Object id);

    /**
     * 根据给定条件查询记录
     * @param example
     * @return
     */
    List<T> selectByExample(BaseExample example);

    /**
     * 根据主键删除一条记录
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Object id);

    /**
     *
     * @param example
     * @return
     */
    Integer deleteByExample(BaseExample example);

    /**
     *
     * @param id
     * @return
     */
    Integer updateByPrimaryKey(Object id);

    /**
     *
     * @param example
     * @return
     */
    Integer updateByExample(BaseExample example);

    /**
     *
     * @param item
     * @return
     */
    Integer insert(T item);

}
