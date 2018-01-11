package cn.it.phw.ms.service;

import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.pojo.BaseExample;
import cn.it.phw.ms.pojo.LearningPlanColumnManager;

public interface LearningPlanColumnManagerService extends BaseService<LearningPlanColumnManager> {

    /**
     *
     * @param example
     * @return
     */
    JsonResult selectByExampleWithBLOBs(BaseExample example);

}
