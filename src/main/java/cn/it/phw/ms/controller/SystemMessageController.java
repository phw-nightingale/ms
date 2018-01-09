package cn.it.phw.ms.controller;

import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.pojo.SystemMessage;
import cn.it.phw.ms.pojo.SystemMessageExample;
import cn.it.phw.ms.service.SystemMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ms")
public class SystemMessageController extends BaseController {

    @Autowired
    private SystemMessageService systemMessageService;

    @ResponseBody
    @GetMapping("/msg/lasted")
    public JsonResult findLastedMessage() {
        SystemMessageExample example = new SystemMessageExample();
        example.setOrderByClause("create_time");
        return systemMessageService.selectByExampleWithBLOBs(example);
    }

    @ResponseBody
    @PutMapping("/msg")
    public JsonResult insertSystemMessage(SystemMessage systemMessage) {
        return systemMessageService.insert(systemMessage);
    }

    @ResponseBody
    @PostMapping("/msg")
    public JsonResult updateSystemMessage(SystemMessage systemMessage) {
        return systemMessageService.updateByPrimaryKey(systemMessage);
    }

    @ResponseBody
    @DeleteMapping("/msg/{id}")
    public JsonResult deleteSystemMessageById(@PathVariable Integer id) {
        return systemMessageService.deleteByPrimaryKey(id);
    }

}
