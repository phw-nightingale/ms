package cn.it.phw.ms.service.impl;

import cn.it.phw.ms.dao.BaseMapper;
import cn.it.phw.ms.dao.LearningPlanFormMapper;
import cn.it.phw.ms.pojo.LearningPlanForm;
import cn.it.phw.ms.service.LearningPlanFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LearningPlanFormServiceImpl extends BaseServiceImpl<LearningPlanForm> implements LearningPlanFormService {

    @Autowired
    private LearningPlanFormMapper learningPlanFormMapper;

    @Override
    public BaseMapper<LearningPlanForm> getBaseMapper() {
        return learningPlanFormMapper;
    }


}
