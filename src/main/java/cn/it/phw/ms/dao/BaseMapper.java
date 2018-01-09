package cn.it.phw.ms.dao;

import cn.it.phw.ms.pojo.BaseEntity;
import cn.it.phw.ms.pojo.BaseExample;

import java.util.List;

public interface BaseMapper<T extends BaseEntity> {

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
