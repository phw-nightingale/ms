package cn.it.phw.ms.service;

import cn.it.phw.ms.common.JsonResult;

public interface BookService extends BaseService {

    JsonResult selectBookByPrimaryKey(Object id);

}
