package cn.it.phw.ms.service.impl;

import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.dao.BaseMapper;
import cn.it.phw.ms.dao.ResourceMapper;
import cn.it.phw.ms.pojo.Resource;
import cn.it.phw.ms.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public BaseMapper<Resource> getBaseMapper() {
        return resourceMapper;
    }

    @Override
    public JsonResult insert(Resource item) {
        item.setIdentity(UUID.randomUUID().toString());
        return super.insert(item);
    }
}
