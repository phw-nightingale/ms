package cn.it.phw.ms.controller;

import cn.it.phw.ms.common.AppContext;
import cn.it.phw.ms.common.Authority;
import cn.it.phw.ms.common.AuthorityType;
import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.pojo.BookExample;
import cn.it.phw.ms.pojo.User;
import cn.it.phw.ms.pojo.UserExample;
import cn.it.phw.ms.service.BaseService;
import cn.it.phw.ms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Authority
@Controller
@RequestMapping("/ms")
public class UserController extends BaseController {

    @Autowired
    private UserService userService; 

    @ResponseBody
    @PostMapping("/login")
    @Authority(AuthorityType.NoValidate)
    public JsonResult doLogin(String username, String password) {
        return userService.doLogin(username, password);
    }

    @ResponseBody
    @GetMapping("/users")
    public JsonResult findAllUsers() {
        return userService.selectByExample(null);
    }

    @ResponseBody
    @GetMapping("/user/{id}")
    public JsonResult findUserById(@PathVariable Integer id) {
        return userService.selectByPrimaryKey(id);
    }

    @ResponseBody
    @GetMapping("/logout")
    @Authority(AuthorityType.NoAuthority)
    public JsonResult doLogout(HttpServletRequest request) {
        return userService.doLogout(request.getAttribute(AppContext.KEY_ID));
    }

    @ResponseBody
    @PutMapping("/user")
    public JsonResult insertUser(User user, HttpServletRequest request) {
        return userService.insertUser(user, request.getAttribute(AppContext.KEY_ID));
    }

    @ResponseBody
    @PutMapping("/user/register")
    public JsonResult doRegisterUser(User user) {
        return userService.doRegister(user);
    }

    @ResponseBody
    @PostMapping("/user")
    public JsonResult updateUser(User user) {
        return userService.updateByPrimaryKey(user);
    }

    @ResponseBody
    @DeleteMapping("/user/{id}")
    public JsonResult deleteUser(@PathVariable Integer id) {
        return userService.deleteByPrimaryKey(id);
    }

    @ResponseBody
    @GetMapping("/user/now")
    public JsonResult findCurrUser(HttpServletRequest request) {
        return userService.selectByPrimaryKey(request.getAttribute(AppContext.KEY_ID));
    }

}
