package cn.it.phw.ms.service.impl;

import cn.it.phw.ms.dao.BaseMapper;
import cn.it.phw.ms.dao.LearningPlanColumnMapper;
import cn.it.phw.ms.pojo.LearningPlanColumn;
import cn.it.phw.ms.service.LearningPlanColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LearningPlanServiceImpl extends BaseServiceImpl<LearningPlanColumn> implements LearningPlanColumnService {

    @Autowired
    private LearningPlanColumnMapper learningPlanColumnMapper;

    @Override
    public BaseMapper<LearningPlanColumn> getBaseMapper() {
        return learningPlanColumnMapper;
    }

}
