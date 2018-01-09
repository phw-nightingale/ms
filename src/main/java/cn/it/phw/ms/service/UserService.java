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
}
