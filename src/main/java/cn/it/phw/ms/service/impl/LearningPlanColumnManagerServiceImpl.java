package cn.it.phw.ms.service.impl;

import cn.it.phw.ms.common.AppContext;
import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.dao.BaseMapper;
import cn.it.phw.ms.dao.LearningPlanColumnManagerMapper;
import cn.it.phw.ms.pojo.BaseExample;
import cn.it.phw.ms.pojo.LearningPlanColumnManager;
import cn.it.phw.ms.service.LearningPlanColumnManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LearningPlanColumnManagerServiceImpl extends BaseServiceImpl<LearningPlanColumnManager> implements LearningPlanColumnManagerService {

    @Autowired
    private LearningPlanColumnManagerMapper learningPlanColumnManagerMapper;

    @Override
    public BaseMapper<LearningPlanColumnManager> getBaseMapper() {
        return learningPlanColumnManagerMapper;
    }

    @Override
    public JsonResult selectByExampleWithBLOBs(BaseExample example) {
        List<LearningPlanColumnManager> learningPlanColumnManagers = learningPlanColumnManagerMapper.selectByExampleWithBLOBs(example);
        if (learningPlanColumnManagers.size() == 0) {
            jsonResult.setStatus(500);
            jsonResult.setMessage("没有相应记录");
        } else {
            jsonResult.setStatus(200);
            jsonResult.setMessage("加载完成");
            data.put(AppContext.KEY_DATA, learningPlanColumnManagers);
            jsonResult.setData(data);
        }
        return jsonResult;
    }
}
