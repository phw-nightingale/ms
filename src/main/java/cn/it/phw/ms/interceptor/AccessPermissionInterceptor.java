package cn.it.phw.ms.interceptor;

import cn.it.phw.ms.common.AppContext;
import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.pojo.Permission;
import cn.it.phw.ms.pojo.PermissionExample;
import cn.it.phw.ms.pojo.User;
import cn.it.phw.ms.service.PermissionService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Component
public class AccessPermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private Gson gson;

    private JsonResult jsonResult = new JsonResult();

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uid = (String) httpServletRequest.getAttribute(AppContext.KEY_ID);
        Integer resId = (Integer) httpServletRequest.getAttribute("resId");

        User user = gson.fromJson((String)(redisTemplate.opsForHash().get(AppContext.USER_CACHE, uid)), User.class);
        String roleId = user.getRoleIds();

        PermissionExample example = new PermissionExample();
        PermissionExample.Criteria criteria = example.or();
        criteria.andActionEqualTo(httpServletRequest.getMethod())
                .andSourceEqualTo(String.valueOf(resId));
        jsonResult = permissionService.selectByExample(example);
        if (jsonResult.getStatus() == 200) {
            List<Permission> permissions = (List<Permission>) jsonResult.getData().get(AppContext.KEY_DATA);
            Permission permission = permissions.get(0);
            String[] requestRoleIds = permission.getRoleIds().split(",");
            for (String id : requestRoleIds) {
                if (id.equals(roleId)) {
                    return true;
                }
            }
            jsonResult.setStatus(500);
            jsonResult.setMessage("Permission Denied!");
            exportJsonResult(httpServletResponse, jsonResult);
        } else {
            exportJsonResult(httpServletResponse, jsonResult);
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * Export Json Result
     * @param httpServletResponse
     * @param jsonResult
     * @throws IOException
     */
    private void exportJsonResult(HttpServletResponse httpServletResponse, JsonResult jsonResult) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.println(gson.toJson(jsonResult));
        out.flush();
        out.close();
    }

}
