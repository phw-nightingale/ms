package cn.it.phw.ms.service;

import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.pojo.BaseEntity;
import cn.it.phw.ms.pojo.BaseExample;

public interface BaseService<T extends BaseEntity> {

    /**
     *
     * @param id
     * @return
     */
    JsonResult selectByPrimaryKey(Object id);

    /**
     * Base Select Option
     *
     * @param example when example is null, it means select all
     * @return
     */
    JsonResult selectByExample(BaseExample example);

    /**
     *
     * @param item
     * @return
     */
    JsonResult updateByPrimaryKey(T item);

    /**
     *
     * @param example
     * @return
     */
    JsonResult updateByExample(BaseExample example);

    /**
     *
     * @param id
     * @return
     */
    JsonResult deleteByPrimaryKey(Object id);

    /**
     *
     * @param example
     * @return
     */
    JsonResult deleteByExample(BaseExample example);

    /**
     *
     * @param item
     * @return
     */
    JsonResult insert(T item);

}
