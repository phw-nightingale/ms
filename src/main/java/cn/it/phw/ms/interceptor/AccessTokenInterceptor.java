package cn.it.phw.ms.interceptor;

import cn.it.phw.ms.common.AppContext;
import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.common.JwtUtils;
import cn.it.phw.ms.pojo.User;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class AccessTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private Gson gson;

    private JsonResult jsonResult = new JsonResult();

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = httpServletRequest.getHeader(AppContext.AUTHORIZATION);
        if (StringUtils.isEmpty(token)) {
            token = httpServletRequest.getParameter("token");
        }
        if (StringUtils.isEmpty(token)) {
            jsonResult.setStatus(500);
            jsonResult.setMessage("您还没有登录，请先登录。");
            exportJsonResult(httpServletResponse, jsonResult);
        } else {
            try {
                Claims claims = JwtUtils.parseJWT(token);
                String uid = claims.getId();

                if (redisTemplate.opsForHash().hasKey(AppContext.USER_CACHE, uid)) {
                    httpServletRequest.setAttribute(AppContext.KEY_ID, uid);
                    return true;
                }

            } catch (SignatureException | MalformedJwtException e) {
                jsonResult.setStatus(500);
                jsonResult.setMessage("身份信息异常，请重新登录！");
                exportJsonResult(httpServletResponse, jsonResult);
            }
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
