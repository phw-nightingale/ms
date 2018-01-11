package cn.it.phw.ms.dao;

import cn.it.phw.ms.pojo.BaseExample;
import cn.it.phw.ms.pojo.LearningPlanColumnManager;

import java.util.List;

public interface LearningPlanColumnManagerMapper extends BaseMapper<LearningPlanColumnManager> {

    /**
     *
     * @param example
     * @return
     */
    List<LearningPlanColumnManager> selectByExampleWithBLOBs(BaseExample example);

}
