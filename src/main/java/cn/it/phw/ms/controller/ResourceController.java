package cn.it.phw.ms.controller;

import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.pojo.Resource;
import cn.it.phw.ms.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ms")
public class ResourceController extends BaseController {

    @Autowired
    private ResourceService resourceService;

    @ResponseBody
    @GetMapping("/resources")
    public JsonResult findAllResources() {
        return resourceService.selectByExample(null);
    }

    @ResponseBody
    @PostMapping("/resource")
    public JsonResult updateResourceById(Resource resource) {
        return resourceService.updateByPrimaryKey(resource);
    }

    @ResponseBody
    @PutMapping("/resource")
    public JsonResult insertResource(Resource resource) {
        return resourceService.insert(resource);
    }

    @ResponseBody
    @DeleteMapping("/resource/{id}")
    public JsonResult deleteResourceById(@PathVariable Integer id) {
        return resourceService.deleteByPrimaryKey(id);
    }


}
