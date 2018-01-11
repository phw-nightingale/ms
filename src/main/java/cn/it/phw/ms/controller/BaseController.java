package cn.it.phw.ms.controller;


import cn.it.phw.ms.common.Authority;
import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.pojo.BaseEntity;
import cn.it.phw.ms.pojo.UserExample;
import cn.it.phw.ms.service.BaseService;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Authority
public abstract class BaseController {

    protected JsonResult jsonResult;
    protected Map<String, Object> data;

    public BaseController() {
        jsonResult = new JsonResult();
        data = new HashMap<>();
    }


}
