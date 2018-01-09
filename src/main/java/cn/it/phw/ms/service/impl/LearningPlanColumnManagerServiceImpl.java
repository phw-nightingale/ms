package cn.it.phw.ms.service.impl;

import cn.it.phw.ms.dao.BaseMapper;
import cn.it.phw.ms.dao.LearningPlanColumnManagerMapper;
import cn.it.phw.ms.pojo.LearningPlanColumnManager;
import cn.it.phw.ms.service.LearningPlanColumnManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LearningPlanColumnManagerServiceImpl extends BaseServiceImpl<LearningPlanColumnManager> implements LearningPlanColumnManagerService {

    @Autowired
    private LearningPlanColumnManagerMapper learningPlanColumnManagerMapper;

    @Override
    public BaseMapper<LearningPlanColumnManager> getBaseMapper() {
        return learningPlanColumnManagerMapper;
    }

}
