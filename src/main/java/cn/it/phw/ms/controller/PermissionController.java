package cn.it.phw.ms.controller;

import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.pojo.Permission;
import cn.it.phw.ms.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ms")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;

    @ResponseBody
    @GetMapping("/permissions")
    public JsonResult findAllPermissions() {
        return permissionService.selectByExample(null);
    }

    @ResponseBody
    @PostMapping("/permission")
    public JsonResult updatePermissionById(Permission permission) {
        return permissionService.updateByPrimaryKey(permission);
    }

    @ResponseBody
    @GetMapping("/permission/{id}")
    public JsonResult findPermissionById(@PathVariable Integer id) {
        return permissionService.selectByPrimaryKey(id);
    }

}
