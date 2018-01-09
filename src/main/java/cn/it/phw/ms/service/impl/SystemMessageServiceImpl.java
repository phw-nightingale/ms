package cn.it.phw.ms.service.impl;

import cn.it.phw.ms.common.AppContext;
import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.dao.BaseMapper;
import cn.it.phw.ms.dao.SystemMessageMapper;
import cn.it.phw.ms.pojo.BaseExample;
import cn.it.phw.ms.pojo.SystemMessage;
import cn.it.phw.ms.service.SystemMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SystemMessageServiceImpl extends BaseServiceImpl<SystemMessage> implements SystemMessageService {

    @Autowired
    private SystemMessageMapper systemMessageMapper;

    @Override
    public BaseMapper<SystemMessage> getBaseMapper() {
        return systemMessageMapper;
    }

    @Override
    public JsonResult selectByExampleWithBLOBs(BaseExample example) {
        List<SystemMessage> systemMessages = systemMessageMapper.selectByExampleWithBLOBs(example);
        if (systemMessages.size() == 0) {
            jsonResult.setStatus(500);
            jsonResult.setMessage("Error: Don't have any Record");
        } else {
            jsonResult.setStatus(200);
            jsonResult.setMessage("OK");
            data.put(AppContext.KEY_DATA, systemMessages);
            jsonResult.setData(data);
        }
        return jsonResult;
    }
}
