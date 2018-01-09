package cn.it.phw.ms.service.impl;

import cn.it.phw.ms.dao.BaseMapper;
import cn.it.phw.ms.dao.PermissionMapper;
import cn.it.phw.ms.pojo.Permission;
import cn.it.phw.ms.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public BaseMapper<Permission> getBaseMapper() {
        return permissionMapper;
    }

}
