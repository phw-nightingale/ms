package cn.it.phw.ms.service;

import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.pojo.BaseEntity;
import cn.it.phw.ms.pojo.BaseExample;

public interface BaseService<T extends BaseEntity> {

    JsonResult selectByPrimaryKey(Object id);

    JsonResult selectByExample(BaseExample example);

    JsonResult updateByPrimaryKey(Object id);

    JsonResult updateByExample(BaseExample example);

    JsonResult deleteByPrimaryKey(Object id);

    JsonResult deleteByExample(BaseExample example);

    JsonResult insert(T item);

}
