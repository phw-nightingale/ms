package cn.it.phw.ms.service.impl;

import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.dao.BaseMapper;
import cn.it.phw.ms.dao.RoleMapper;
import cn.it.phw.ms.pojo.Role;
import cn.it.phw.ms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public BaseMapper<Role> getBaseMapper() {
        return roleMapper;
    }

    @Override
    public JsonResult insert(Role item) {
        item.setCreateTime(new Date(System.currentTimeMillis()));
        return super.insert(item);
    }
}
