package cn.it.phw.ms.service;

import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.pojo.User;

public interface UserService extends BaseService<User> {

    /**
     * User Login
     * @param username not null
     * @param password not null
     * @return
     */
    JsonResult doLogin(String username, String password);

    /**
     * User Logout
     * @param userId not null
     * @return
     */
    JsonResult doLogout(Object userId);

    /**
     * 由用户来注册的方法
     * @param user
     * @return
     */
    JsonResult doRegister(User user);

    /**
     * 由管理员来手动添加用户的方法
     * @param user
     * @param adminId
     * @return
     */
    JsonResult insertUser(User user, Object adminId);
}
