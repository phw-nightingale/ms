package cn.it.phw.ms.service.impl;

import cn.it.phw.ms.common.*;
import cn.it.phw.ms.dao.BaseMapper;
import cn.it.phw.ms.dao.UserMapper;
import cn.it.phw.ms.pojo.BaseExample;
import cn.it.phw.ms.pojo.User;
import cn.it.phw.ms.pojo.UserExample;
import cn.it.phw.ms.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private Gson gson;

    @Override
    public BaseMapper<User> getBaseMapper() {
        return userMapper;
    }


    @Override
    @Cacheable("doLogin")
    public JsonResult doLogin(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            jsonResult.setStatus(500);
            jsonResult.setMessage("Username Or Password Cannot be Empty");
        } else {
            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.or();
            criteria.andUsernameEqualTo(username);
            List<User> userList = userMapper.selectByExample(example);
            if (userList.size() == 0) {
                jsonResult.setStatus(500);
                jsonResult.setMessage("No Such User");
            } else {
                User user = userList.get(0);
                String nowPass = password + user.getSalt();
                String userPass = user.getPassword();
                if (userPass.equalsIgnoreCase(Md5Utils.MD5Encode(nowPass, "UTF-8", true))) {

                    //Update Last Login Time
                    user.setLastTime(new Date(System.currentTimeMillis()));
                    userMapper.updateByPrimaryKey(user);

                    //Create Token
                    String token = JwtUtils.createJWT(user.getId(), AppContext.KEY_ISSUSE, 1000*60*60*24);

                    //Save User in Redis
                    redisTemplate.opsForHash()
                            .put(AppContext.USER_CACHE, String.valueOf(user.getId()), gson.toJson(user));

                    //Return Json Data
                    jsonResult.setStatus(200);
                    jsonResult.setMessage("Login Success");
                    data.put(AppContext.KEY_TOKEN, token);
                    data.put(AppContext.KEY_DATA, user);
                    jsonResult.setData(data);

                } else {
                    jsonResult.setStatus(500);
                    jsonResult.setMessage("Password Incorrect");
                }
            }

        }
        return jsonResult;
    }

    @Override
    public JsonResult doLogout(Object userId) {
        if (userId == null) {
            jsonResult.setStatus(500);
            jsonResult.setMessage("Params Cannot be NULL");
        }
        HashOperations<String, String, Object> opsForHash = redisTemplate.opsForHash();
        if (opsForHash.hasKey(AppContext.USER_CACHE, userId)) {
            opsForHash.delete(AppContext.USER_CACHE, userId);
            jsonResult.setStatus(200);
            jsonResult.setMessage("Logout Successful");
        } else {
            jsonResult.setStatus(500);
            jsonResult.setMessage("Login First Please");
        }

        return jsonResult;
    }

}
