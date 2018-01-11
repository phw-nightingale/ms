package cn.it.phw.ms.controller;

import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.pojo.Role;
import cn.it.phw.ms.service.BaseService;
import cn.it.phw.ms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ms")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @PutMapping("/role")
    public JsonResult insertRole(Role role) {
        return roleService.insert(role);
    }

    @ResponseBody
    @GetMapping("/roles")
    public JsonResult findAllRoles() {
        return roleService.selectByExample(null);
    }

    @ResponseBody
    @DeleteMapping("/role/{id}")
    public JsonResult deleteRolesById(@PathVariable Integer id) {
        return roleService.deleteByPrimaryKey(id);
    }

    @ResponseBody
    @PostMapping("/role")
    public JsonResult updateRolesById(Role role) {
        return roleService.updateByPrimaryKey(role);
    }

    @ResponseBody
    @GetMapping("/role/{id}")
    public JsonResult findRoleById(@PathVariable Integer id) {
        return roleService.selectByPrimaryKey(id);
    }


}
