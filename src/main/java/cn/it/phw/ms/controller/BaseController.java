package cn.it.phw.ms.controller;


import cn.it.phw.ms.common.Authority;
import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.pojo.UserExample;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Authority
@RequestMapping("/ms")
public class BaseController {

    protected JsonResult jsonResult;
    protected Map<String, Object> data;

    public BaseController() {
        jsonResult = new JsonResult();
        data = new HashMap<>();
    }


}
