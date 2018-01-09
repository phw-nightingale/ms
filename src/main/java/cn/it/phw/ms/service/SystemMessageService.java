package cn.it.phw.ms.service;

import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.pojo.BaseExample;
import cn.it.phw.ms.pojo.SystemMessage;

public interface SystemMessageService extends BaseService<SystemMessage> {

    /**
     *
     * @param example
     * @return
     */
    JsonResult selectByExampleWithBLOBs(BaseExample example);

}
